package nz.ac.ara.ayreye.theseusandtheminotaur.v5;

import static org.junit.Assert.*;
import org.junit.*;

public class GameTests {

	public static Playable game;
	public static Loadable gameLoader;
	public static Saveable gameSaver;
	
	@Before // could just use 'Before' and remove tests
	public void setUp() {
		game = new Game();
		gameLoader = (Loadable)game;
		gameSaver = (Saveable)game;
		
		gameLoader.setDepthDown(7);
		gameLoader.setWidthAcross(7);
		

		/*
		 * Set basic boundary
		 */
		for (int i = 0; i < gameSaver.getWidthAcross(); i++) {
			MyPoint point = new DefaultPoint(i, 0);
			gameLoader.addWallAbove(point);
		}
		for (int j = 0; j < gameSaver.getDepthDown(); j++) {
			MyPoint point = new DefaultPoint(0, j);
			gameLoader.addWallLeft(point);
		}
		for (int k = 0; k < gameSaver.getWidthAcross(); k++) {
			MyPoint point = new DefaultPoint(k, gameSaver.getDepthDown() - 1);
			gameLoader.addWallAbove(point);
		}
		for (int l = 0; l < gameSaver.getDepthDown(); l++) {
			MyPoint point = new DefaultPoint(gameSaver.getWidthAcross() - 1, l);
			gameLoader.addWallLeft(point);
		}
		
		/*
		 * Test 1
		 */
//		for (int i = 0; i < gameSaver.getDepthDown(); i++) {
//			String row = "";
//			for (int j = 0; j < gameSaver.getWidthAcross(); j++) {
//				MyPoint where = new DefaultPoint(j, i);
//				Wall top = gameSaver.whatsAbove(where);
//				Wall left = gameSaver.whatsLeft(where);
//				row += "{left:" + left + ", top:" + top + "} ";
//			}
//			System.out.println(row);
//		}
		
		/*
		 * Test 2
		 */
//		gameLoader.addTheseus(new DefaultPoint(1, 4));
//		gameLoader.addMinotaur(new DefaultPoint(3, 3));
//		System.out.println("th:" + gameSaver.wheresTheseus().across()
//							+ ", "
//							+ gameSaver.wheresTheseus().down());
//		System.out.println("min:" + gameSaver.wheresMinotaur().across()
//				+ ", "
//				+ gameSaver.wheresMinotaur().down());
//		//game.moveTheseus(Direction.RIGHT);
//		game.moveMinotaur();
//		game.moveMinotaur();
//		System.out.println("th:" + gameSaver.wheresTheseus().across()
//							+ ", "
//							+ gameSaver.wheresTheseus().down());
//		System.out.println("min:" + gameSaver.wheresMinotaur().across()
//				+ ", "
//				+ gameSaver.wheresMinotaur().down());
	}
	
	/*
	 * isBlocked()
	 */
	
	@Test
	public void isBlocked_moveUp_true() {
		MyPoint current = new DefaultPoint(3, 5);
		MyPoint destination = new DefaultPoint(3, 4);
		
		MyPoint wallAt = new DefaultPoint(3, 5);
		gameLoader.addWallAbove(wallAt);
		
		boolean expected = true;
		boolean actual = game.isBlocked(Direction.UP, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveUp_false() {
		MyPoint current = new DefaultPoint(3, 5);
		MyPoint destination = new DefaultPoint(3, 4);
		
		boolean expected = false;
		boolean actual = game.isBlocked(Direction.UP, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveRight_true() {
		MyPoint current = new DefaultPoint(3, 3);
		MyPoint destination = new DefaultPoint(4, 3);
		
		MyPoint wallAt = new DefaultPoint(4, 3);
		gameLoader.addWallLeft(wallAt);
		
		boolean expected = true;
		boolean actual = game.isBlocked(Direction.RIGHT, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveRight_false() {
		MyPoint current = new DefaultPoint(3, 3);
		MyPoint destination = new DefaultPoint(4, 3);
		
		boolean expected = false;
		boolean actual = game.isBlocked(Direction.RIGHT, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveDown_true() {
		MyPoint current = new DefaultPoint(3, 3);
		MyPoint destination = new DefaultPoint(3, 4);
		
		MyPoint wallAt = new DefaultPoint(3, 4);
		gameLoader.addWallAbove(wallAt);
		
		boolean expected = true;
		boolean actual = game.isBlocked(Direction.DOWN, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveDown_false() {
		MyPoint current = new DefaultPoint(3, 3);
		MyPoint destination = new DefaultPoint(3, 4);
		
		boolean expected = false;
		boolean actual = game.isBlocked(Direction.DOWN, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveLeft_true() {
		MyPoint current = new DefaultPoint(3, 3);
		MyPoint destination = new DefaultPoint(2, 3);
		
		MyPoint wallAt = new DefaultPoint(3, 3);
		gameLoader.addWallLeft(wallAt);
		
		boolean expected = true;
		boolean actual = game.isBlocked(Direction.LEFT, current, destination);
		
		assertEquals(expected, actual);	
	}
	@Test
	public void isBlocked_moveLeft_false() {
		MyPoint current = new DefaultPoint(3, 3);
		MyPoint destination = new DefaultPoint(2, 3);
		
		boolean expected = false;
		boolean actual = game.isBlocked(Direction.LEFT, current, destination);
		
		assertEquals(expected, actual);	
	}
	/*
	 * findDirection
	 */
	@Test
	public void findDirection_tNorthMSouth_dirUp() {
		MyPoint theseusAt = new DefaultPoint(3, 0);
		gameLoader.addTheseus(theseusAt);
		
		MyPoint minotaurAt = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.UP;
		Direction actual = game.findDirection(theseusAt, minotaurAt);
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tSouthMNorth_dirDown() {
		MyPoint theseusAt = new DefaultPoint(3, 3);
		gameLoader.addTheseus(theseusAt);
		
		MyPoint minotaurAt = new DefaultPoint(3, 0);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.DOWN;
		Direction actual = game.findDirection(theseusAt, minotaurAt);
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tEastMWest_dirRight() {
		MyPoint theseusAt = new DefaultPoint(3, 3);
		gameLoader.addTheseus(theseusAt);
		
		MyPoint minotaurAt = new DefaultPoint(0, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.RIGHT;
		Direction actual = game.findDirection(theseusAt, minotaurAt);
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tWestMEast_dirLeft() {
		MyPoint theseusAt = new DefaultPoint(0, 3);
		gameLoader.addTheseus(theseusAt);
		
		MyPoint minotaurAt = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.LEFT;
		Direction actual = game.findDirection(theseusAt, minotaurAt);
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tNorthEast_dirRight() {
		MyPoint theseusAt = new DefaultPoint(4, 2);
		gameLoader.addTheseus(theseusAt);
		
		MyPoint minotaurAt = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.RIGHT;
		Direction actual = game.findDirection(theseusAt, minotaurAt);
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tNorthWest_dirLeft() {
		MyPoint theseusAt = new DefaultPoint(2, 2);
		gameLoader.addTheseus(theseusAt);
		
		MyPoint minotaurAt = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.LEFT;
		Direction actual = game.findDirection(theseusAt, minotaurAt);
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tSouthEast_dirRight() {
		MyPoint theseusAt = new DefaultPoint(4, 4);
		gameLoader.addTheseus(theseusAt);
		
		MyPoint minotaurAt = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.RIGHT;
		Direction actual = game.findDirection(theseusAt, minotaurAt);
		
		assertEquals(expected, actual);
	}
	@Test
	public void findDirection_tSouthWest_dirLeft() {
		MyPoint theseusAt = new DefaultPoint(2, 4);
		gameLoader.addTheseus(theseusAt);
		
		MyPoint minotaurAt = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(minotaurAt);
		
		Direction expected = Direction.LEFT;
		Direction actual = game.findDirection(theseusAt, minotaurAt);
		
		assertEquals(expected, actual);
	}
	
	// 1. up 1
	@Test
	public void moveMinotaurUp_oneSpaceBetween_upTwo_theseusDead() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 1);
		gameLoader.addTheseus(whereThes);
		
		// Not sure if 2 calls?
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 1);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		// Find better solution??
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 2. up 2
	@Test
	public void moveMinotaurUp_twoSpacesBetween_upTwo() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 0);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 1);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 3. up 3
	@Test
	public void moveMinotaurUp_threeSpacesBetween_upTwo() {
		MyPoint whereMin = new DefaultPoint(3, 4);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 0);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 2);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 4. right 1
	@Test
	public void moveMinotaurRight_oneSpaceBetween_rightTwo_theseusDead() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(5, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(5, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 5. right 2
	@Test
	public void moveMinotaurRight_twoSpacesBetween_rightTwo() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(6, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(5, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 5. a) right 2 
	@Test
	public void moveMinotaur_rightTwice_0_5() {
		MyPoint whereMin = new DefaultPoint(0, 5);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(2, 4);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(2, 5);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 6. right 3
	@Test
	public void moveMinotaurRight_threeSpacesBetween_rightTwo() {
		MyPoint whereMin = new DefaultPoint(2, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(6, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(4, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 7. down 1
	@Test
	public void moveMinotaurDown_oneSpaceBetween_downTwo_theseusDead() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 5);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 5);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 8. down 2
	@Test
	public void moveMinotaurDown_twoSpacesBetween_downTwo() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 6);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 5);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 9. down 3
	@Test
	public void moveMinotaurDown_threeSpacesBetween_downTwo() {
		MyPoint whereMin = new DefaultPoint(3, 2);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 6);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 4);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 10. left 1
	@Test
	public void moveMinotaurLeft_oneSpaceBetween_leftTwo_theseusDead() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 11. left 2
	@Test
	public void moveMinotaurLeft_twoSpacesBetween_leftTwo() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(0, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 12. left 3
	@Test
	public void moveMinotaurLeft_threeSpacesBetween_leftTwo() {
		MyPoint whereMin = new DefaultPoint(4, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(0, 3);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(2, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	/*
	 * Using walls
	 */
	
	// 13. up 1 with wall
	@Test
	public void moveMinotaurUp_oneSpaceBetweenButWall_upNone() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 1);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 3);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
//		boolean expected = true;
//		boolean actual = game.isBlocked(Direction.UP, whereMin, new DefaultPoint(3, 2));
//		assertEquals(expected, actual);
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 14. up 2 with wall
	@Test
	public void moveMinotaurUp_twoSpacesBetweenButWall_upOne() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 0);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 2);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 2);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 15. up 3 with wall
	@Test
	public void moveMinotaurUp_threeSpacesBetweenButWall_upTwo() {
		MyPoint whereMin = new DefaultPoint(3, 4);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 0);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 2);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 2);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 16. right 1 with wall
	@Test
	public void moveMinotaurRight_oneSpaceBetweenButWall_rightNone() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(5, 3);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(4, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 17. right 2 with wall
	@Test
	public void moveMinotaurRight_oneSpaceBetweenButWall_rightOne() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(6, 3);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(5, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(4, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 18. right 3 with wall
	@Test
	public void moveMinotaurRight_oneSpaceBetweenButWall_rightTwo() {
		MyPoint whereMin = new DefaultPoint(2, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(6, 3);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(5, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(4, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 19. down 1 with wall
	@Test
	public void moveMinotaurDown_oneSpaceBetweenButWall_downNone() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 5);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 4);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 20. down 2 with wall
	@Test
	public void moveMinotaurDown_twoSpaceBetweenButWall_downOne() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 6);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 5);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 4);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 21. down 3 with wall
	@Test
	public void moveMinotaurDown_threeSpaceBetweenButWall_downTwo() {
		MyPoint whereMin = new DefaultPoint(3, 2);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 6);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 5);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 4);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 22. left 1 with wall
	@Test
	public void moveMinotaurDown_oneSpaceBetweenButWall_leftNone() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 3);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 23. left 2 with wall
	@Test
	public void moveMinotaurDown_twoSpacesBetweenButWall_leftOne() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(0, 3);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(2, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(2, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 24. left 3 with wall
	@Test
	public void moveMinotaurDown_threeSpacesBetweenButWall_leftTwo() {
		MyPoint whereMin = new DefaultPoint(4, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(0, 3);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(2, 3);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(2, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 25. no7
	@Test
	public void moveMinotaur_RightDown_wall_downRight() {
		MyPoint whereMin = new DefaultPoint(0, 0);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(2, 1);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(1, 0);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 1);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 26. no8
	@Test
	public void moveMinotaur_RightDown_walls_downDown() {
		MyPoint whereMin = new DefaultPoint(0, 0);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(2, 1);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall1 = new DefaultPoint(1, 0);
		gameLoader.addWallLeft(whereWall1);
		MyPoint whereWall2 = new DefaultPoint(1, 1);
		gameLoader.addWallLeft(whereWall2);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(0, 1);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 27. no9
	@Test
	public void moveMinotaur_RightDown_rightDown() {
		MyPoint whereMin = new DefaultPoint(0, 0);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 2);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 1);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 28. no10
	@Test
	public void moveMinotaur_RightDown_wall_rightNone() {
		MyPoint whereMin = new DefaultPoint(0, 0);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 2);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(1, 1);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 0);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 29. no11
	@Test
	public void moveMinotaur_RightDown_wall2_downRight() {
		MyPoint whereMin = new DefaultPoint(0, 0);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 2);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(1, 0);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 1);
		MyPoint actual = gameSaver.wheresMinotaur(); // 0, 0
		
		// 0, 0
		System.out.println("top:" + gameSaver.whatsAbove(whereMin));
		System.out.println("left:" + gameSaver.whatsLeft(whereMin));
		
		// 0, 1
		MyPoint zeroOne = new DefaultPoint(0, 1);
		System.out.println("top:" + gameSaver.whatsAbove(zeroOne));
		System.out.println("left:" + gameSaver.whatsLeft(zeroOne));
		System.out.println("blocked?:" + game.isBlocked(Direction.DOWN, whereMin, zeroOne));
		
		// 0, 2
		MyPoint zeroTwo = new DefaultPoint(0, 2);
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
		MyPoint whereMin = new DefaultPoint(0, 0);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(2, 1);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall1 = new DefaultPoint(2, 0);
		gameLoader.addWallLeft(whereWall1);
		MyPoint whereWall2 = new DefaultPoint(1, 1);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 0);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 31. no12
	@Test
	public void moveMinotaur_RightDown_2walls_downDown() {
		MyPoint whereMin = new DefaultPoint(0, 0);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 2);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall1 = new DefaultPoint(1, 0);
		gameLoader.addWallLeft(whereWall1);
		MyPoint whereWall2 = new DefaultPoint(1, 1);
		gameLoader.addWallLeft(whereWall2);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(0, 2);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 32. no13
	@Test
	public void moveMinotaur_RightDown_2wallsCorner_downNone() {
		MyPoint whereMin = new DefaultPoint(0, 0);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 2);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall1 = new DefaultPoint(1, 0);
		gameLoader.addWallLeft(whereWall1);
		MyPoint whereWall2 = new DefaultPoint(1, 1);
		gameLoader.addWallLeft(whereWall2);
		MyPoint whereWall3 = new DefaultPoint(0, 2);
		gameLoader.addWallAbove(whereWall3);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(0, 1);
		MyPoint actual = gameSaver.wheresMinotaur();
		
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
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(2, 5);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 4);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(2, 4);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 34. no8
	@Test
	public void moveMinotaur_downLeft_2walls_leftNone() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(2, 5);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 4);
		gameLoader.addWallAbove(whereWall);
		MyPoint whereWall2 = new DefaultPoint(2, 4);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(2, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 35. no9
	@Test
	public void moveMinotaur_downLeft_leftLeft() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 4);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 36. no10
	@Test
	public void moveMinotaur_downLeft_wall_leftLeft() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 4);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 4);
		gameLoader.addWallLeft(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 37. no11
	@Test
	public void moveMinotaur_downLeft_wall_leftLeft2() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 4);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 4);
		gameLoader.addWallAbove(whereWall);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 38. no6
	@Test
	public void moveMinotaur_downLeft_wallCorner_leftLeft() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(2, 5);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(3, 4);
		gameLoader.addWallLeft(whereWall);
		MyPoint whereWall2 = new DefaultPoint(3, 5);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(2, 4);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 39. no12
	@Test
	public void moveMinotaur_downLeft_2walls_leftLeft() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 4);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall = new DefaultPoint(2, 4);
		gameLoader.addWallAbove(whereWall);
		MyPoint whereWall2 = new DefaultPoint(3, 4);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(1, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// 40. no13
	@Test
	public void moveMinotaur_downLeft_2wallsCorner_leftNone() {
		MyPoint whereMin = new DefaultPoint(3, 3);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(1, 4);
		gameLoader.addTheseus(whereThes);
		MyPoint whereWall0 = new DefaultPoint(2, 3);
		gameLoader.addWallLeft(whereWall0);
		MyPoint whereWall1 = new DefaultPoint(2, 4);
		gameLoader.addWallAbove(whereWall1);
		MyPoint whereWall2 = new DefaultPoint(3, 4);
		gameLoader.addWallAbove(whereWall2);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(2, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
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
		MyPoint whereMin = new DefaultPoint(2, 4);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(3, 2);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(3, 3);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
	// left->up->death
	@Test
	public void moveMinotaur_leftUp_death() {
		MyPoint whereMin = new DefaultPoint(1, 1);
		gameLoader.addMinotaur(whereMin);
		MyPoint whereThes = new DefaultPoint(0, 0);
		gameLoader.addTheseus(whereThes);
		
		game.moveMinotaur();
		game.moveMinotaur();
		
		MyPoint expected = new DefaultPoint(0, 0);
		MyPoint actual = gameSaver.wheresMinotaur();
		
		assertEquals(expected.across(), actual.across());
		assertEquals(expected.down(), actual.down());
	}
	
}
