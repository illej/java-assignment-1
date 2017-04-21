package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

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
	
	public void run() {
		/* LOAD */
		String filename = "level.txt";
		String level = loader.load(filename);
		
		// TODO: extract to new method with a hashmap
		String[] sections = level.split(";");
		String up = sections[0];
		String left = sections[1];
		String min = sections[2];
		String thes = sections[3];
		String exit = sections[4];
		
		String upTrimed = up.substring(2, up.length());
		String[] rowsUp = upTrimed.split(",");
		
		String leftTrimmed = left.substring(2, left.length());
		String[] rowsLeft = leftTrimmed.split(",");
		
		gameLoader.setDepthDown(rowsUp.length);
		gameLoader.setWidthAcross(rowsUp[0].length());
		
		// build level in GAME
		for (int i = 0; i < rowsUp.length; i++) {
			String row = rowsUp[i];
			view.display("row: "+ row);
			for (int j = 0; j < row.length(); j++) {
				char ch = row.charAt(j);
				view.display("ch: " + ch);
				if (ch == 'x') {
					gameLoader.addWallAbove(new Position(j, i));
				}
			}
		}
		
		/* Tests */
		for (String section : sections) {
			view.display(section);
		}
		view.display("ups");
		for (String row : rowsUp) {
			view.display(row);
		}
		view.display("lefts");
		for (String row : rowsLeft) {
			view.display(row);
		}
		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
			String row = "";
			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
				String wall = gameSaver.whatsAbove(new Position(j, i)).toString();
				row += wall + " ";
			}
			view.display(row);
		}
		
		/* SAVE */
//		String newLevel = "level01.txt";
//		saver.save(gameSaver, newLevel);
		
	}
}
