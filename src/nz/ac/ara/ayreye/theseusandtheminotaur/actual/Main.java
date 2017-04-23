package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Main {

	public static void main(String[] args) {

		/* <<Models>> */
		Game game = new Game(new FileLoader(), new FileSaver());
		Playable playable = (Playable) game;
		Loadable loadable = (Loadable) game;
		Saveable saveable = (Saveable) game;
		Loader loader = (Loader) game;
		Saver saver = (Saver) game;
		
		/* <<View>> */
		View view = new ConsoleView();
		
		/* <<Controller>> */
		new Controller(
				playable,
				loadable,
				saveable,
				loader,
				saver,
				view).run();
	}

}
