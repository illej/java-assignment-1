package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

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
	
	private void setCell(MyPoint where, Wall wall, String flag) {
		int rowIndex = where.down();
		int cellIndex = where.across();
		List<Cell> rowCopy = level.get(rowIndex);
		Cell cellCopy = rowCopy.get(cellIndex);
		
		if (flag == "top") {
			cellCopy.top = wall;
		} else if (flag == "left") {
			cellCopy.left = wall;
		}
		
		rowCopy.set(cellIndex, cellCopy);
		level.set(rowIndex, rowCopy);
	}
	
	private void setCell(MyPoint where, Actor actor) {
		int rowIndex = where.down();
		int cellIndex = where.across();
		List<Cell> rowCopy = level.get(rowIndex);
		Cell cellCopy = rowCopy.get(cellIndex);
		
		cellCopy.actor = actor;
		
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
	 * Currently only finds first occurrence..
	 * TODO: ensure only ONE occurrence?
	 * TODO: Make input parameter generic 
	 * if ACTOR enum is converted to classes.
	 * TODO: Rename (to 'find()'?)
	 */
	private MyPoint findActor(Actor actor) {
		MyPoint result = null;
		
		for (int i = 0; i < this.depth; i++) {
			for (int j = 0; j < this.width; j++) {
				MyPoint here = new DefaultPoint(j, i);
				if (this.getCell(here).actor == actor) {
					result = here;
				}
			}
		}
		
		return result;
	}
	
	// [8/8] Tests passing
	public boolean isBlocked(Direction direction, MyPoint current, MyPoint destination) {
		boolean result = false;
		
		if (direction == Direction.LEFT) {
			if (this.getCell(current).left == Wall.SOMETHING) {
				result = true;
			}
		}
		else if (direction == Direction.RIGHT) {
			if (this.getCell(destination).left == Wall.SOMETHING) {
				result = true;
			}
		}
		else if (direction == Direction.UP) {
			if (this.getCell(current).top == Wall.SOMETHING) {
				result = true;
			}
		}
		else if (direction == Direction.DOWN) {
			if (this.getCell(destination).top == Wall.SOMETHING) {
				result = true;
			}
		}
		
		return result;
	}
	
	// OLD (unneeded?)
	public Direction findDirection(MyPoint theseus, MyPoint minotaur) {
		Direction result = null;
		
		if (theseus.across() > minotaur.across()) {
			result = Direction.RIGHT;
		} else if (theseus.across() < minotaur.across()) {
			result = Direction.LEFT;
		} else if (theseus.down() > minotaur.down()) {
			result = Direction.DOWN;
		} else if (theseus.down() < minotaur.down()) {
			result = Direction.UP;
		}
		
		return result;
	}
	
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
		// assertNotNull(this.width);
		return this.width;
	}

	@Override
	public int getDepthDown() {
		// assertNotNull(this.depth);
		return this.depth;
	}

	@Override
	public Wall whatsAbove(MyPoint where) {
		return this.getCell(where).top;
	}

	@Override
	public Wall whatsLeft(MyPoint where) {
		return this.getCell(where).left;
	}

	@Override
	public MyPoint wheresTheseus() {
		return this.findActor(Actor.THESEUS);
	}

	@Override
	public MyPoint wheresMinotaur() {
		return this.findActor(Actor.MINOTAUR);
	}

	@Override
	public MyPoint wheresExit() {
		return this.findActor(Actor.EXIT);
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
	public void addWallAbove(MyPoint where) {
		this.setCell(where, Wall.SOMETHING, "top");
	}

	@Override
	public void addWallLeft(MyPoint where) {
		this.setCell(where, Wall.SOMETHING, "left");
	}

	@Override
	public void addTheseus(MyPoint where) {
		this.setCell(where, Actor.THESEUS);
	}

	@Override
	public void addMinotaur(MyPoint where) {
		this.setCell(where, Actor.MINOTAUR);
	}

	@Override
	public void addExit(MyPoint where) {
		this.setCell(where, Actor.EXIT);
	}

	/*
	 * <<Interface>> Game
	 */
	
	// TODO: Move to CONTROLLER?
	private boolean winState() {
		boolean result = false;
		if (this.wheresTheseus() == this.wheresExit()) {
			result = true;
		}
		return result;
	}
	
	/* NEW_FEATURE 
	 * [20/20] Tests passing
	 */
	@Override
	public void moveTheseus(Direction direction) {
		MyPoint current = this.wheresTheseus();
		MyPoint destination = new DefaultPoint(current.across() + direction.xAdjust,
												current.down() + direction.yAdjust);

		if (!this.isBlocked(direction, current, destination)) {
			this.setCell(current, Actor.NONE);
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
						destination =  new DefaultPoint(
								minotaurAt.across() + horizDir.xAdjust,
								minotaurAt.down() + horizDir.yAdjust))) {
			this.setCell(minotaurAt, Actor.NONE);
			this.addMinotaur(destination);			
		} else if (vertDir != null
				&& !this.isBlocked(
						vertDir,
						minotaurAt,
						destination =  new DefaultPoint(
								minotaurAt.across() + vertDir.xAdjust,
								minotaurAt.down() + vertDir.yAdjust))) {
			this.setCell(minotaurAt, Actor.NONE);
			this.addMinotaur(destination);
		}
	}

}
