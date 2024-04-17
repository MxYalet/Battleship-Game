package battleship;

public class Battleship extends Ship {
    static final int BATTLESHIP_LEN = 4; //ship length for hit array
    static final String BATTLESHIP_STRING = "battleship"; //ship name that will be used for printWithShips


    /**
     * Inherits constructor from Ship class where ship and hit array will be initialized
     */
    public Battleship() {
        super(BATTLESHIP_LEN);
    }
    @Override
    public String getShipType() {
        return BATTLESHIP_STRING;
    }


}
