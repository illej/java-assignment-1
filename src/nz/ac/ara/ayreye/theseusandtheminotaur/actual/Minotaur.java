package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Minotaur extends Character {

	private MyPoint where;
	
	@Override
	public void setPosition(MyPoint where) {
		this.where = where;
	}

	@Override
	public MyPoint getPosition() {
		return this.where;
	}

	@Override
	public void move(Direction direction) {
		int newX = this.getPosition().across() + direction.xAdjust;
		int newY = this.getPosition().down() + direction.yAdjust;
		this.where = new DefaultPoint(newX, newY);
	}

}
