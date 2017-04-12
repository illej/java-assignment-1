package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public class Theseus extends Character {

	protected MyPoint where;
	
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
		int newX = this.where.across() + direction.xAdjust;
		int newY = this.where.down() + direction.yAdjust;
		this.where = new DefaultPoint(newX, newY);
	}

}
