package battleship;

/**
 * Class that defines the characteristics common to all ships.
 * Contains several instance variables, a constructor, getter and setters as well as abstract and other methods.
 * This class is an abstract class as ir has 5 subclasses of ships.
 */
public abstract class Ship {

    //Instance Variables
    private int bowRow; //The row that contains the bow (front part of the ship)

    private int bowColumn; //The column that contains the bow (front part of the ship)

    private int length; //The length of the ship (equivalent to number of squares on the two-dimensional grid)

    private boolean horizontal; //A boolean that represents whether the ship is going to be placed horizontally or vertically

    private boolean[] hit; //An array of booleans that indicate whether a specific location of the ship has been hit or not

    private boolean initialSunk = true; //Boolean used to identify if a ship was sunken for the first time.
    // This variable is useful when the user hits an already sunken ship in order to notify the user the ship has already been sunken

    //Constructor

    /**
     * This constructor sets the length property of the particular ship and initializes
     * the hit array based on that length.
     * @param length is used to se the length of the instance of ship.
     */
    public Ship (int length) {
        //Sets the given length of the ship to the length of the instance of ship
        this.length = length;
        //creates a new array of booleans with the same length as the length of the ship (length being the number of squares it occupies in the ocean grid)
        hit = new boolean[this.getLength()];
        //for statement which sets the initial value of the boolean(s) in the array to false
        for (int i = 0; i < hit.length; i++ ) {
            //sets the value of the boolean being iterated over to false
            hit[i] = false;
        }
    }

    //Getter Methods

    /**
     * Returns the ship length.
     * @return the ship length.
     */
    public int getLength() {
        //Returns the instance variable length for a given ship
        return length;
    }

    /**
     * Returns the row corresponding to the position of the bow.
     * @return the row corresponding to the position of the bow.
     */
    public int getBowRow() {
        //Returns the instance variable bowRow for a given ship
        return bowRow;
    }

    /**
     * Returns the bow column location.
     * @return the bow column location.
     */
    public int getBowColumn() {
        //Returns the instance variable bowColumn for a given ship
        return bowColumn;
    }

    /**
     * Returns the hit array.
     * @return the hit array.
     */
    public boolean[] getHit() {
        //Returns the ship array for a given ship
        return hit;
    }

    /**
     * Returns the boolean value that identifies if the ship has already been sunken for a first time.
     * @return the boolean value of initialSunk.
     */
    public boolean getInitialSunk () {
        //Returns the boolean value of initialSunk for a given ship
        return this.initialSunk;
    }

    /**
     * Returns whether the ship is horizontal or not.
     * @return whether the ship is horizontal or not.
     */
    public boolean isHorizontal() {
        //Returns the boolean value horizontal for a given ship
        return horizontal;
    }

    //Setter Methods

    /**
     * Sets the value of bowRow
     * @param row integer used to set the value of the bowRow.
     */
    public void setBowRow(int row) {
        //Sets the given int row to the bowRow instance variable of the ship
        this.bowRow = row;
    }

    /**
     * Sets the value of bowColumn.
     * @param column integer used to set the value of the bowColumn.
     */
    public void setBowColumn(int column) {
        //Sets the given int column to the bowColumn instance variable of the ship
        this.bowColumn = column;
    }

    /**
     * Sets the value of the instance variable horizontal.
     * @param horizontal boolean used to set the value of the variable horizontal.
     */
    public void setHorizontal(boolean horizontal) {
        //Sets the given boolean horizontal to the horizontal instance variable of the ship
        this.horizontal = horizontal;
    }

    /**
     * Sets the value of the hit array at index zero to the value of the given boolean.
     * Used in the Ocean class when an instance of emptySea is shot at (as this type of ship has a length of one).
     * @param hit boolean used to set the value of the hit array of the ship.
     */
    public void setHit(boolean hit) {
        //Sets the value of the first index of the hit array for a given ship to the given boolean value
        this.getHit()[0] = hit;
    }

    /**
     * Sets the value of the boolean setInitialSunk to the given boolean value.
     * @param b boolean used to set the value of the setInitialSunk instance variable.
     */
    public void setInitialSunk (boolean b) {
        //Sets the value of the initialSunk instance variable of a given ship to the given boolean value
        this.initialSunk = b;
    }

    //Abstract Methods

    /**
     * Abstract method that returns the given ship type. Note that the method is not defined in this class as
     * the method is overwritten in every subclass of ship.
     * @return the type of the given ship.
     */
    public abstract String getShipType();

    //Other Methods

    /**
     * Based on the given row, column, and orientation, returns true if it is okay to put a
     * ship of this length with its bow in this location; false otherwise. The ship must not
     * overlap another ship, or touch another ship (vertically, horizontally, or diagonally),
     * and it must not stick out beyond the array. Does not actually change either the
     * ship or the Ocean - it just says if it is legal to do so.
     * @param row wherein the front of the given ship will try to be placed.
     * @param column wherein the front of the given ship will try to be placed.
     * @param horizontal boolean that determines if the given ship will be placed horizontally(true) or vertically(false).
     * @param ocean the two-dimensional grid wherein the given ship will try to be placed.
     * @return true if the ship can be placed in the provided location and direction, false if it does not meet the above stated conditions.
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        //if statement that proceeds if the ship direction is horizontal
        if (horizontal) {
            //if statement that proceeds only if the given column wherein the front of the ship will be placed minus the
            //length of the ship plus 1 (1 is added back as this is equals the column of the back of a ship for vertically placed ships)
            //is less than zero (which would mean the tail of the ship has a value that is out of bounds)
            if((column - this.length + 1) < 0) {
                //returns false which means that the given ship cannot be placed in the suggested position
                return false;
            }
            //for statement that iterates over one row below and one row above (and everything in between)
            // the provided row for horizontally placed ships
            for (int i = row - 1; i <= row + 1; i++) {
                //for statement that iterates over one column before and one column after (and everything in between)
                // the provided column for horizontally placed ships
                for (int j = column - this.length; j <= column + 1; j++) {
                    //if statement that proceeds if the row and column combination is out of bounds
                    //which is defined as a row/column less than 0 or greater than or equal to the board size
                    if ((i < 0) || (i >= Ocean.OCEAN_SIZE) || (j < 0) || ((j >= Ocean.OCEAN_SIZE))) {
                        //for these out of bound cases, the program will continue checking the other row/column combinations
                        //and will not cause the program to throw an out-of-bounds exception
                        continue;
                    }
                    //checks if the given row/column combination is already occupied by another ship (excluding emptySea instances)
                    if (ocean.isOccupied(i, j)) {
                        //returns false which means that the given ship cannot be placed in the suggested position
                        return false;
                    }
                }
            }
        }
        //if statement that proceeds if the ship direction is vertical (a false horizontal value)
        if (!horizontal) {
            //if statement that proceeds only if the given row wherein the front of the ship will be placed minus the
            //length of the ship plus 1 (1 is added back as this is equals the row of the back of a ship for vertical placed ships)
            //is less than zero (which would mean the tail of the ship has a value that is out of bounds)
            if((row - this.length + 1) < 0) {
                //returns false which means that the given ship cannot be placed in the suggested position
                return false;
            }
            //for statement that iterates over one row before and one row after (and everything in between)
            // the provided row for vertically placed ships
            for (int i = row - this.length; i <= row + 1; i++) {
                //for statement that iterates over one column below and one column above (and everything in between)
                // the provided column for vertically placed ships
                for (int j = column -1; j <= column + 1; j++) {
                    //if statement that proceeds if the row and column combination is out of bounds
                    //which is defined as a row/column less than 0 or greater than or equal to the board size
                    if ((i < 0) || (i >= Ocean.OCEAN_SIZE) || (j < 0) || ((j >= Ocean.OCEAN_SIZE))) {
                        //for these out of bound cases, the program will continue checking the other row/column combinations
                        //and will not cause the program to throw an out-of-bounds exception
                        continue;
                    }
                    //checks if the given row/column combination is already occupied by another ship (excluding emptySea instances)
                    if (ocean.isOccupied(i, j)) {
                        //returns false which means that the given ship cannot be placed in the suggested position
                        return false;
                    }
                }
            }

        }

        //If statement that runs if the given row or colum is less than zero or greater than the board size
        if (row >= Ocean.OCEAN_SIZE || row < 0 || column >= Ocean.OCEAN_SIZE || column < 0) {
            //returns false which means that the given ship cannot be placed in the suggested position
            return false;
        }
        //this method returns true if all the prior if statements are not met
        //this means that the given ship can be placed in the given location and direction
        return true;
    }

    /**
     * Places the given type of ship in the given row, column, horizontal/vertical position, and the ocean grid.
     * @param row wherein the front of the given ship will be placed.
     * @param column wherein the front of the given ship will be placed.
     * @param horizontal boolean that determines if the given ship will be placed horizontally(true) or vertically(false).
     * @param ocean the two-dimensional grid wherein the given ship will be places.
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean)  {
        //sets the givens ships bowRow to the given row
        setBowRow(row);
        //sets the givens ships bowColumn to the given column
        setBowColumn(column);
        //sets the given ships horizontal instance value to the given boolean value
        setHorizontal(horizontal);
        //uses the getShipArray method to set the instance of the Ocean class ocean to the instance variable ship
        Ship[][] ships = ocean.getShipArray();
        //for statement that iterates over the length of the given ship
        for (int i = 0; i < this.length; i++) {
            //if statement that proceeds if the given ship to be placed is horizontal
            if (horizontal == true) {
                //sets value of the row/column combination in the ocean grid to an instance of the given ship type
                //note that as this is a horizontally placed ship, the ship is being placed in the location of the given bowRow
                //and bowColumn as well as n (n being the length of the type of ship) many times to the left of
                // the front (only if the ship has a length greater than one)
                //this is because horizontally placed ships are placed from east to west in the grid
                ships[row][column - i] = this;
            }
            //else statement that proceeds if the given ship to be placed is vertical (only other option if the above if statement is not met)
            else {
                //sets value of the row/column combination in the ocean grid to an instance of the given ship type
                //note that as this is a vertically placed ship, the ship is being placed in the location of the given bowRow
                //and bowColumn as well as n (n being the length of the type of ship) many times to the top of
                // the front (only if the ship has a length greater than one)
                //this is because vertically placed ships are placed from south to north in the grid
                ships[row - i][column] =this;
            }
        }
    }


    /**
     * If a part of the ship occupies the given row and column, and the ship hasn’t been
     * sunk, this method marks that part of the ship as “hit” (in the hit array, index 0 indicates the
     * bow) and return true; otherwise it returns false.
     * @param row wherein the user shot at.
     * @param column wherein the user shot at.
     * @return true if the given ship has not been sunk but has been hit. Returns false if the ship has already been sunken.
     */
    boolean shootAt(int row, int column) {
        //if statement that proceeds if the given ship has not been sunk, if the ship is horizontal in direction,
        //if the given row is the same as the ships bowRow and if the column is less than or equal to the bowColumn
        if (!isSunk() && this.isHorizontal() && row == this.getBowRow() && column <= this.getBowColumn()) {
            //calculates hit array index for the given ship and the given place that was "shot at"
            //this is done by subtracting the given column from the ships bowColumn for horizontal ships
            //if the ship has a length of 1 the resulting index is zero (which is the same index as the front of the ship in the hit array).
            int index = this.getBowColumn() - column;
            //sets the value of the ships hit array for the given index
            // to true (which signifies that the ship has been hit at the given location).
            this.getHit()[index] = true;
            //returns true, which means that the given location changed the value of the ships array (meaning there was a successful hit).
            return true;
            //else if statement that proceeds if the given ship has not been sunk, if the ship is vertical in direction,
            //if the given column is the same as the ships bowColumn and if the row is less than or equal to the bowRow
        } else if (!isSunk() && !this.isHorizontal() && column == this.getBowColumn() && row <= this.getBowRow()) {
            //calculates hit array index for the given ship and the given place that was "shot at"
            //this is done by subtracting the given row from the ships bowRow for vertical ships
            //if the ship has a length of 1 the resulting index is zero (which is the same index as the front of the ship in the hit array).
            int index = this.getBowRow() - row;
            //sets the value of the ships hit array for the given index
            // to true (which signifies that the ship has been hit at the given location).
            this.getHit()[index] = true;
            //returns true, which means that the given location changed the value of the ships array (meaning there was a successful hit).
            return true;
        }
        //if all the above if statements are not met, this method returns false which signifies that a "hit" was not made
        return false;
    }

    /**
     * Returns true if every part of the ship has been hit, false otherwise.
     * @return true is every part (square in the grid) of the ship has been "hit".
     * This means that every boolean in the ships hit array has been set to true.
     */
    boolean isSunk() {
        //for statement that iterates over evert index of the ships hit array (based on the ship length)
        for (boolean i : this.getHit()) {
            //if statement that proceeds if a location in the hit array is set to false
            if (i == false) {
                //if a location in the hit array is set to false this method returns false
                return false;
            }
        }
        //if all the indexes in the ships hit array have been analyzed and none is still set to false, this method returns true
        return true;
    }

    /**
     * Helper method implemented to search through the givens ships hit array and return true if the given location has been hit.
     * @param row of the given ship that will be analyzed to see if it has been "hit".
     * @param column of the given ship that will be analyzed to see if it has been "hit".
     * @return true if the location has been hit, false if it has not.
     */
    boolean beenHit (int row, int column) {
        //if statement that proceeds if the given ship in the given location is horizontally placed
        if (this.horizontal) {
            //calculates hit array index for the given ship and the given place that was "shot at"
            //this is done by subtracting the given column from the ships bowColumn for horizontal ships
            //if the ship has a length of 1 the resulting index is zero (which is the same index as the front of the ship in the hit array).
            int index =this.getBowColumn() - column;
            //returns the value of the ships hit array for the given index (based on the given row/column combination).
            return this.hit[index];
            //else statement that proceeds if the given ship is vertically placed
        } else {
            //calculates hit array index for the given ship and the given place that was "shot at"
            //this is done by subtracting the given row from the ships bowRow for vertical ships
            //if the ship has a length of 1 the resulting index is zero (which is the same index as the front of the ship in the hit array).
            int index = this.getBowRow() - row;
            //returns the value of the ships hit array for the given index (based on the given row/column combination).
            return this.hit[index];
        }
    }

    /**
     * Returns a single-character String to use in the Ocean’s print method. This method
     * returns ”s” if the ship has been sunk and ”x” if it has not been sunk. This
     * method is used to print out locations in the ocean that have been shot at.
     * @return ”s” if the given ship has been sunk or ”x” if it has been hit but not yet sunk.
     */
    @Override
    public String toString() {
        //if statement that proceeds if the given ship has not yet been sunk
        if (!this.isSunk()) {
            //returns ”x” if it has been hit but not yet sunk
            return "x";
            //else statement that proceeds if the given ship has not been sunken yet
        } else {
            //returns ”s” if the given ship has been sunk
            return "s";
        }
    }
}