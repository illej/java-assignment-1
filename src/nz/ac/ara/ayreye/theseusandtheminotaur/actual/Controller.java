package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

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
	
	public void run() {
		
		// Load file into a hashmap
		String filename = "level.txt"; // HARDCODED
		this.loader.load(gameLoader, filename);
		
		/* Tests */
		System.out.println("> walls above:");
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				Wall wall = gameSaver.whatsAbove(new Pointer(j, i));
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
				Wall wall = gameSaver.whatsLeft(new Pointer(j, i));
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
				Point here = new Pointer(j, i);
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
				Point here = new Pointer(j, i);
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
				Point here = new Pointer(j, i);
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
