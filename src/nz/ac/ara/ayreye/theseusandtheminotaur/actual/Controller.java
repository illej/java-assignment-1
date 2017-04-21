package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Controller {

	private Playable game;
	private Loadable gameLoader;
	private Saveable gameSaver;
	private Loader loader;
	private Saver saver;
	private View view;
	
	public Controller(
			Playable game, 
			Loadable gameLoader, 
			Saveable gameSaver, 
			Loader loader,
			Saver saver,
			View view) {
		this.game = game;
		this.gameLoader = gameLoader;
		this.gameSaver = gameSaver;
		this.loader = loader;
		this.saver = saver;
		this.view = view;
	}
	
	private Runnable partLib(String key, Point where) {
		
		Map<String, Runnable> partLib = new HashMap<String, Runnable>();
		partLib.put("U", () -> gameLoader.addWallAbove(where));
		partLib.put("L", () -> gameLoader.addWallLeft(where));
		partLib.put("T", () -> gameLoader.addTheseus(where));
		partLib.put("M", () -> gameLoader.addMinotaur(where));
		partLib.put("E", () -> gameLoader.addExit(where));
		
		return partLib.get(key);
	}
	
	private Point parseCoordinates(String[] value) {
		int x = Integer.parseInt(value[0]);
		int y = Integer.parseInt(value[1]);
		return new Position(x, y);
	}
	
	public void run() {
		
		/* LOAD */
		String filename = "level.txt"; // HARDCODED
		Map<String, String[]> level = this.loader.load(filename);
		
		// U
		String[] rowsUps = level.get("U");
		int depth = rowsUps.length;
		int width = rowsUps[0].length();
		
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
		
		// L
		String[] rowsLefts = level.get("L");
		for (int i = 0; i < depth; i++) {
			String row = rowsLefts[i];
			for (int j = 0; j < width; j++) {
				if (row.charAt(j) == 'x') {
					gameLoader.addWallLeft(new Position(j, i));
				}
			}
		}
		
		/* TEST THIS CODE BLOCK */
		Point where = new Position(3, 3);
		
		Map<String, Consumer<Point>> partLib = new HashMap<String, Consumer<Point>>();
		partLib.put("U", (Point x) -> gameLoader.addWallAbove(x));
		partLib.put("L", (Point x) -> gameLoader.addWallLeft(x));
		partLib.put("T", (Point x) -> gameLoader.addTheseus(x));
		partLib.put("M", (Point x) -> gameLoader.addMinotaur(x));
		partLib.put("E", (Point x) -> gameLoader.addExit(x));
		
		for (String key : partLib.keySet()) {
		    partLib.get(key).accept(where);;
		}
		/* TEST THIS CODE BLOCK */
		
		// T
		//Point where = this.parseCoordinates(level.get("T"));
		//gameLoader.addTheseus(where);
		
		// M
		String[] minotaurCoords = level.get("M");
		int mX = Integer.parseInt(minotaurCoords[0]);
		int mY = Integer.parseInt(minotaurCoords[1]);
		gameLoader.addMinotaur(new Position(mX, mY));
		
		// E
		String[] exitCoords = level.get("E");
		int eX = Integer.parseInt(exitCoords[0]);
		String coords = exitCoords[1];
		String sub = coords.substring(0, 1);
		int eY = Integer.parseInt(sub);
		gameLoader.addExit(new Position(eX, eY));
		
		
		/* Tests */
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				String wall = gameSaver.whatsAbove(new Position(j, i)).toString();
				row += wall + " ";
			}
			view.display(row);
		}
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				String wall = gameSaver.whatsLeft(new Position(j, i)).toString();
				row += wall + " ";
			}
			view.display(row);
		}
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Point here = new Position(j, i);
				if (gameSaver.wheresTheseus().across() == here.across()
						&& gameSaver.wheresTheseus().down() == here.down()) {
					row += "T ";
				} else {
					row += "  ";
				}
			}
			view.display(row);
		}
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Point here = new Position(j, i);
				if (gameSaver.wheresMinotaur().across() == here.across()
						&& gameSaver.wheresMinotaur().down() == here.down()) {
					row += "M ";
				} else {
					row += "  ";
				}
			}
			view.display(row);
		}
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Point here = new Position(j, i);
				if (gameSaver.wheresExit().across() == here.across()
						&& gameSaver.wheresExit().down() == here.down()) {
					row += "E ";
				} else {
					row += "  ";
				}
			}
			view.display(row);
		}
		
		/* SAVE */
//		String newLevel = "level01.txt";
//		saver.save(gameSaver, newLevel);
		
	}
}
