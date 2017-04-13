package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Position implements Point {
	
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int across() {
		return this.x;
	}

	@Override
	public int down() {
		return this.y;
	}

}
