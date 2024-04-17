package battleship;

public class Cruiser extends Ship {
    static final int CRUISER_LEN = 3; //ship length for hit array
    static final String CRUISER_STRING = "cruiser"; //ship name that will be used for printWithShips

    /**
     * Inherits constructor from Ship class where ship and hit array will be initialized
     */
    public Cruiser() {
        super(CRUISER_LEN);

    }
    @Override
    public String getShipType() {
        return CRUISER_STRING;
    }


}
