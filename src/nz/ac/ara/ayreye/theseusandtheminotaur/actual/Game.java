package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import static org.junit.Assert.*;

public class Game implements Playable, Loadable, Saveable, Saver, Loader {

	private Loader loader;
	private Saver saver;
	
	private List<List<Cell>> level = new ArrayList<List<Cell>>();
	private int width = 0;
	private int depth = 0;
	
	public Game(Loader loader, Saver saver) {
		// Could still receive these from the controller?
		this.loader = new FileLoader(/*this*/);
		this.saver = new FileSaver(/*this*/);
	}

	/*
	 * Private Methods
	 */

	private Cell getCell(Point where) {
		List<Cell> row = level.get(where.down());
		Cell cell = row.get(where.across());
		return cell;
	}

	private void setCellInfo(Point where, Object object, String key) {
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
	public Point findObject(Object object, String key) {
		Point result = null;
		List<Point> points = new ArrayList<Point>();
		
		for (int i = 0; i < this.depth; i++) {
			for (int j = 0; j < this.width; j++) {
				Point here = new Position(j, i);
				if (this.getCell(here).get(key) == object) {
					result = here;
					points.add(result);
				}
			}
		}
		
		// TODO: Replace with an Exception??
		if (points.size() > 1) {
			System.out.println("Too many " + object + " in LEVEL.");
		}
		
		return result;
	}

	// [8/8] Tests passing
	// TODO: Polymorph?
	public boolean isBlocked(Direction direction, Point current, Point destination) {
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
	public Direction findDirection(Point theseus, Point minotaur, String flag) {
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
	public Wall whatsAbove(Point where) {
		return (Wall) this.getCell(where).get("top");
	}

	@Override
	public Wall whatsLeft(Point where) {
		return (Wall) this.getCell(where).get("left");
	}

	@Override
	public Point wheresTheseus() {
		return this.findObject(Part.THESEUS, "character");
	}

	@Override
	public Point wheresMinotaur() {
		return this.findObject(Part.MINOTAUR, "character");
	}

	@Override
	public Point wheresExit() {
		return this.findObject(Part.EXIT, "objective");
	}

	/*
	 * <<Interface>> Loadable
	 */

	@Override
	public int setWidthAcross(int widthAcross) {
		this.width = widthAcross;

		if (this.depth > 0
				&& this.width > 0) {
			this.build();
		} // TODO: else throw exception?

		return this.width; // ?
	}

	@Override
	public int setDepthDown(int depthDown) {
		this.depth = depthDown;

		if (this.width > 0
				&& this.depth > 0) {
			this.build();
		} // TODO: else throw exception
		
		return this.depth; // ?
	}

	@Override
	public void addWallAbove(Point where) {
		this.setCellInfo(where, Wall.SOMETHING, "top");
	}

	@Override
	public void addWallLeft(Point where) {
		this.setCellInfo(where, Wall.SOMETHING, "left");
	}

	@Override
	public void addTheseus(Point where) {
		/* 
		 * TODO: Add error checking to ensure only 1 theseus 
		 * exists in the level at all times.
		 */
		
		Point check = this.findObject(Part.THESEUS, "character");
		if (check != null) {
			this.setCellInfo(check, Part.NONE, "character");
			System.out.println("found a theseus, but cloned and killed the original.");
			// TODO: throw new Exception();
		}
		
		this.setCellInfo(where, Part.THESEUS, "character");
	}

	@Override
	public void addMinotaur(Point where) {
		this.setCellInfo(where, Part.MINOTAUR, "character");
	}

	@Override
	public void addExit(Point where) {
		this.setCellInfo(where, Part.EXIT, "objective");
	}

	/*
	 * <<Interface>> Playable
	 */

	/*
	 * [20/20] Tests passing
	 */
	@Override
	public void moveTheseus(Direction direction) {
		Point current = this.wheresTheseus();
		Point destination = 
				new Position(
					current.across() + direction.xAdjust,
					current.down() + direction.yAdjust);

		if (!this.isBlocked(direction, current, destination)) {
			this.setCellInfo(current, Part.NONE, "character");
			this.addTheseus(destination);
		} else {
			System.out.println("blocked! ~:(");
		}
	}

	@Override
	public void moveMinotaur() {
		int moves = 2;
		while (moves > 0) {
			Point theseusAt = this.wheresTheseus();
			Point minotaurAt = this.wheresMinotaur();
			Point destination;
			
			Direction horizDir = this.findDirection(theseusAt, minotaurAt, "horizontal");
			Direction vertDir = this.findDirection(theseusAt, minotaurAt, "vertical");
	
			if (horizDir != null
					&& !this.isBlocked(
							horizDir, 
							minotaurAt,
							destination = new Position(
									minotaurAt.across() + horizDir.xAdjust,
									minotaurAt.down() + horizDir.yAdjust))) {
				this.setCellInfo(minotaurAt, Part.NONE, "character");
				this.addMinotaur(destination);
			} else if (vertDir != null
					&& !this.isBlocked(
							vertDir, 
							minotaurAt,
							destination = new Position(
									minotaurAt.across() + vertDir.xAdjust,
									minotaurAt.down() + vertDir.yAdjust))) {
				this.setCellInfo(minotaurAt, Part.NONE, "character");
				this.addMinotaur(destination);
			}
			moves--;
		}
	}

	/*
	 * <<Interface>> Loader
	 */
	
	@Override
	public void load(Loadable gameLoader, String filename) {
		this.loader.load(gameLoader, filename);
	}

	/*
	 * <<Interface>> Saver
	 */

	@Override
	public void save(Saveable gameSaver) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(Saveable gameSaver, String fileName) {
		this.saver.save(gameSaver, fileName);
	}

	@Override
	public void save(Saveable gameSaver, String fileName, String levelName) {
		// TODO Auto-generated method stub
		
	}

}
