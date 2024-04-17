package battleship;

public class Submarine extends Ship {
    static final int SUB_LEN = 1; //ship length for hit array
    static final String SUBMARINE_STRING = "submarine"; //ship name that will be used for printWithShips

    /**
     * Inherits constructor from Ship class where ship and hit array will be initialized
     */
    public Submarine() {
        super(SUB_LEN);
    }

    @Override
    public String getShipType() {
        return SUBMARINE_STRING;
    }

}
