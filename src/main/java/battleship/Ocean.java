package battleship;

import java.util.Random;

public class Ocean {
    /**
     * Indicates size of the ocean and number of ships in the fleet.
     */
    static final int OCEAN_SIZE = 10;

    /**
     * Used to store different kinds of ships in the fleet.
     */
    private Ship[][] ships = new Ship[Ocean.OCEAN_SIZE][Ocean.OCEAN_SIZE];

    static final int NUM_BATTLESHIPS = 1; //total # of battleship
    static final int NUM_CRUISERS = 2; //total # of cruisers
    static final int NUM_DESTROYERS = 3; //total # of destroyers
    static final int NUM_SUBMARINES = 4; //total # of subs

    /**
     * The total number of shots fired by the user.
     */
    private int shotsFired;

    /**
     * The number of times a shot hit a ship. If the user shots the same part of a ship
     * more than once, every hit is counted, even though additional "hits" don't do the user any good.
     */
    private int hitCount;

    /**
     * the number of ships sunk (10 ships in total).
     */
    private int shipsSunk;

    /**
     * Will initialize appropriate counts that will be included in the game summary, as well as creates the ocean
     * with empty sea ship objects in every location
     */
    public Ocean() {
        //initialize shot, hit, ships sunk count
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;

        //create a 10x10 "ocean" array and place empty ship in every array location
        for (int row = 0; row < OCEAN_SIZE; row++) {
            for (int col = 0; col < OCEAN_SIZE; col++) {
                Ship emptySea = new EmptySea();
                emptySea.placeShipAt(row, col, true, this);
            }
        }
    }

    /**
     * This method will use the okToPlaceShipAt method to decide where to place all 10 ships in the Ocean array.
     * Random numbers & a random boolean will be generated until a valid position is found for the ship
     */
    void placeAllShipsRandomly() {

        //init random variables that will be used
        Random random = new Random();
        int randRow;
        int randCol;
        boolean randHorizontal;

        //iterate through the # of ships that need to be created
        //use random numbers and boolean and pass to OkToPlaceShipAt until all ships are allowed to be placed in valid positions
        //place the ship in the ocean and continue process for each type of ship until all ships are placed
        for (int i = 0; i < NUM_BATTLESHIPS; i++) {
            Ship battleShip = new Battleship();
            randRow = random.nextInt(OCEAN_SIZE);
            randCol = random.nextInt(OCEAN_SIZE);
            randHorizontal = random.nextBoolean();

            while ((battleShip.okToPlaceShipAt(randRow, randCol, randHorizontal, this)) == false) {
                randRow = random.nextInt(OCEAN_SIZE);
                randCol = random.nextInt(OCEAN_SIZE);
                randHorizontal = random.nextBoolean();
            }
            battleShip.placeShipAt(randRow, randCol, randHorizontal, this);
        }

        for (int i = 0; i < NUM_CRUISERS; i++) {
            Ship cruiser = new Cruiser();
            randRow = random.nextInt(OCEAN_SIZE);
            randCol = random.nextInt(OCEAN_SIZE);
            randHorizontal = random.nextBoolean();

            while ((cruiser.okToPlaceShipAt(randRow, randCol, randHorizontal, this)) == false) {
                randRow = random.nextInt(OCEAN_SIZE);
                randCol = random.nextInt(OCEAN_SIZE);
                randHorizontal = random.nextBoolean();
            }
            cruiser.placeShipAt(randRow, randCol, randHorizontal, this);
        }

        for (int i = 0; i < NUM_DESTROYERS; i++) {
            Ship destroyer = new Destroyer();
            randRow = random.nextInt(OCEAN_SIZE);
            randCol = random.nextInt(OCEAN_SIZE);
            randHorizontal = random.nextBoolean();

            while ((destroyer.okToPlaceShipAt(randRow, randCol, randHorizontal, this)) == false) {
                randRow = random.nextInt(OCEAN_SIZE);
                randCol = random.nextInt(OCEAN_SIZE);
                randHorizontal = random.nextBoolean();
            }
            destroyer.placeShipAt(randRow, randCol, randHorizontal, this);
        }

        for (int i = 0; i < NUM_SUBMARINES; i++) {
            Ship submarine = new Submarine();
            randRow = random.nextInt(OCEAN_SIZE);
            randCol = random.nextInt(OCEAN_SIZE);
            randHorizontal = random.nextBoolean();

            while ((submarine.okToPlaceShipAt(randRow, randCol, randHorizontal, this)) == false) {
                randRow = random.nextInt(OCEAN_SIZE);
                randCol = random.nextInt(OCEAN_SIZE);
                randHorizontal = random.nextBoolean();
            }
            submarine.placeShipAt(randRow, randCol, randHorizontal, this);
        }
    }

    /**
     *
     * @param row
     * @param column
     * @return true if there is a ship at a row, column position in the array. Return false if there is an empty position
     */
    boolean isOccupied(int row, int column) {
        if ((ships[row][column]).getShipType().equals("empty")) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param row
     * @param column
     * @return true if a ship has been hit and the ship's hit array updated from false to true. Return false if a ship is not hit
     * This method will also increment shotsFired, hitCount, and shipsSunk when necessary
     */
    boolean shootAt(int row, int column) {
        shotsFired++;
        Ship ship = getShipArray()[row][column]; //init the specific ship that is being shot at based on row, colum passed

        if (!ship.getShipType().equals("empty")) { //for ships that aren't empty sea ships
            boolean hit = ship.shootAt(row, column);
            if (hit) {
                hitCount++;
                if (ship.isSunk()) {
                    shipsSunk++;

                }
            }
            return hit;
        } if (ship.getShipType().equals("empty")) { //empty see ships will be marked with an X by having it's hit array updated in .setHit()
            ship.setHit(true);
        }
        return false;
    }

    /**
     *
     * @return total # of shots fired as an int
     */
    int getShotsFired() {
        return shotsFired;
    }

    /**
     *
     * @return total # of hits as an int
     */
    int getHitCount() {
        return hitCount;
    }

    /**
     *
     * @return total # of ships sunk as an int
     */
    int getShipsSunk() {
        return shipsSunk;
    }

    /**
     *
     * @return true if all 10 ships have been sunk
     */
    boolean isGameOver() {
        if (getShipsSunk() == OCEAN_SIZE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @return ships array so each ship location can be accessed
     */
    Ship[][] getShipArray() {
        return ships;
    }

    /**
     * Prints out the battleship grid after every game and will store/update hits, misses, and sunks as the game progresses
     */
    void print() {

        System.out.print("\t");
        for (int i = 0; i < OCEAN_SIZE; i++) {
            System.out.print(i + "\t"); //print column numbers 0-9
        }
        System.out.println();
        Ship ship; //init ship to store hits, misses, and sunks
        for (int row = 0; row < OCEAN_SIZE; row++) {
            System.out.print(row + "\t"); //print row numbers 0-9
            for (int col = 0; col < OCEAN_SIZE; col++) {
                ship = this.ships[row][col];
                if (ship.isSunk() == true || ship.beenHit(row, col) == true) {
                    System.out.print(ship + "\t"); //if ship is sunk/hit, use ship's overridden toString method
                }
                else {
                    System.out.print("." + "\t"); //place periods in all location of ocean array before any shots are fired
                    //periods will remain in place until a hit, miss, or sunk occurs
                }
            }
            System.out.println();
        }
        System.out.println();

    }


    /**
     * Used for debugging. Prints out the placement of all the ships without hits, misses, or sunks
     * Each ship will be denoted with the first character in its name. For example: "b" for battleship
     */

    void printWithShips() {
        System.out.print("\t");
        for (int i = 0; i < OCEAN_SIZE; i++) {
            System.out.print(i + "\t");} //print column numbers 0-9
        System.out.println();
        Ship ship;
        for (int row = 0; row < OCEAN_SIZE; row++) {
                System.out.print(row + "\t");  //print row numbers 0-9

                //nested for loop that will print the proper string for each ship type so user can see all ship placements
                for (int col = 0; col < OCEAN_SIZE; col++) {
                    ship = ships[row][col];
                    if (ship.getShipType().equals("empty")) {
                        System.out.print(" " + "\t"); //prints empty space if empty sea ship
                    }

                    else if (ship.getShipType().equals("battleship")) {
                        System.out.print("b" + "\t"); //prints "b" if battleship
                    }

                    else if (ship.getShipType().equals("destroyer")) {
                        System.out.print("d" + "\t"); //prints "d" if destroyer ship
                    }

                    else if (ship.getShipType().equals("cruiser")) {
                        System.out.print("c" + "\t"); //prints "c" if cruiser ship
                    }

                    else if (ship.getShipType().equals("submarine")) {
                        System.out.print("s" + "\t"); //prints "s" if submarine
                    }

                }
                System.out.println();
            }

        }

    }




