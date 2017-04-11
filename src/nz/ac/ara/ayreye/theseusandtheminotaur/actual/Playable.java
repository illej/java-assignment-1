package nz.ac.ara.ayreye.theseusandtheminotaur.actual;


public interface Playable {
	void moveTheseus(Direction direction);
	void moveMinotaur();
	
	/*
	 * Temporary for testing
	 * TODO: Move to inside GAME?
	 */
	boolean isBlocked(Direction dir, MyPoint cur, MyPoint dest);
	public Direction findDirection(MyPoint theseus, MyPoint minotaur);
	public Direction findDirection(MyPoint theseus, MyPoint minotaur, String flag);
}
