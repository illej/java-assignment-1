package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Controller {

	protected Playable game;
	protected Loadable gameLoader;
	protected Saveable gameSaver;
	
	public Controller(Playable game, Loadable gameLoader, Saveable gameSaver) {
		this.game = game;
		this.gameLoader = gameLoader;
		this.gameSaver = gameSaver;
	}
	
	public void run() {
		
	}
}
