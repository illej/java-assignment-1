package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.ArrayList;
import java.util.List;
//import static org.junit.Assert.*;

public class Game implements Playable, Loadable, Saveable {

	protected List<List<Cell>> level = new ArrayList<List<Cell>>();
	protected int width = 0;
	protected int depth = 0;

	/*
	 * Private Methods
	 */

	private Cell getCell(MyPoint where) {
		List<Cell> row = level.get(where.down());
		Cell cell = row.get(where.across());
		return cell;
	}

	private void setCellInfo(MyPoint where, Object object, String key) {
		int rowIndex = where.down();
		int cellIndex = where.across();
		List<Cell> rowCopy = level.get(rowIndex);
		Cell cellCopy = rowCopy.get(cellIndex);

		cellCopy.set(key, object);

		rowCopy.set(cellIndex, cellCopy);
		level.set(rowIndex, rowCopy);
	}

	private void build() {
		for (int i = 0; i < this.depth; i++) {
			List<Cell> row = new ArrayList<Cell>();
			for (int j = 0; j < this.width; j++) {
				row.add(new Cell());
			}
			level.add(row);
		}
	}

	/*
	 * Currently only finds first occurrence.. TODO: ensure only ONE occurrence?
	 */
	public MyPoint findObject(Object object, String key) {
		MyPoint result = null;
		List<MyPoint> points = new ArrayList<MyPoint>();
		
		for (int i = 0; i < this.depth; i++) {
			for (int j = 0; j < this.width; j++) {
				MyPoint here = new DefaultPoint(j, i);
				if (this.getCell(here).get(key) == object) {
					result = here;
					points.add(result);
				}
			}
		}
		
		if (points.size() > 1) {
			System.out.println("Too many " + object + " in LEVEL.");
		}
		
		return result;
	}

	// [8/8] Tests passing
	public boolean isBlocked(Direction direction, MyPoint current, MyPoint destination) {
		boolean result = false;

		if (direction == Direction.LEFT) {
			if ((Wall) this.getCell(current).get("left") == Wall.SOMETHING) {
				result = true;
			}
		} else if (direction == Direction.RIGHT) {
			if ((Wall) this.getCell(destination).get("left") == Wall.SOMETHING) {
				result = true;
			}
		} else if (direction == Direction.UP) {
			if ((Wall) this.getCell(current).get("top") == Wall.SOMETHING) {
				result = true;
			}
		} else if (direction == Direction.DOWN) {
			if ((Wall) this.getCell(destination).get("top") == Wall.SOMETHING) {
				result = true;
			}
		}

		return result;
	}

	// [8/8] Tests passing
	// TODO: Polymorph?
	public Direction findDirection(MyPoint theseus, MyPoint minotaur, String flag) {
		Direction result = null;

		if (flag == "horizontal") {
			if (theseus.across() > minotaur.across()) {
				result = Direction.RIGHT;
			} else if (theseus.across() < minotaur.across()) {
				result = Direction.LEFT;
			}
		} else if (flag == "vertical") {
			if (theseus.down() > minotaur.down()) {
				result = Direction.DOWN;
			} else if (theseus.down() < minotaur.down()) {
				result = Direction.UP;
			}
		}

		return result;
	}

	/*
	 * <<Interface>> Saveable
	 */

	@Override
	public int getWidthAcross() {
		return this.level.get(0).size(); // this.width;
	}

	@Override
	public int getDepthDown() {
		return this.level.size(); // this.depth;
	}

	@Override
	public Wall whatsAbove(MyPoint where) {
		return (Wall) this.getCell(where).get("top");
	}

	@Override
	public Wall whatsLeft(MyPoint where) {
		return (Wall) this.getCell(where).get("left");
	}

	@Override
	public MyPoint wheresTheseus() {
		// TODO: Change to 'getCell' ?
		return this.findObject(Actor.THESEUS, "character");
	}

	@Override
	public MyPoint wheresMinotaur() {
		// TODO: Change to 'getCell' ?
		return this.findObject(Actor.MINOTAUR, "character");
	}

	@Override
	public MyPoint wheresExit() {
		// TODO: Change to 'getCell' ?
		return this.findObject(Objective.EXIT, "objective");
	}

	/*
	 * <<Interface>> Loadable
	 */

	@Override
	public int setWidthAcross(int widthAcross) {
		this.width = widthAcross;

		if (this.depth > 0 && this.width > 0) {
			this.build();
		} // TODO: else throw exception?

		return this.width; // ?
	}

	@Override
	public int setDepthDown(int depthDown) {
		this.depth = depthDown;

		if (this.width > 0 && this.depth > 0) {
			this.build();
		} // TODO: else throw exception

		return this.depth; // ?
	}

	@Override
	public void addWallAbove(MyPoint where) {
		this.setCellInfo(where, Wall.SOMETHING, "top");
	}

	@Override
	public void addWallLeft(MyPoint where) {
		this.setCellInfo(where, Wall.SOMETHING, "left");
	}

	@Override
	public void addTheseus(MyPoint where) {
		// TODO: Change to 'Character base class type'
		// TODO: Add error checking to ensure only 1 theseus
		// 			exists in the level at all times.
		
		MyPoint check = this.findObject(Actor.THESEUS, "character");
		if (check != null) {
			this.setCellInfo(check, Actor.NONE, "character");
			System.out.println("found a theseus, but cloned him, and killed the original.");
		}
		this.setCellInfo(where, Actor.THESEUS, "character");
	}

	@Override
	public void addMinotaur(MyPoint where) {
		// TODO: Change to 'Character base class type'
		this.setCellInfo(where, Actor.MINOTAUR, "character");
	}

	@Override
	public void addExit(MyPoint where) {
		this.setCellInfo(where, Objective.EXIT, "objective");
	}

	/*
	 * <<Interface>> Playable
	 */

	/*
	 * [20/20] Tests passing
	 */
	@Override
	public void moveTheseus(Direction direction) {
		MyPoint current = this.wheresTheseus();
		MyPoint destination = new DefaultPoint(
				current.across() + direction.xAdjust,
				current.down() + direction.yAdjust);

		if (!this.isBlocked(direction, current, destination)) {
			this.setCellInfo(current, Actor.NONE, "character");
			this.addTheseus(destination);
		} else {
			System.out.println("blocked! ~:(");
		}
	}

	@Override
	public void moveMinotaur() {
		MyPoint theseusAt = this.wheresTheseus();
		MyPoint minotaurAt = this.wheresMinotaur();
		MyPoint destination;

		Direction horizDir = this.findDirection(theseusAt, minotaurAt, "horizontal");
		Direction vertDir = this.findDirection(theseusAt, minotaurAt, "vertical");

		if (horizDir != null
				&& !this.isBlocked(
						horizDir, 
						minotaurAt,
						destination = new DefaultPoint(
								minotaurAt.across() + horizDir.xAdjust,
								minotaurAt.down() + horizDir.yAdjust))) {
			this.setCellInfo(minotaurAt, Actor.NONE, "character");
			this.addMinotaur(destination);
		} else if (vertDir != null
				&& !this.isBlocked(
						vertDir, 
						minotaurAt,
						destination = new DefaultPoint(
								minotaurAt.across() + vertDir.xAdjust,
								minotaurAt.down() + vertDir.yAdjust))) {
			this.setCellInfo(minotaurAt, Actor.NONE, "character");
			this.addMinotaur(destination);
		}
	}

}
