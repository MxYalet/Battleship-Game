package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

class ShipTest {

    Ocean ocean;
    Ship ship;

    @BeforeEach
    void setUp() throws Exception {
        ocean = new Ocean();
    }

    @Test
    void testGetLength() {
        ship = new Battleship();
        assertEquals(4, ship.getLength());

        //TODO
        //More tests
        //set ship to Submarine and test that the length provided is 1
        ship = new Submarine();
        assertEquals(1, ship.getLength());

        //set ship to EmptySea and test that the length provided is 1
        ship = new EmptySea();
        assertEquals(1, ship.getLength());

        //set ship to Destroyer and test that the length provided is 2
        ship = new Destroyer();
        assertEquals(2, ship.getLength());

        //set ship to Cruiser and test that the length provided is 3
        ship = new Cruiser();
        assertEquals(3, ship.getLength());
    }

    @Test
    void testGetBowRow() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, battleship.getBowRow());

        //TODO
        //More tests
        //Set a cruiser with the front part at row 9 column 0 and test that the get bowRow method returns 9
        Ship cruiser = new Cruiser();
        row = 9;
        column = 0;
        horizontal = false;
        cruiser.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, cruiser.getBowRow());

        //Set a submarine with the front part at row 5 column 9 and test that the get bowRow method returns 5
        Ship submarine = new Submarine();
        row = 5;
        column = 9;
        horizontal = true;
        submarine.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, submarine.getBowRow());

    }

    @Test
    void testGetBowColumn() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        battleship.setBowColumn(column);
        assertEquals(column, battleship.getBowColumn());

        //TODO
        //More tests
        //Set a cruiser with the front part at row 9 column 8 and test that the get bowColumn method returns 8
        Ship cruiser = new Cruiser();
        row = 9;
        column = 8;
        horizontal = true;
        cruiser.placeShipAt(row, column, horizontal, ocean);
        assertEquals(column, cruiser.getBowColumn());

        //Set a submarine with the front part at row 5 column 9 and test that the get bowColumn method returns 9
        Ship submarine = new Submarine();
        row = 5;
        column = 9;
        horizontal = true;
        submarine.placeShipAt(row, column, horizontal, ocean);
        assertEquals(column, submarine.getBowColumn());
    }

    @Test
    void testGetHit() {
        ship = new Battleship();
        boolean[] hits = new boolean[4];
        assertArrayEquals(hits, ship.getHit());
        assertFalse(ship.getHit()[0]);
        assertFalse(ship.getHit()[1]);

        //TODO
        //More tests
        //Test that creates a submarine and a new hit array method of length 1
        //Ensures that the new array has a default value of false in the first index of the array
        ship = new Submarine();
        boolean[] hits1 = new boolean[1];
        assertArrayEquals(hits1, ship.getHit());
        assertFalse(ship.getHit()[0]);

        //Test that creates a Cruiser and a new hit array method of length 3
        //Ensures that the new array has a default value of false in the first, second and third index of the array
        ship = new Cruiser();
        boolean[] hits2 = new boolean[3];
        assertArrayEquals(hits2, ship.getHit());
        assertFalse(ship.getHit()[0]);
        assertFalse(ship.getHit()[1]);
        assertFalse(ship.getHit()[2]);


    }

    @Test
    void testgetInitialSunk () {
        //Sets up a new battleship and tests that the getInitialSunk method returns the default value of true
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertTrue(battleship.getInitialSunk());
        //Sets up a new cruiser and tests that the getInitialSunk method returns the default value of true
        Ship cruiser = new Cruiser();
        row = 9;
        column = 9;
        horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertTrue(cruiser.getInitialSunk());
        //Sets up a new destroyer and sets the initialSunk variable to false by calling the setInitialSunk method to false,
        //tests that the getInitialSunk method returns the updated false value
        Ship destroyer = new Destroyer();
        row = 4;
        column = 4;
        horizontal = true;
        destroyer.setInitialSunk(false);
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertFalse(destroyer.getInitialSunk());
    }
    @Test
    void testGetShipType() {
        ship = new Battleship();
        assertEquals("battleship", ship.getShipType());

        //TODO
        //More tests

        //creates a new Cruiser and tests that the getShipType returns cruiser
        ship = new Cruiser();
        assertEquals("cruiser", ship.getShipType());
        //creates a new Destroyer and tests that the getShipType returns destroyer
        ship = new Destroyer();
        assertEquals("destroyer", ship.getShipType());
        //creates a new Submarine and tests that the getShipType returns submarine
        ship = new Submarine();
        assertEquals("submarine", ship.getShipType());
        //creates a new EmptySea and tests that the getShipType returns empty
        ship = new EmptySea();
        assertEquals("empty", ship.getShipType());
    }

    @Test
    void testIsHorizontal() {
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertTrue(battleship.isHorizontal());

        //TODO
        //More tests
        //Create a new instance of a destroyer that is placed in vertical position
        //Checks that the isHorizontal method returns false
        Ship destroyer = new Destroyer();
        row = 5;
        column = 6;
        horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);
        assertFalse(destroyer.isHorizontal());


        //Create a new instance of a submarine that is placed in horizontal position
        //Checks that the isHorizontal method returns true
        Ship submarine = new Submarine();
        row = 3;
        column = 3;
        horizontal = true;
        submarine.placeShipAt(row, column, horizontal, ocean);
        assertTrue(submarine.isHorizontal());
    }

    @Test
    void testSetBowRow() {
        Ship battleship = new Battleship();
        int row = 0;
        battleship.setBowRow(row);
        assertEquals(row, battleship.getBowRow());

        //TODO
        //More tests
        //creates a new instance of a destroyer in row 5 column 7 and tests if the setBowRow method successfully sets the row
        //tests the method by calling the getBowRow method tested above
        Ship destroyer = new Destroyer();
        row = 5;
        destroyer.setBowRow(row);
        assertEquals(row, destroyer.getBowRow());

        //creates a new instance of a cruiser in row 9 column 9 and tests if the setBowRow method successfully sets the row
        //tests the method by calling the getBowRow method tested above
        Ship cruiser = new Cruiser();
        row = 9;
        cruiser.setBowRow(row);
        assertEquals(row, cruiser.getBowRow());
    }

    @Test
    void testSetBowColumn() {
        Ship battleship = new Battleship();
        int column = 4;
        battleship.setBowColumn(column);
        assertEquals(column, battleship.getBowColumn());

        //TODO
        //More tests
        //creates a new instance of a EmptySea in row 4 column 4 and tests if the setBowColumn method successfully sets the column
        //tests the method by calling the getBowColumn method tested above
        Ship emptySea = new EmptySea();
        column = 4;
        emptySea.setBowColumn(column);
        assertEquals(column, emptySea.getBowColumn());

        //creates a new instance of a Cruiser in row 7 column 7 and tests if the setBowColumn method successfully sets the column
        //tests the method by calling the getBowColumn method tested above
        Ship cruiser = new Cruiser();
        column = 7;
        cruiser.setBowColumn(column);
        assertEquals(column, cruiser.getBowColumn());
    }

    @Test
    void testSetHorizontal() {
        Ship battleship = new Battleship();
        boolean horizontal = true;
        battleship.setHorizontal(horizontal);
        assertTrue(battleship.isHorizontal());

        //TODO
        //More tests
        //Creates a new instance of a Destroyer and sets the direction to horizontal by using the setHorizontal method
        //Checks that the method successfully set the direction of the ship by calling the isHorizontal method tested above
        Ship destroyer = new Destroyer();
        horizontal = true;
        destroyer.setHorizontal(horizontal);
        assertTrue(destroyer.isHorizontal());

        //Creates a new instance of a Cruiser and sets the direction to vertical (false) by using the setHorizontal method
        //Checks that the method successfully set the direction of the ship by calling the isHorizontal method tested above
        Ship cruiser = new Cruiser();
        horizontal = false;
        cruiser.setHorizontal(horizontal);
        assertFalse(cruiser.isHorizontal());
    }

    @Test
    void testsetHit () {
        //Creates a new instance of EmptySea and creates a new boolean array of length of 1 with a value of true
        //uses the setHit method to set the ships hit array to true and checks that the new array and the ships array equal
        //also checks that the setHit method functions correctly by using the getHit method on the first boolean value of the array
        EmptySea ship = new EmptySea();
        boolean[] array = {true};
        boolean hit = true;
        ship.setHit(hit);
        assertArrayEquals(array, ship.getHit());
        assertTrue(ship.getHit()[0]);
        //Creates a new instance of EmptySea and creates a new boolean array of length of 1 with a value of false
        //uses the setHit method to set the ships hit array to false and checks that the new array and the ships array equal
        //also checks that the setHit method functions correctly by using the getHit method on the first boolean value of the array
        EmptySea ship1 = new EmptySea();
        boolean[] array1 = {false};
        hit = false;
        ship1.setHit(hit);
        assertArrayEquals(array1, ship1.getHit());
        assertFalse(ship1.getHit()[0]);
        //Creates a new instance of Submarine and creates a new boolean array of length of 1 with a value of false
        //uses the setHit method to set the ships hit array to false and checks that the new array and the ships array equal
        //also checks that the setHit method functions correctly by using the getHit method on the first boolean value of the array
        Ship ship2 = new Submarine();
        boolean[] array2 = {false};
        hit = false;
        ship2.setHit(hit);
        assertArrayEquals(array2, ship2.getHit());
        assertFalse(ship2.getHit()[0]);
    }
    @Test
    void testsetInitialSunk () {
        //Creates an instance of a battleship, uses the setInitialSunk method to set the variable to false
        //checks that the value was successfully updated by calling the getInitialSunk method and ensuring the values are equal
        Ship battleship = new Battleship();
        boolean bol = false;
        battleship.setInitialSunk(bol);
        assertEquals(bol, battleship.getInitialSunk());
        assertFalse(battleship.getInitialSunk());
        //Creates an instance of a cruiser, uses the setInitialSunk method to set the variable to true
        //checks that the value was successfully updated by calling the getInitialSunk method and ensuring the values are equal
        Ship cruiser = new Cruiser();
        bol = true;
        cruiser.setInitialSunk(bol);
        assertEquals(bol, cruiser.getInitialSunk());
        assertTrue(cruiser.getInitialSunk());
        //Creates an instance of a submarine, uses the setInitialSunk method to set the variable to false
        //checks that the value was successfully updated by calling the getInitialSunk method and ensuring the values are equal
        Ship submarine = new Submarine();
        bol = false;
        submarine.setInitialSunk(bol);
        assertEquals(bol, submarine.getInitialSunk());
        assertFalse(submarine.getInitialSunk());
    }
    @Test
    void testOkToPlaceShipAt() {

        //test when other ships are not in the ocean
        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok, "OK to place ship here.");

        //TODO
        //More tests

        //test that a ship that is being placed in row 0, column 0 and is horizontal should not be able to be placed in this location
        //since part of the ship would be out of bounds
        Ship cruiser = new Cruiser();
        row = 0;
        column = 0;
        horizontal = true;
        ok = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok, "Ship should not be allowed to be placed here.");


        //test that a ship that is being placed in row 5, column 5 and is vertical should be able to be placed in this location
        Ship destroyer = new Destroyer();
        row = 5;
        column = 5;
        horizontal = false;
        ok = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok, "OK to place ship here.");

        //test that a ship that is being placed in row 10, column 10 and is horizontal should not be able to be placed in this location
        //since both given locations are out of bounds
        //as a result of this test the method was updated to prevent out of bound inputs from being valid points per this test
        Ship cruiser2 = new Cruiser();
        row = 15;
        column = 15;
        horizontal = true;
        ok = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok, "Ship should not be allowed to be placed here.");


    }

    @Test
    void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {

        //test when other ships are in the ocean

        //place first ship
        Battleship battleship1 = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok1, "OK to place ship here.");
        battleship1.placeShipAt(row, column, horizontal, ocean);

        //test second ship
        Battleship battleship2 = new Battleship();
        row = 1;
        column = 4;
        horizontal = true;
        boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok2, "Not OK to place ship vertically adjacent below.");

        //TODO
        //More tests

        //test when other ships are in the ocean and the second one should not be ok to place since it occupies the space of the first one

        //place first ship
        Cruiser cruiser1 = new Cruiser();
        row = 9;
        column = 9;
        horizontal = true;
        boolean ok3 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok3, "OK to place ship here.");
        cruiser1.placeShipAt(row, column, horizontal, ocean);

        //test second ship
        Cruiser cruiser2 = new Cruiser();
        row = 9;
        column = 7;
        horizontal = true;
        boolean ok4 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
        assertFalse(ok4, "Not OK to place ship vertically adjacent below.");


        //test when other ships are in the ocean and the second one should be ok to place since it does not interfere with
        // the space of the first one
        //place first ship
        Destroyer destroyer1 = new Destroyer();
        row = 5;
        column = 5;
        horizontal = true;
        boolean ok5 = destroyer1.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok5, "OK to place ship here.");
        destroyer1.placeShipAt(row, column, horizontal, ocean);

        //test second ship
        Destroyer destroyer2 = new Destroyer();
        row = 3;
        column = 5;
        horizontal = true;
        boolean ok6 = cruiser2.okToPlaceShipAt(row, column, horizontal, ocean);
        assertTrue(ok6, "OK to place ship here.");
    }

    @Test
    void testPlaceShipAt() {

        Ship battleship = new Battleship();
        int row = 0;
        int column = 4;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, battleship.getBowRow());
        assertEquals(column, battleship.getBowColumn());
        assertTrue(battleship.isHorizontal());

        assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
        assertEquals(battleship, ocean.getShipArray()[0][1]);


        //TODO
        //More tests

        //Creates an instance of a cruiser and checks that the placeShipAt method successfully places the ship with the correct location and direction
        //uses the getBowRow, getBowColumn, isHorizontal and getShipType methods to ensure that the placed ship has the same attributes as the ones
        //used in the arguments used to place the ship
        Ship cruiser = new Cruiser();
        row = 9;
        column = 9;
        horizontal = true;
        cruiser.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, cruiser.getBowRow());
        assertEquals(column, cruiser.getBowColumn());
        assertTrue(cruiser.isHorizontal());
        //Checks that a location around the placed ship is empty
        assertEquals("empty", ocean.getShipArray()[8][9].getShipType());
        //checks that the location where the ship was placed returns the correct ship type when calling the getShipType method
        assertEquals(cruiser, ocean.getShipArray()[9][8]);

        //Creates an instance of a submarine and checks that the placeShipAt method successfully places the ship with the correct location and direction
        //uses the getBowRow, getBowColumn, isHorizontal and getShipType methods to ensure that the placed ship has the same attributes as the ones
        //used in the arguments used to place the ship
        Ship submarine = new Submarine();
        row = 5;
        column = 5;
        horizontal = false;
        submarine.placeShipAt(row, column, horizontal, ocean);
        assertEquals(row, submarine.getBowRow());
        assertEquals(column, submarine.getBowColumn());
        assertFalse(submarine.isHorizontal());
        //Checks that the area around the placed ship is empty
        assertEquals("empty", ocean.getShipArray()[4][5].getShipType());
        assertEquals("empty", ocean.getShipArray()[6][5].getShipType());
        assertEquals("empty", ocean.getShipArray()[5][6].getShipType());
        assertEquals("empty", ocean.getShipArray()[5][4].getShipType());
        //checks that the location where the ship was placed returns the correct ship type when calling the getShipType method
        assertEquals(submarine, ocean.getShipArray()[5][5]);
    }

    @Test
    void testShootAt() {

        Ship battleship = new Battleship();
        int row = 0;
        int column = 9;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);

        assertFalse(battleship.shootAt(1, 9));
        boolean[] hitArray0 = {false, false, false, false};
        assertArrayEquals(hitArray0, battleship.getHit());

        //TODO
        //More tests
        //Creates an instance of submarine, shoots at it, and ensures that the hit array for the ship is updated to true
        // after successfully hitting the location
        Ship submarine = new Submarine();
        row = 5;
        column = 5;
        horizontal = true;
        submarine.placeShipAt(row, column, horizontal, ocean);

        assertTrue(submarine.shootAt(5, 5));
        boolean[] hitArray1 = {true};
        assertArrayEquals(hitArray1, submarine.getHit());
        //Creates an instance of destroyer, shoots at both locations it covers, and ensures that the
        // hit array for the ship is updated to true (in both indexes)
        // after successfully hitting the locations
        Ship destroyer = new Destroyer();
        row = 8;
        column = 8;
        horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        assertTrue(destroyer.shootAt(7, 8));
        assertTrue(destroyer.shootAt(8, 8));
        boolean[] hitArray2 = {true, true};
        assertArrayEquals(hitArray2, destroyer.getHit());
        //Creates an instance of cruiser, shoots at only two out of three locations it covers, and ensures that the
        // hit array for the ship is updated to true (in both indexes that were shot)
        // after successfully hitting the locations (leaving one location in the array as false as it was not shot at)
        Ship cruiser = new Cruiser();
        row = 8;
        column = 2;
        horizontal = false;
        cruiser.placeShipAt(row, column, horizontal, ocean);

        assertTrue(cruiser.shootAt(8, 2));
        assertTrue(cruiser.shootAt(7, 2));
        boolean[] hitArray3 = {true, true, false};
        assertArrayEquals(hitArray3, cruiser.getHit());
    }

    @Test
    void testIsSunk() {

        Ship submarine = new Submarine();
        int row = 3;
        int column = 3;
        boolean horizontal = true;
        submarine.placeShipAt(row, column, horizontal, ocean);

        assertFalse(submarine.isSunk());
        assertFalse(submarine.shootAt(5, 2));
        assertFalse(submarine.isSunk());

        //TODO
        //More tests
        //Creates an instance of battleship (which covers four locations)
        //calls the isSunk method to check that it is initially not sunk
        //shots at the front of the ship only
        //calls the isSunk method once again and checks that the ship is still not sunk (isSunk = false)
        Ship battleship = new Battleship();
        row = 9;
        column = 9;
        horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);

        assertFalse(battleship.isSunk());
        assertTrue(battleship.shootAt(9, 9));
        assertFalse(battleship.isSunk());

        //Creates an instance of destroyer (which covers two locations)
        //calls the isSunk method to check that it is initially not sunk
        //shots all the ships locations
        //calls the isSunk method once again and checks that the ship is now sunk (isSunk = true)
        Ship destroyer = new Destroyer();
        row = 5;
        column = 5;
        horizontal = true;
        destroyer.placeShipAt(row, column, horizontal, ocean);

        assertFalse(destroyer.isSunk());
        assertTrue(destroyer.shootAt(5, 5));
        assertTrue(destroyer.shootAt(5, 4));
        assertTrue(destroyer.isSunk());

    }

    @Test
    void testbeenHit () {
        //Sets up a new battleship, places it on the ocean grid and shoots at one location it takes on the grid.
        //Tests that calling the beenHit function the hit location returns true and false for a location that has not been shot at.
        Ship battleship = new Battleship();
        int row = 9;
        int column = 9;
        boolean horizontal = true;
        battleship.placeShipAt(row, column, horizontal, ocean);
        battleship.shootAt(9, 9);
        assertFalse(battleship.beenHit(9, 8));
        assertTrue(battleship.beenHit(9, 9));

        //Sets up a new cruiser, places it on the ocean grid and shoots at one location it takes on the grid.
        //Tests that calling the beenHit function the hit location returns true and false for a location that has not been shot at.
        Ship cruiser = new Cruiser();
        row = 5;
        column = 5;
        horizontal = false;
        cruiser.placeShipAt(row, column, horizontal, ocean);
        cruiser.shootAt(3, 5);
        assertFalse(cruiser.beenHit(5, 5));
        assertTrue(cruiser.beenHit(3, 5));


        //Sets up a new destroyer, places it on the ocean grid and shoots at both locations it takes on the grid.
        //Tests that calling the beenHit function on both locations returns true
        Ship destroyer = new Destroyer();
        row = 0;
        column = 1;
        horizontal = true;
        destroyer.placeShipAt(row, column, horizontal, ocean);
        destroyer.shootAt(0, 0);
        destroyer.shootAt(0, 1);
        assertTrue(destroyer.beenHit(0, 0));
        assertTrue(destroyer.beenHit(0, 1));
    }
    @Test
    void testToString() {

        Ship battleship = new Battleship();
        assertEquals("x", battleship.toString());

        int row = 9;
        int column = 1;
        boolean horizontal = false;
        battleship.placeShipAt(row, column, horizontal, ocean);
        battleship.shootAt(9, 1);
        assertEquals("x", battleship.toString());

        //TODO
        //More tests
        //Creates a new destroyer
        //shots at both locations it covers and calls the toSting method
        //checks that the method returns "s" as the ship has been sunken
        Ship destroyer = new Destroyer();
        assertEquals("x", destroyer.toString());

        row = 9;
        column = 9;
        horizontal = false;
        destroyer.placeShipAt(row, column, horizontal, ocean);
        destroyer.shootAt(9, 9);
        destroyer.shootAt(8, 9);
        assertEquals("s", destroyer.toString());

        //Creates a new cruiser
        //shots at only two locations (it covers 3) and calls the toSting method
        //checks that the method returns "x" as the ship has not yet been sunken
        Ship cruiser = new Cruiser();
        assertEquals("x", cruiser.toString());

        row = 5;
        column = 5;
        horizontal = false;
        cruiser.placeShipAt(row, column, horizontal, ocean);
        cruiser.shootAt(5, 5);
        cruiser.shootAt(4, 5);
        assertEquals("x", cruiser.toString());
    }
}