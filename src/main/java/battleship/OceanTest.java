package battleship;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.*;





class OceanTest {

	Ocean ocean;

	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;

	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testEmptyOcean() {

		//tests that all locations in the ocean are "empty"

		Ship[][] ships = ocean.getShipArray();

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];

				assertEquals("empty", ship.getShipType());
			}
		}

		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());

		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());

		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}

	@Test
	void testPlaceAllShipsRandomly() {

		//tests that the correct number of each ship type is placed in the ocean

		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();

		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;

		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}

		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}

		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);

		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE;
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);

		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.isOccupied(1, 5));

		//create a new battleship and place it on the grid in its respective row, column
		Ship battleship = new Battleship();
		row = 2;
		column = 7;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		//test battleship placement runs east-to-west
		assertFalse(ocean.isOccupied(2, 9));

		//test battleship placement is valid
		assertTrue(ocean.isOccupied(2, 7));

		//test submarine placement is still valid and the recently created battleship
		assertEquals(ocean.isOccupied(0,0), ocean.isOccupied(2,7));

	}

	@Test
	void testShootAt() {

		assertFalse(ocean.shootAt(0, 1));

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));

		//create a new battleship and place it on the grid in its respective row, column
		Ship battleship = new Battleship();
		row = 5;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		//tests if a battleship horizontally can be hit at and sunk based on its row, column, length
		assertTrue(ocean.shootAt(5, 8));
		assertTrue(ocean.shootAt(5, 7));
		assertTrue(ocean.shootAt(5, 6));
		assertTrue(ocean.shootAt(5, 5));
		assertTrue(battleship.isSunk());

		//creates and places sub object
		Ship submarine = new Submarine();
		row = 9;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		//tests if a sub horizontally can be hit at and sunk based on its row, column, length
		assertTrue(ocean.shootAt(9, 0));
		assertTrue(submarine.isSunk());


	}

	@Test
	void testGetShotsFired() {

		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());

		//creates and places destroyer obj
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		//creates and places sub object
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);

		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());

		//tests if ship is already sunk, return false
		assertFalse(ocean.shootAt(0,5));
		assertFalse(ocean.shootAt(1,5));

		//tests for addtl shot counts
		assertEquals(8, ocean.getShotsFired());

	}

	@Test
	void testGetHitCount() {

		//test Destroyer being placed
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		//test if shot is fired, but ship not sunk
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());

		//test if shot is fired, hits ship, and ship sinks, hit count is incremented
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());

		//test if shot is fired at an empty sea, will hit count remained unchanged
		assertFalse(ocean.shootAt(0, 0));
		assertEquals(2, ocean.getHitCount());

		//test submarine being placed
		Submarine sub = new Submarine();
		row = 6;
		column = 6;
		horizontal = true;
		sub.placeShipAt(row, column, horizontal, ocean);

		//test if shot is fired, hits ship, and ship sinks, hit count is incremented
		assertTrue(ocean.shootAt(6, 6));
		assertTrue(sub.isSunk());
		assertEquals(3, ocean.getHitCount());




	}

	@Test
	void testGetShipsSunk() {

		//test destroyer being placed
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);

		//test whether destroyer is sunk or not based on hit array
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());

		//test battleship being placed
		Battleship battleship = new Battleship();
		row = 3;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);

		//test whether battleship is sunk or not based on hit array
		assertTrue(ocean.shootAt(3, 8));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(3, 7));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(3, 6));
		assertFalse(battleship.isSunk());
		assertTrue(ocean.shootAt(3, 5));
		assertTrue(battleship.isSunk());

		//test submarine being placed
		Submarine sub = new Submarine();
		row = 6;
		column = 6;
		horizontal = true;
		sub.placeShipAt(row, column, horizontal, ocean);

		//test whether sub is sunk or not based on hit array
		assertTrue(ocean.shootAt(6, 6));
		assertTrue(sub.isSunk());

	}

	@Test
	void testGetShipArray() {

		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);

		assertEquals("empty", shipArray[0][0].getShipType());

		//create a new battleship
		Battleship battleship = new Battleship();
		int row = 4;
		int col = 5;
		boolean horizontal = false;
		battleship.placeShipAt(row, col, horizontal, ocean);

		//test if battleship type is properly returned for the placement of the battleship
		assertEquals("battleship", shipArray[3][5].getShipType());
		assertEquals("battleship", shipArray[4][5].getShipType());
		assertEquals("empty", shipArray[5][5].getShipType());

		//create a new cruiser
		Cruiser cruiser = new Cruiser();
		 row = 9;
		 col = 2;
		 horizontal = true;
		cruiser.placeShipAt(row, col, horizontal, ocean);

		//test if cruiser type is properly returned for the placement of the battleship
		assertEquals("cruiser", shipArray[9][2].getShipType());
		assertEquals("cruiser", shipArray[9][0].getShipType());
		assertEquals("empty", shipArray[9][3].getShipType());


	}

	@Test
	void testisGameOver() {

	//test creation of three new sub objects, sink them, then see if game is over
	Submarine sub1 = new Submarine();
	int row = 0;
	int col = 3;
	boolean horizontal = true;
	sub1.placeShipAt(row, col, horizontal, ocean);
	ocean.shootAt(0, 3);
	assertTrue(sub1.isSunk());
	assertFalse(ocean.isGameOver());

	Submarine sub2 = new Submarine();
	 row = 0;
	 col = 5;
	 horizontal = true;
	 sub2.placeShipAt(row, col, horizontal, ocean);
	 ocean.shootAt(0, 5);
	 assertTrue(sub2.isSunk());
	 assertFalse(ocean.isGameOver());


	 Submarine sub3 = new Submarine();
	 row = 9;
	 col = 9;
	 horizontal = false;
	 sub3.placeShipAt(row, col, horizontal, ocean);
	 ocean.shootAt(9,9);
	 assertTrue(sub3.isSunk());
	 assertFalse(ocean.isGameOver());




	}



}