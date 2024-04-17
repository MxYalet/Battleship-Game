package battleship;

public class EmptySea extends Ship {

    /**
     * Inherits constructor from Ship class where ship and hit array will be initialized
     */
    public EmptySea() {
        super(1);
    }

    /**
     *
     * @param row wherein the user shot at.
     * @param column wherein the user shot at.
     * @return false always to show nothing was hit. method overridden and inherited from ship class
     */

    @Override
    boolean shootAt(int row, int column) {
        return false;
    }

    /**
     *
     * @return false always to show nothing was sunk. method overridden and inherited from ship class
     */
    @Override
    boolean isSunk() {
        return false;
    }

    /**
     *
     * @return "-" always when empty sea ships are printed out. used when empty sea is fired upon.
     */
    @Override
    public String toString() {
        return "-";
    }

    /**
     *
     * @return "empty" when empty sea ship type is called upon
     */
    @Override
    public String getShipType() {
        return "empty";
    }


}
