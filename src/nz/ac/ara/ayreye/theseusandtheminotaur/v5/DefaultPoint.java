package nz.ac.ara.ayreye.theseusandtheminotaur.v5;

public class DefaultPoint implements MyPoint {
	
	private int x;
	private int y;
	
	public DefaultPoint(int x, int y) {
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
