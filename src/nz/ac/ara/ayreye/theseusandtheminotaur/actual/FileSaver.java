package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileSaver implements Saver {

	//private Saveable gameSaver;
	
//	public FileSaver(Saveable gameSaver) {
//		this.gameSaver = gameSaver;
//	}
	
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
		
		// TODO: condense!!!
		// and maybe extract to new method 'builerOrSeomthing'?
		// build 'U'
		str.append("U=");
		for (int i = 0; i < depth; i++) {
			for (int j = 0; j < width; j++) {
				Wall wall = gameSaver.whatsAbove(new Position(j, i));
				if (wall == Wall.SOMETHING) {
					str.append("x");
				} else {
					str.append("o");
				}
			}
			str.append(","); // TODO: don't append after last
		}
		// build 'L'
		str.append("L=");
		for (int i = 0; i < depth; i++) {
			for (int j = 0; j < width; j++) {
				Wall wall = gameSaver.whatsLeft(new Position(j, i));
				if (wall == Wall.SOMETHING) {
					str.append("x");
				} else {
					str.append("o");
				}
			}
			str.append(","); // TODO: if last, append(";");
		}
		// build 'M'
		Point minotaurAt = gameSaver.wheresMinotaur();
		str.append("M=" + minotaurAt.across() + "," + minotaurAt.down() + ";");
		// build 'T'
		Point theseusAt = gameSaver.wheresTheseus();
		str.append("T=" + theseusAt.across() + "," + theseusAt.down() + ";");
		// build 'E'
		Point exitAt = gameSaver.wheresExit();
		str.append("E=" + exitAt.across() + "," + exitAt.down() + ":");
		// TODO: levelname?
		
		level = str.toString();
		
		try (BufferedWriter bWriter =
				new BufferedWriter(new FileWriter(fileName))) {
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
