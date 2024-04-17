package battleship;

public class Destroyer extends Ship {
    static final int DESTROYER_LEN = 2; //ship length for hit array
    static final String DESTROYER_STRING = "destroyer"; //ship name that will be used for printWithShips

    /**
     * Inherits constructor from Ship class where ship and hit array will be initialized
     */
    public Destroyer() {
        super(DESTROYER_LEN);
    }
    @Override
    public String getShipType() {
        return DESTROYER_STRING;
    }

}
