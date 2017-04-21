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
	
	//private Loadable gameLoader;
	
//	public FileLoader(Loadable gameLoader) {
//		this.gameLoader = gameLoader;
//	}
	
	/*
	 * private methods
	 */

	private Map<String, String[]> parse(String level) {
		// TODO: remove the trailing ':'
		Map<String, String[]> map = new HashMap<String, String[]>();
		
		String[] sections = level.split(";");

		for (String section : sections) {
			String[] pair = section.split("=");
			String key = pair[0];
			String[] values = pair[1].split(",");
			map.put(key, values);
		}
		
		/* TEST */
		System.out.println("> map contents:");
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
		    String key = entry.getKey();
		    String[] value = entry.getValue();
		    System.out.println(key + " : " + Arrays.toString(value));
		}
		System.out.println();
		/**/
		
		return map;
	}
	
	private Map<String, String[]> loadFile(String filename) {
		
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
		System.out.println();
		return this.parse(level);
	}
	
	private Point parseCoordinates(String[] value) {
		int x = Integer.parseInt(value[0]);
		int y = Integer.parseInt(value[1]);
		return new Position(x, y);
	}
	
	/*
	 * <<interface>> Loader
	 */
	
	@Override
	public void load(Loadable gameLoader, String filename) {
		Map<String, String[]> levelMap = this.loadFile(filename);
		// do all the loops n stuff!
		
		// Use hashmap to build the level in GAME
		/* U */
		String[] rowsUps = levelMap.get("U");
		
		int depth = rowsUps.length;
		System.out.println(depth);
		int width = rowsUps[0].length();
		System.out.println(width);
		
		gameLoader.setDepthDown(depth);
		gameLoader.setWidthAcross(width);
		
		for (int i = 0; i < depth; i++) {
			String row = rowsUps[i];
			for (int j = 0; j < width; j++) {
				if (row.charAt(j) == 'x') {
					gameLoader.addWallAbove(new Position(j, i));
				}
			}
		}
		
		/* L */
		String[] rowsLefts = levelMap.get("L");
		for (int i = 0; i < depth; i++) {
			String row = rowsLefts[i];
			for (int j = 0; j < width; j++) {
				if (row.charAt(j) == 'x') {
					gameLoader.addWallLeft(new Position(j, i));
				}
			}
		}
		
		/* TEST THIS CODE BLOCK
		 * Works for T, M, E
		 * need something different for U, L?  
		 */
		System.out.println("> building level:");
		
		Point where = new Position(3, 3);
		
		// => Only being called by options 1 & 2
		Map<String, Consumer<Point>> partLib = new HashMap<String, Consumer<Point>>();
		partLib.put("U", (Point x) -> { 
			// TODO: make this a separate function?
			for (int i = 0; i < depth; i++) {
				String row = rowsUps[i];
				for (int j = 0; j < width; j++) {
					if (row.charAt(j) == 'x') {
						gameLoader.addWallAbove(new Position(j, i));
						System.out.println("adding wall above @ " + j + ", " + i + "!");
					}
				}
			}
		});
		partLib.put("L", (Point x) -> { gameLoader.addWallLeft(x); System.out.println("adding wall left @ " + x.across() + ", " + x.down() + "!"); });
		partLib.put("T", (Point x) -> { gameLoader.addTheseus(x); System.out.println("adding theseus @ " + x.across() + ", " + x.down() + "!"); });
		partLib.put("M", (Point x) -> { gameLoader.addMinotaur(x); System.out.println("adding minotaur @ " + x.across() + ", " + x.down() + "!"); });
		partLib.put("E", (Point x) -> { gameLoader.addExit(x); System.out.println("adding exit @ " + x.across() + ", " + x.down() + "!"); });
		
		// option 1
//				for (String key : partLib.keySet()) {
//				    partLib.get(key).accept(where);;
//				}
		
		// option 2
//				partLib.forEach((key, value) -> { //value.accept(where); });
//				    System.out.println("Key: " + key + " Value(lambda): " + value);
//				    value.accept(where);
//				});
		
		/* TEST THIS CODE BLOCK */
		
		/* T */
		System.out.println("> adding a theseus:");
		String[] value = levelMap.get("T");
		Point location = this.parseCoordinates(value);
		gameLoader.addTheseus(location);
		System.out.println();
		
		/* M */
		System.out.println("> adding a minotaur:");
		String[] minotaurCoords = levelMap.get("M");
		int mX = Integer.parseInt(minotaurCoords[0]);
		int mY = Integer.parseInt(minotaurCoords[1]);
		gameLoader.addMinotaur(new Position(mX, mY));
		System.out.println();
		
		/* E */
		System.out.println("> adding an exit:");
		String[] exitCoords = levelMap.get("E");
		int eX = Integer.parseInt(exitCoords[0]);
		String coords = exitCoords[1];
		String sub = coords.substring(0, 1);
		int eY = Integer.parseInt(sub);
		gameLoader.addExit(new Position(eX, eY));
		System.out.println();
		
		

	}
	
	

}
