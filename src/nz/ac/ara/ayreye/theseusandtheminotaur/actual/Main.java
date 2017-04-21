package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Main {

	public static void main(String[] args) {

		/* <<Models>> */
		Playable game = new Game();
		Loadable gameLoader = (Loadable)game;
		Saveable gameSaver = (Saveable)game;
		
		Loader loader = new FileLoader();
		Saver saver = new FileSaver();
		
		/* <<Views>> */
		View view = new ConsoleView();
		
		/* <<Controller>> */
		new Controller(game, gameLoader, gameSaver, loader, saver, view).run();
	}

}
