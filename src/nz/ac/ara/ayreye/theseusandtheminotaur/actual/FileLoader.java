package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileLoader implements Loader {
	
	@Override
	public String load(String filename) {
		String level = "";
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
		    StringBuilder strBuilder = new StringBuilder();
		    String line = bufferedReader.readLine();
			while (line != null) { 
				strBuilder.append(line); 
				line = bufferedReader.readLine();
				if(line != null) { 
					strBuilder.append("\n"); 
				} 
			}
			level = strBuilder.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return level;
	}

}
