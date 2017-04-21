package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FileLoader implements Loader {
	
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
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
		    String key = entry.getKey();
		    String[] value = entry.getValue();
		    System.out.println(key + " : " + Arrays.toString(value));
		}
		
		return map;
	}
	
	/*
	 * <<interface>> Loader
	 */
	
	@Override
	public Map<String, String[]> load(String filename) {
		
		String level = "";

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
		
		return this.parse(level);
	}

}
