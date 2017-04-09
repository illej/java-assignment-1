package nz.ac.ara.ayreye.theseusandtheminotaur.v5;


public interface Playable {
	void moveTheseus(Direction direction);
	void moveMinotaur();
	
	boolean isBlocked(Direction dir, MyPoint cur, MyPoint dest);
	public Direction findDirection(MyPoint theseus, MyPoint minotaur);
	public Direction findDirection(MyPoint theseus, MyPoint minotaur, String flag);
}
