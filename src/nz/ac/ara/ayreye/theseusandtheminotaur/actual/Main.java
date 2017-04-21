package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Main {

	public static void main(String[] args) {

//		/* <<Models>> */
//		Playable game = new Game();
//		Loadable gameLoader = (Loadable)game;
//		Saveable gameSaver = (Saveable)game;
//		
//		Loader loader = new FileLoader(/*gameLoader*/);
//		Saver saver = new FileSaver(/*gameSaver*/);
//		
//		/* <<Views>> */
//		View view = new ConsoleView();
//		
//		/* <<Controller>> */
//		new Controller(game, gameLoader, gameSaver, loader, saver, view).run();
		
		
		/* <<Models>> */
		Loader loader = new FileLoader();
		Saver saver = new FileSaver();
		Game game = new Game(loader, saver);
		
		Playable gamePlayer = (Playable) game;
		Loadable gameLoader = (Loadable) game;
		Saveable gameSaver = (Saveable) game;
		
		/* <<Views>> */
		View view = new ConsoleView();
		
		/* <<Controller>> */
		new Controller(gamePlayer, gameLoader, gameSaver, loader, saver, view).run();
	}

}
