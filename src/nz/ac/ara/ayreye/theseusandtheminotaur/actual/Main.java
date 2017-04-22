package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Main {

	public static void main(String[] args) {

		/* <<Models>> */
		Game game = new Game(new FileLoader(), new FileSaver());
		Playable gamePlayer = (Playable) game;
		Loadable gameLoader = (Loadable) game;
		Saveable gameSaver = (Saveable) game;
		Loader loader = (Loader) game;
		Saver saver = (Saver) game;
		
		/* <<View>> */
		View view = new ConsoleView();
		
		/* <<Controller>> */
		new Controller(
				gamePlayer,
				gameLoader,
				gameSaver,
				loader,
				saver,
				view).run();
	}

}
