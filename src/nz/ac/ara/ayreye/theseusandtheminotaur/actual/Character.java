package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public abstract class Character {
	public abstract void setPosition(MyPoint where);
	public abstract MyPoint getPosition();
	public abstract void move(Direction direction);
}
