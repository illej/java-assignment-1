package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Character {
	
	private int x;
	private int y;
	
	public Character(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point where() {
		return new Position(this.x, this.y);
	}
	
	public void move(Direction direction) {
		this.x += direction.xAdjust;
		this.y += direction.yAdjust;
	}
	
}
