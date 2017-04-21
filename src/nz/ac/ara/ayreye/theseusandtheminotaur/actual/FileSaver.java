package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver implements Saver {

	@Override
	public void save(Saveable gameSaver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Saveable gameSaver, String fileName) {
		int width = gameSaver.getWidthAcross();
		int depth = gameSaver.getDepthDown();
		String level = "";
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < depth; i++) {
			for (int j = 0; j < width; j++) {
				Wall wall = gameSaver.whatsAbove(new Position(j, i));
				if (wall == Wall.SOMETHING) {
					str.append('x');
				} else {
					str.append('o');
				}
				
			}
		}
		level = str.toString();
		
		try(BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileName))) {
			bWriter.write(level);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(Saveable gameSaver, String fileName, String levelName) {
		// TODO Auto-generated method stub
		
	}

}
