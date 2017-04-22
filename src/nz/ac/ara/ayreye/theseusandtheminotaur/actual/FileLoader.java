package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FileLoader implements Loader {
	
	/*
	 * private methods
	 */

	private Map<String, Object> parse(String level) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String trimmed = level.substring(0, level.length() - 1);
		String[] sections = trimmed.split(";");

		for (String section : sections) {
			String[] pair = section.split("=");
			String key = pair[0];
			String[] values = pair[1].split(",");
			map.put(key, values);
		}
		
		/* TEST */
		System.out.println("> map contents:");
		for (Map.Entry<String, Object> entry : map.entrySet()) {
		    String key = entry.getKey();
		    String[] value = (String[]) entry.getValue();
		    System.out.println(key + " : " + Arrays.toString(value));
		}
		System.out.println();
		/**/
		
		return map;
	}
	
	private Map<String, Object> loadFile(String filename) {
		String level = "";

		// only 1 line per file anyway?
		try (BufferedReader bufferedReader = 
				new BufferedReader(new FileReader(filename))) {
			
			StringBuilder strBuilder = new StringBuilder();
			String line = bufferedReader.readLine();
			
			while (line != null) { 
				strBuilder.append(line); 
				line = bufferedReader.readLine();
				if (line != null) { 
					strBuilder.append("\n"); 
				} 
			}
			
			level = strBuilder.toString();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("> file contents:");
		System.out.println(level);
		return this.parse(level);
	}
	
	private Point parseCoordinates(String[] value) {
		int x = Integer.parseInt((String) value[0]);
		int y = Integer.parseInt((String) value[1]);
		return new Pointer(x, y);
	}
	
	/*
	 * <<interface>> Loader
	 */
	
	@Override
	public void load(Loadable gameLoader, String filename) {
		Map<String, Object> levelInfo = this.loadFile(filename);		
		Map<String, Consumer<Object>> funcMap = new HashMap<String, Consumer<Object>>();
		
		funcMap.put("U", (Object obj) -> {
			System.out.println("> adding walls above..");
			String[] rows = (String[]) obj;
			int depth = rows.length;
			int width = rows[0].length();
			
			for (int i = 0; i < depth; i++) {
				String row = rows[i];
				for (int j = 0; j < width; j++) {
					if (row.charAt(j) == 'x') {
						gameLoader.addWallAbove(new Pointer(j, i));
					}
				}
			}
			System.out.println("> done!");
		});
		funcMap.put("L", (Object obj) -> {
			System.out.println("> adding walls left..");
			String[] rows = (String[]) obj;
			int depth = rows.length;
			int width = rows[0].length();
			
			for (int i = 0; i < depth; i++) {
				String row = rows[i];
				for (int j = 0; j < width; j++) {
					if (row.charAt(j) == 'x') {
						gameLoader.addWallLeft(new Pointer(j, i));
					}
				}
			}
			System.out.println("> done!");
		});
		funcMap.put("T", (Object obj) -> {
			System.out.println("> adding theseus..");
			Point p = this.parseCoordinates((String[]) obj);
			gameLoader.addTheseus(p);
		});
		funcMap.put("M", (Object obj) -> {
			System.out.println("> adding minotaur..");
			Point p = this.parseCoordinates((String[]) obj);
			gameLoader.addMinotaur(p);
			System.out.println("> done!");
		});
		funcMap.put("E", (Object obj) -> {
			System.out.println("> adding exit..");
			Point p = this.parseCoordinates((String[]) obj);
			gameLoader.addExit(p);
			System.out.println("> done!");
		});
		
		String[] rs = (String[]) levelInfo.get("U");
		int d = rs.length;
		int w = rs[0].length();
		gameLoader.setDepthDown(d);
		gameLoader.setWidthAcross(w);
		
		levelInfo.forEach((key, value) -> { funcMap.get(key).accept(value); });
	}

}
