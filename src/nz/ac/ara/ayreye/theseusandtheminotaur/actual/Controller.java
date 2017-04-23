package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Controller {

	private Playable playable;
	private Loadable loadable;
	private Saveable saveable;
	private Loader loader;
	private Saver saver;
	private View view;
	
	public Controller(
			Playable playable, 
			Loadable loadable, 
			Saveable saveable, 
			Loader loader,
			Saver saver,
			View view) {
		this.playable = playable;
		this.loadable = loadable;
		this.saveable = saveable;
		this.loader = loader;
		this.saver = saver;
		this.view = view;
	}
	
	public void run() {
		
		String filename = "hadnmande01.txt"; // HARDCODED
		this.loader.load(loadable, filename);
		
		/* Tests */
		System.out.println("> walls above:");
		for (int i = 0; i < saveable.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < saveable.getWidthAcross(); j++) {
				Wall wall = saveable.whatsAbove(new Pointer(j, i));
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
		for (int i = 0; i < saveable.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < saveable.getWidthAcross(); j++) {
				Wall wall = saveable.whatsLeft(new Pointer(j, i));
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
		for (int i = 0; i < saveable.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < saveable.getWidthAcross(); j++) {
				Point here = new Pointer(j, i);
				if (saveable.wheresTheseus().across() == here.across()
						&& saveable.wheresTheseus().down() == here.down()) {
					row += " T ";
				} else {
					row += " - ";
				}
			}
			System.out.println(row);
		}
		System.out.println();
		
		System.out.println("> minotaurAt:");
		for (int i = 0; i < saveable.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < saveable.getWidthAcross(); j++) {
				Point here = new Pointer(j, i);
				if (saveable.wheresMinotaur().across() == here.across()
						&& saveable.wheresMinotaur().down() == here.down()) {
					row += " M ";
				} else {
					row += " - ";
				}
			}
			System.out.println(row);
		}
		System.out.println();
		
		System.out.println("> exitAt:");
		for (int i = 0; i < saveable.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < saveable.getWidthAcross(); j++) {
				Point here = new Pointer(j, i);
				if (saveable.wheresExit().across() == here.across()
						&& saveable.wheresExit().down() == here.down()) {
					row += " E ";
				} else {
					row += " - ";
				}
			}
			System.out.println(row);
		}
		
		/* SAVE */
//		String newLevel = "level01.txt";
//		saver.save(saveable, newLevel);
	}
}
