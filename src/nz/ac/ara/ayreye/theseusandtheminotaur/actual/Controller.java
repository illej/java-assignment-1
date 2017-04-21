package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Controller {

	private Playable gamePlayer;
	private Loadable gameLoader;
	private Saveable gameSaver;
	private Loader loader;
	private Saver saver;
	private View view;
	
	public Controller(
			Playable gamePlayer, 
			Loadable gameLoader, 
			Saveable gameSaver, 
			Loader loader,
			Saver saver,
			View view) {
		this.gamePlayer = gamePlayer;
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
		
		// Load file into a hashmap
		String filename = "level.txt"; // HARDCODED
		this.loader.load(gameLoader, filename);
		
		/* Tests */
		System.out.println("> walls above:");
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Wall wall = gameSaver.whatsAbove(new Position(j, i));
				if (wall == Wall.SOMETHING) {
					row += "^- ";
				} else {
					row += " - ";
				}
			}
			System.out.println(row);
		}
		System.out.println();
		
		System.out.println("> walls left:");
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Wall wall = gameSaver.whatsLeft(new Position(j, i));
				if (wall == Wall.SOMETHING) {
					row += "|- ";
				} else {
					row += " - ";
				}
				
			}
			System.out.println(row);
		}
		System.out.println();
		
		System.out.println("> theseusAt:");
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Point here = new Position(j, i);
				if (gameSaver.wheresTheseus().across() == here.across()
						&& gameSaver.wheresTheseus().down() == here.down()) {
					row += " T ";
				} else {
					row += " - ";
				}
			}
			System.out.println(row);
		}
		System.out.println();
		
		System.out.println("> minotaurAt:");
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Point here = new Position(j, i);
				if (gameSaver.wheresMinotaur().across() == here.across()
						&& gameSaver.wheresMinotaur().down() == here.down()) {
					row += " M ";
				} else {
					row += " - ";
				}
			}
			System.out.println(row);
		}
		System.out.println();
		
		System.out.println("> exitAt:");
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Point here = new Position(j, i);
				if (gameSaver.wheresExit().across() == here.across()
						&& gameSaver.wheresExit().down() == here.down()) {
					row += " E ";
				} else {
					row += " - ";
				}
			}
			System.out.println(row);
		}
		
		/* SAVE */
		String newLevel = "level01.txt";
		saver.save(gameSaver, newLevel);
	}
}
