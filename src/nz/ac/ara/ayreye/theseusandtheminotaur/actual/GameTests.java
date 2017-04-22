package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GameTests {

	public static Playable game;
	public static Loadable gameLoader;
	public static Saveable gameSaver;
	public static Loader loader;
	public static Saver saver;
	
	@Before // Run before every test
	public void setUp() {
		loader = new FileLoader();
		saver = new FileSaver();
		game = new Game(loader, saver);
		gameLoader = (Loadable)game;
		gameSaver = (Saveable)game;
		
		gameLoader.setDepthDown(7);
		gameLoader.setWidthAcross(7);
		
		/*
		 * Set basic boundary
		 */
		
		for (int i = 0; i < gameSaver.getWidthAcross(); i++) {
			Point point = new Pointer(i, 0);
			gameLoader.addWallAbove(point);
		}
		for (int j = 0; j < gameSaver.getDepthDown(); j++) {
			Point point = new Pointer(0, j);
			gameLoader.addWallLeft(point);
		}
		for (int k = 0; k < gameSaver.getWidthAcross(); k++) {
			Point point = new Pointer(k, gameSaver.getDepthDown() - 1);
			gameLoader.addWallAbove(point);
		}
		for (int l = 0; l < gameSaver.getDepthDown(); l++) {
			Point point = new Pointer(gameSaver.getWidthAcross() - 1, l);
			gameLoader.addWallLeft(point);
		}
	}
	
	/*
	 * isBlocked()
	 */
	
	@Test
	public void test01_isBlocked_moveUp_true() {
		Point current = new Pointer(3, 5);
		Point destination = new Pointer(3, 4);
		
		Point wallAt = new Pointer(3, 5);
		gameLoader.addWallAbove(wallAt);
		
		boolean expected = true;
		boolean actual = game.isBlocked(Direction.UP, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void test02_isBlocked_moveUp_false() {
		Point current = new Pointer(3, 5);
		Point destination = new Pointer(3, 4);
		
		boolean expected = false;
		boolean actual = game.isBlocked(Direction.UP, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveRight_true() {
		Point current = new Pointer(3, 3);
		Point destination = new Pointer(4, 3);
		
		Point wallAt = new Pointer(4, 3);
		gameLoader.addWallLeft(wallAt);
		
		boolean expected = true;
		boolean actual = game.isBlocked(Direction.RIGHT, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveRight_false() {
		Point current = new Pointer(3, 3);
		Point destination = new Pointer(4, 3);
		
		boolean expected = false;
		boolean actual = game.isBlocked(Direction.RIGHT, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveDown_true() {
		Point current = new Pointer(3, 3);
		Point destination = new Pointer(3, 4);
		
		Point wallAt = new Pointer(3, 4);
		gameLoader.addWallAbove(wallAt);
		
		boolean expected = true;
		boolean actual = game.isBlocked(Direction.DOWN, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveDown_false() {
		Point current = new Pointer(3, 3);
		Point destination = new Pointer(3, 4);
		
		boolean expected = false;
		boolean actual = game.isBlocked(Direction.DOWN, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveLeft_true() {
		Point current = new Pointer(3, 3);
		Point destination = new Pointer(2, 3);
		
		Point wallAt = new Pointer(3, 3);
		gameLoader.addWallLeft(wallAt);
		
		boolean expected = true;
		boolean actual = game.isBlocked(Direction.LEFT, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveLeft_false() {
		Point current = new Pointer(3, 3);
		Point destination = new Pointer(2, 3);
		
		boolean expected = false;
		boolean actual = game.isBlocked(Direction.LEFT, current, destination);
		
		assertEquals(expected, actual);	
	}
	
	/*
	 * findDirection
	 */
	
	@Test
	public void findDirection_tNorthMSouth_dirUp() {
		Point theseusAt = new Pointer(3, 0);
		gameLoader.addTheseus(theseusAt);
		
		Point minotaurAt = new Pointer(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.UP;
		Direction actual = game.findDirection(theseusAt, minotaurAt, "vertical");
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tSouthMNorth_dirDown() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		
		Point minotaurAt = new Pointer(3, 0);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.DOWN;
		Direction actual = game.findDirection(theseusAt, minotaurAt, "vertical");
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tEastMWest_dirRight() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		
		Point minotaurAt = new Pointer(0, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.RIGHT;
		Direction actual = game.findDirection(theseusAt, minotaurAt, "horizontal");
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tWestMEast_dirLeft() {
		Point theseusAt = new Pointer(0, 3);
		gameLoader.addTheseus(theseusAt);
		
		Point minotaurAt = new Pointer(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.LEFT;
		Direction actual = game.findDirection(theseusAt, minotaurAt, "horizontal");
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tNorthEast_dirRight() {
		Point theseusAt = new Pointer(4, 2);
		gameLoader.addTheseus(theseusAt);
		
		Point minotaurAt = new Pointer(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.RIGHT;
		Direction actual = game.findDirection(theseusAt, minotaurAt, "horizontal");
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tNorthWest_dirLeft() {
		Point theseusAt = new Pointer(2, 2);
		gameLoader.addTheseus(theseusAt);
		
		Point minotaurAt = new Pointer(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.LEFT;
		Direction actual = game.findDirection(theseusAt, minotaurAt, "horizontal");
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tSouthEast_dirRight() {
		Point theseusAt = new Pointer(4, 4);
		gameLoader.addTheseus(theseusAt);
		
		Point minotaurAt = new Pointer(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.RIGHT;
		Direction actual = game.findDirection(theseusAt, minotaurAt, "horizontal");
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tSouthWest_dirLeft() {
		Point theseusAt = new Pointer(2, 4);
		gameLoader.addTheseus(theseusAt);
		
		Point minotaurAt = new Pointer(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.LEFT;
		Direction actual = game.findDirection(theseusAt, minotaurAt, "horizontal");
		
		assertEquals(expected, actual);
	}
	
	/*
	 * moveMinotaur
	 */
	
	// 1. up 1
	@Test
	public void moveMinotaurUp_oneSpaceBetween_upTwo_theseusDead() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 1);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 1);
		Point actual = gameSaver.wheresMinotaur();
		
		// Find better solution??
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 2. up 2
	@Test
	public void moveMinotaurUp_twoSpacesBetween_upTwo() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 0);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 1);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 3. up 3
	@Test
	public void moveMinotaurUp_threeSpacesBetween_upTwo() {
		Point whereMin = new Pointer(3, 4);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 0);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 4. right 1
	@Test
	public void moveMinotaurRight_oneSpaceBetween_rightTwo_theseusDead() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(5, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(5, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 5. right 2
	@Test
	public void moveMinotaurRight_twoSpacesBetween_rightTwo() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(6, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(5, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 5. a) right 2 
	@Test
	public void moveMinotaur_rightTwice_0_5() {
		Point whereMin = new Pointer(0, 5);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(2, 4);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(2, 5);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 6. right 3
	@Test
	public void moveMinotaurRight_threeSpacesBetween_rightTwo() {
		Point whereMin = new Pointer(2, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(6, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 7. down 1
	@Test
	public void moveMinotaurDown_oneSpaceBetween_downTwo_theseusDead() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 5);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 5);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 8. down 2
	@Test
	public void moveMinotaurDown_twoSpacesBetween_downTwo() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 6);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 5);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 9. down 3
	@Test
	public void moveMinotaurDown_threeSpacesBetween_downTwo() {
		Point whereMin = new Pointer(3, 2);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 6);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 10. left 1
	@Test
	public void moveMinotaurLeft_oneSpaceBetween_leftTwo_theseusDead() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 11. left 2
	@Test
	public void moveMinotaurLeft_twoSpacesBetween_leftTwo() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(0, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 12. left 3
	@Test
	public void moveMinotaurLeft_threeSpacesBetween_leftTwo() {
		Point whereMin = new Pointer(4, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(0, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	/*
	 * Using walls
	 */
	
	// 13. up 1 with wall
	@Test
	public void moveMinotaurUp_oneSpaceBetweenButWall_upNone() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 1);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 3);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 14. up 2 with wall
	@Test
	public void moveMinotaurUp_twoSpacesBetweenButWall_upOne() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 0);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 2);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 15. up 3 with wall
	@Test
	public void moveMinotaurUp_threeSpacesBetweenButWall_upTwo() {
		Point whereMin = new Pointer(3, 4);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 0);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 2);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 16. right 1 with wall
	@Test
	public void moveMinotaurRight_oneSpaceBetweenButWall_rightNone() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(5, 3);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(4, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 17. right 2 with wall
	@Test
	public void moveMinotaurRight_oneSpaceBetweenButWall_rightOne() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(6, 3);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(5, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 18. right 3 with wall
	@Test
	public void moveMinotaurRight_oneSpaceBetweenButWall_rightTwo() {
		Point whereMin = new Pointer(2, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(6, 3);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(5, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 19. down 1 with wall
	@Test
	public void moveMinotaurDown_oneSpaceBetweenButWall_downNone() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 5);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 4);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 20. down 2 with wall
	@Test
	public void moveMinotaurDown_twoSpaceBetweenButWall_downOne() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 6);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 5);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 21. down 3 with wall
	@Test
	public void moveMinotaurDown_threeSpaceBetweenButWall_downTwo() {
		Point whereMin = new Pointer(3, 2);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 6);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 5);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 22. left 1 with wall
	@Test
	public void moveMinotaurDown_oneSpaceBetweenButWall_leftNone() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 3);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 23. left 2 with wall
	@Test
	public void moveMinotaurDown_twoSpacesBetweenButWall_leftOne() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(0, 3);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(2, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 24. left 3 with wall
	@Test
	public void moveMinotaurDown_threeSpacesBetweenButWall_leftTwo() {
		Point whereMin = new Pointer(4, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(0, 3);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(2, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 25. no7
	@Test
	public void moveMinotaur_RightDown_wall_downRight() {
		Point whereMin = new Pointer(0, 0);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(2, 1);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(1, 0);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 1);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 26. no8
	@Test
	public void moveMinotaur_RightDown_walls_downDown() {
		Point whereMin = new Pointer(0, 0);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(2, 1);
		gameLoader.addTheseus(whereThes);
		Point whereWall1 = new Pointer(1, 0);
		gameLoader.addWallLeft(whereWall1);
		Point whereWall2 = new Pointer(1, 1);
		gameLoader.addWallLeft(whereWall2);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(0, 1);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 27. no9
	@Test
	public void moveMinotaur_RightDown_rightDown() {
		Point whereMin = new Pointer(0, 0);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 2);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 1);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 28. no10
	@Test
	public void moveMinotaur_RightDown_wall_rightNone() {
		Point whereMin = new Pointer(0, 0);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 2);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(1, 1);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 0);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 29. no11
	@Test
	public void moveMinotaur_RightDown_wall2_downRight() {
		Point whereMin = new Pointer(0, 0);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 2);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(1, 0);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 1);
		Point actual = gameSaver.wheresMinotaur(); // 0, 0
		
		// 0, 0
		System.out.println("top:" + gameSaver.whatsAbove(whereMin));
		System.out.println("left:" + gameSaver.whatsLeft(whereMin));
		
		// 0, 1
		Point zeroOne = new Pointer(0, 1);
		System.out.println("top:" + gameSaver.whatsAbove(zeroOne));
		System.out.println("left:" + gameSaver.whatsLeft(zeroOne));
		System.out.println("blocked?:" + game.isBlocked(Direction.DOWN, whereMin, zeroOne));
		
		// 0, 2
		Point zeroTwo = new Pointer(0, 2);
		System.out.println("top:" + gameSaver.whatsAbove(zeroTwo));
		System.out.println("left:" + gameSaver.whatsLeft(zeroTwo));
		System.out.println("blocked?:" + game.isBlocked(Direction.DOWN, zeroOne, zeroTwo));
		
		System.out.println("ex:" + expected.across() + ", " + expected.across());
		System.out.println("act:" + actual.down() + ", " + actual.down());
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 30. no6
	@Test
	public void moveMinotaur_RightDown_wallCorner_rightNone() {
		Point whereMin = new Pointer(0, 0);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(2, 1);
		gameLoader.addTheseus(whereThes);
		Point whereWall1 = new Pointer(2, 0);
		gameLoader.addWallLeft(whereWall1);
		Point whereWall2 = new Pointer(1, 1);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 0);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 31. no12
	@Test
	public void moveMinotaur_RightDown_2walls_downDown() {
		Point whereMin = new Pointer(0, 0);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 2);
		gameLoader.addTheseus(whereThes);
		Point whereWall1 = new Pointer(1, 0);
		gameLoader.addWallLeft(whereWall1);
		Point whereWall2 = new Pointer(1, 1);
		gameLoader.addWallLeft(whereWall2);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(0, 2);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 32. no13
	@Test
	public void moveMinotaur_RightDown_2wallsCorner_downNone() {
		Point whereMin = new Pointer(0, 0);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 2);
		gameLoader.addTheseus(whereThes);
		Point whereWall1 = new Pointer(1, 0);
		gameLoader.addWallLeft(whereWall1);
		Point whereWall2 = new Pointer(1, 1);
		gameLoader.addWallLeft(whereWall2);
		Point whereWall3 = new Pointer(0, 2);
		gameLoader.addWallAbove(whereWall3);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(0, 1);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}

	/*
	 * DOWN-LEFT M @ 3, 3
	 * 
	 */
	
	// 33. no7
	@Test
	public void moveMinotaur_downLeft_wall_leftDown() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(2, 5);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 4);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(2, 4);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 34. no8
	@Test
	public void moveMinotaur_downLeft_2walls_leftNone() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(2, 5);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 4);
		gameLoader.addWallAbove(whereWall);
		Point whereWall2 = new Pointer(2, 4);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 35. no9
	@Test
	public void moveMinotaur_downLeft_leftLeft() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 4);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 36. no10
	@Test
	public void moveMinotaur_downLeft_wall_leftLeft() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 4);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 4);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 37. no11
	@Test
	public void moveMinotaur_downLeft_wall_leftLeft2() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 4);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 4);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 38. no6
	@Test
	public void moveMinotaur_downLeft_wallCorner_leftLeft() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(2, 5);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(3, 4);
		gameLoader.addWallLeft(whereWall);
		Point whereWall2 = new Pointer(3, 5);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(2, 4);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 39. no12
	@Test
	public void moveMinotaur_downLeft_2walls_leftLeft() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 4);
		gameLoader.addTheseus(whereThes);
		Point whereWall = new Pointer(2, 4);
		gameLoader.addWallAbove(whereWall);
		Point whereWall2 = new Pointer(3, 4);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(1, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 40. no13
	@Test
	public void moveMinotaur_downLeft_2wallsCorner_leftNone() {
		Point whereMin = new Pointer(3, 3);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(1, 4);
		gameLoader.addTheseus(whereThes);
		Point whereWall0 = new Pointer(2, 3);
		gameLoader.addWallLeft(whereWall0);
		Point whereWall1 = new Pointer(2, 4);
		gameLoader.addWallAbove(whereWall1);
		Point whereWall2 = new Pointer(3, 4);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	/*
	 * Others
	 */
	
//	@Test
//	public void moveMinotaur_rightTwice_0_6() {
//		Point whereMin = new Point(0, 6);
//		gameLoader.addMinotaur(whereMin);
//		Point whereThes = new Point(1, 4);
//		gameLoader.addTheseus(whereThes);
//		
//		game.moveMinotaur();
//		
//		Point expected = new Point(2, 6);
//		Point actual = gameSaver.wheresMinotaur();
//		
//		assertEquals(expected, actual);
//	}
	
	// right->up
	@Test
	public void moveMinotaur_rightUp() {
		Point whereMin = new Pointer(2, 4);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(3, 2);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// left->up->death
	@Test
	public void moveMinotaur_leftUp_death() {
		Point whereMin = new Pointer(1, 1);
		gameLoader.addMinotaur(whereMin);
		Point whereThes = new Pointer(0, 0);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		
		Point expected = new Pointer(0, 0);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	/*
	 * moveTHESEUS tests
	 */
	
	// 1
	@Test
	public void moveTheseus_up_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 2
	@Test
	public void moveTheseus_right_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 3
	@Test
	public void moveTheseus_down_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 4
	@Test
	public void moveTheseus_left_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 5
	@Test
	public void moveTheseus_up_wallAbove_fail() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallTop = new Pointer(3, 3);
		gameLoader.addWallAbove(wallTop);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 6
	@Test
	public void moveTheseus_up_wallLeft_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallLeft = new Pointer(3, 3);
		gameLoader.addWallLeft(wallLeft);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 7
	@Test
	public void moveTheseus_up_wallRight_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallRight = new Pointer(4, 3);
		gameLoader.addWallLeft(wallRight);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 8
	@Test
	public void moveTheseus_up_wallDown_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallDown = new Pointer(3, 4);
		gameLoader.addWallAbove(wallDown);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 9
	@Test
	public void moveTheseus_right_wallAbove_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallTop = new Pointer(3, 3);
		gameLoader.addWallAbove(wallTop);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 10
	@Test
	public void moveTheseus_right_wallLeft_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallLeft = new Pointer(3, 3);
		gameLoader.addWallLeft(wallLeft);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 11
	@Test
	public void moveTheseus_right_wallRight_fail() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallRight = new Pointer(4, 3);
		gameLoader.addWallLeft(wallRight);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 12
	@Test
	public void moveTheseus_right_wallDown_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallDown = new Pointer(3, 4);
		gameLoader.addWallAbove(wallDown);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 13
	@Test
	public void moveTheseus_down_wallAbove_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallTop = new Pointer(3, 3);
		gameLoader.addWallAbove(wallTop);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 14
	@Test
	public void moveTheseus_down_wallLeft_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallLeft = new Pointer(3, 3);
		gameLoader.addWallLeft(wallLeft);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 15
	@Test
	public void moveTheseus_down_wallRight_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallRight = new Pointer(4, 3);
		gameLoader.addWallLeft(wallRight);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 16
	@Test
	public void moveTheseus_down_wallDown_fail() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallDown = new Pointer(3, 4);
		gameLoader.addWallAbove(wallDown);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 17
	@Test
	public void moveTheseus_left_wallAbove_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallTop = new Pointer(3, 3);
		gameLoader.addWallAbove(wallTop);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 18
	@Test
	public void moveTheseus_left_wallLeft_fail() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallLeft = new Pointer(3, 3);
		gameLoader.addWallLeft(wallLeft);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 19
	@Test
	public void moveTheseus_left_wallRight_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallRight = new Pointer(4, 3);
		gameLoader.addWallLeft(wallRight);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 20
	@Test
	public void moveTheseus_left_wallDown_success() {
		Point theseusAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		Point wallDown = new Pointer(3, 4);
		gameLoader.addWallAbove(wallDown);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	/*
	 * addWall one space away
	 */
	
	// 21
	@Test
	public void moveTheseus_up_oneSpace_wallAbove_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAbove = new Pointer(3, 2);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallAbove(wallAbove);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 22
	@Test
	public void moveTheseus_up_oneSpace_wallRight_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(4, 2);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallLeft(wallAt);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 23 already done?
	@Test
	public void moveTheseus_up_oneSpace_wallDown_fail() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallAbove(wallAt);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 24
	@Test
	public void moveTheseus_up_oneSpace_wallLeft_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(3, 2);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallLeft(wallAt);
		
		game.moveTheseus(Direction.UP);
		
		Point expected = new Pointer(3, 2);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 25
	@Test
	public void moveTheseus_right_oneSpace_wallUp_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(4, 3);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallAbove(wallAt);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 26
	@Test
	public void moveTheseus_right_oneSpace_wallRight_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(5, 3);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallLeft(wallAt);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 27
	@Test
	public void moveTheseus_right_oneSpace_wallDown_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(4, 4);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallAbove(wallAt);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(4, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 28 repeat?
	@Test
	public void moveTheseus_right_oneSpace_wallLeft_fail() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(4, 3);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallLeft(wallAt);
		
		game.moveTheseus(Direction.RIGHT);
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 29 repeat?
	@Test
	public void moveTheseus_down_oneSpace_wallUp_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(3, 4);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallAbove(wallAt);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 30
	@Test
	public void moveTheseus_down_oneSpace_wallRight_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(4, 4);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallLeft(wallAt);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 31
	@Test
	public void moveTheseus_down_oneSpace_wallDown_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(3, 5);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallAbove(wallAt);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 32
	@Test
	public void moveTheseus_down_oneSpace_wallLeft_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(3, 4);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallLeft(wallAt);
		
		game.moveTheseus(Direction.DOWN);
		
		Point expected = new Pointer(3, 4);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 33
	@Test
	public void moveTheseus_left_oneSpace_wallUp_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(2, 3);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallAbove(wallAt);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 34 repeat?
	@Test
	public void moveTheseus_left_oneSpace_wallRight_fail() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(3, 3);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallLeft(wallAt);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(3, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 35
	@Test
	public void moveTheseus_left_oneSpace_wallDown_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(2, 4);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallAbove(wallAt);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	// 36 
	@Test
	public void moveTheseus_left_oneSpace_wallLeft_success() {
		Point theseusAt = new Pointer(3, 3);
		Point wallAt = new Pointer(2, 3);
		gameLoader.addTheseus(theseusAt);
		gameLoader.addWallLeft(wallAt);
		
		game.moveTheseus(Direction.LEFT);
		
		Point expected = new Pointer(2, 3);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	/*
	 * addExit
	 */
	
	@Test
	public void addExit_4_4() {
		Point exit =  new Pointer(4, 4);
		gameLoader.addExit(exit);
		
		Point expected = new Pointer(4, 4);
		Point actual = gameSaver.wheresExit();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	/*
	 * addWall
	 */
	
	// 1
	@Test
	public void addWallAbove_exists() {
		Point wallAt = new Pointer(2, 2);
		gameLoader.addWallAbove(wallAt);
		
		Wall expected = Wall.SOMETHING;
		Wall actual = gameSaver.whatsAbove(new Pointer(2, 2));
		
		assertEquals(expected, actual);
	}
	
	// 2
	@Test
	public void addWallLeft_exists() {
		Point wallAt = new Pointer(3, 3);
		gameLoader.addWallLeft(wallAt);
		
		Wall expected = Wall.SOMETHING;
		Wall actual = gameSaver.whatsLeft(new Pointer(3, 3));
		
		assertEquals(expected, actual);
	}
	
	/*
	 * exception Tests
	 */
	
	/*@Test (expected = Exception.class)
	public void test82_twoTheseus_gettingrekt() throws Exception {
		MyPoint firstTheseus = new DefaultPoint(3, 3);
		gameLoader.addTheseus(firstTheseus);
		
		MyPoint secondTheseus = new DefaultPoint(4, 4);
		gameLoader.addTheseus(secondTheseus);
		
		MyPoint expected = new DefaultPoint(4, 4);
		MyPoint actual = game.findObject(Actor.THESEUS, "character");
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	@Test (expected = Exception.class)
	public void test83_twoTheseus_throwException() throws Exception {
		MyPoint firstTheseus = new DefaultPoint(3, 3);
		gameLoader.addTheseus(firstTheseus);
		
		MyPoint secondTheseus = new DefaultPoint(4, 4);
		gameLoader.addTheseus(secondTheseus);
	}
	
	@Test  //WORKS! When safety code in .addTheseus() is removed
	public void test84_findObject_twoTheseus_tooMany() {
		MyPoint firstTheseus = new DefaultPoint(3, 3);
		gameLoader.addTheseus(firstTheseus);
		
		MyPoint secondTheseus = new DefaultPoint(4, 4);
		gameLoader.addTheseus(secondTheseus);
		
		game.findObject(Actor.THESEUS, "character");
	}*/
	
	/*
	 * Load Tests
	 */
	
	@Test
	public void load_level_mPos_correct() {
		Game game = new Game(new FileLoader(), new FileSaver());
		//Playable gamePlayer = (Playable) game;
		Loadable gameLoader = (Loadable) game;
		Saveable gameSaver = (Saveable) game;
		Loader loader = (Loader) game;
		//Saver saver = (Saver) game;
		
		loader.load(gameLoader, "level.txt");
		
		// "U=xxxo,oxox,oxox,xxxo;L=xxxo,oooo,oxoo,xoxo;M=01,0;T=1,2;E=3,1:"
		Point expected = new Pointer(1, 0);
		Point actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	@Test
	public void load_level_tPos_correct() {
		Game game = new Game(new FileLoader(), new FileSaver());
		//Playable gamePlayer = (Playable) game;
		Loadable gameLoader = (Loadable) game;
		Saveable gameSaver = (Saveable) game;
		Loader loader = (Loader) game;
		//Saver saver = (Saver) game;
		
		loader.load(gameLoader, "level.txt");
		
		// "U=xxxo,oxox,oxox,xxxo;L=xxxo,oooo,oxoo,xoxo;M=01,0;T=1,2;E=3,1:"
		Point expected = new Pointer(1, 2);
		Point actual = gameSaver.wheresTheseus();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	@Test
	public void load_level_ePos_correct() {
		Game game = new Game(new FileLoader(), new FileSaver());
		//Playable gamePlayer = (Playable) game;
		Loadable gameLoader = (Loadable) game;
		Saveable gameSaver = (Saveable) game;
		Loader loader = (Loader) game;
		//Saver saver = (Saver) game;
		
		loader.load(gameLoader, "level.txt");
		
		// "U=xxxo,oxox,oxox,xxxo;L=xxxo,oooo,oxoo,xoxo;M=01,0;T=1,2;E=3,1:"
		Point expected = new Pointer(3, 1);
		Point actual = gameSaver.wheresExit();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
}
