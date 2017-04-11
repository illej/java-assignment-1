package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Main {

	public static void main(String[] args) {

		/* <<Models>> */
		Playable game = new Game();
		Loadable gameLoader = (Loadable)game;
		Saveable gameSaver = (Saveable)game;
		
		/* <<Views>> */
		
		/* <<Controller>> */
		new Controller(game, gameLoader, gameSaver).run();
	}

}
