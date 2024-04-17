package battleship;

import java.util.Scanner;

/**
 * Main class for a Human Vs. Computer version of Battleship.
 * Creates a single instance of Ocean. Places all ships on the board randomly.
 * Prints the grid and gets user input (row and column)
 * for iterating with and playing against the Computer.
 */
public class BattleshipGame {

    /**
     * Launches the game.
     * Creates a single instance of Ocean.
     * Place ships on the ocean.
     * Prints the ocean grid.
     * Calls the gameControl method which processes users input and provides output.
     * @param args Arguments to main method.
     */
    public static void main(String[] args) {
        //Creates an instance of the Ocean class names ocean
        Ocean ocean = new Ocean();

        //calls on the placeAllShipsRandomly method in the Ocean class to place all the enemy ships on the grid
        ocean.placeAllShipsRandomly();

        //This is a method that prints the location of the randomly placed ships on the grid.
        // It is commented out as it is used for debugging purposes.
        ocean.printWithShips();

        //Calls the gameControl below which gets user input and provides outputs which allow the user to play the game until
        //all ships have been sunk.
        BattleshipGame.gameControl(ocean);
    }

    /**
     * This method gets user inputs and processes it.
     * It provides output that allows the user to see what progress they have
     * made in the game (by printing the updated ocean grid) after every shot they make.
     * @param ocean This method calls on the instance of the Ocean class to apply the shots that the user has chosen.
     */
    private static void gameControl(Ocean ocean) {

        //Creates a new instance of the scanner class which will be used to obtain users input
        Scanner scanner = new Scanner(System.in);

        //while loop that runs as loons
        while (!ocean.isGameOver()) {

            //Prints out the game grid which has a length and width of 10 (from row & column 0 to 9)
            ocean.print();
            //Prints out message asking user to enter a row and column separated by a space
            System.out.print("Enter a row and column, separated by a space: ");

            //sets the value of the first input entered by the user to an integer named row
            int row = scanner.nextInt();
            //sets the value of the first input entered by the user to an integer named col
            int col = scanner.nextInt();
            //uses the user inputs in to call the shootAt method from the Ocean class and saves the result under the boolean hit
            boolean hit = ocean.shootAt(row, col);

            //calls the getShipArray method to call get the instance of Ship in the given row/column and saves it under the variable ship
            Ship ship = ocean.getShipArray()[row][col];

            //if statement that runs if the hit boolean is true
            if (hit) {
                //if statement that runs if the ship is sunk (checked by calling the isSunk method) and if the getInitialSunk variable of the ship is true
                if (ship.isSunk() && ship.getInitialSunk()) {
                    //Prints out message informing user that they hit a ship
                    System.out.println("Hit!");

                    //Prints out message informing the user that they just sunk a ship
                    //obtains the type of the ship by calling the getShipType method
                    System.out.println("You just sunk a " + ship.getShipType() + "!");

                    //prints out a new line
                    System.out.println();

                    //sets the setInitialSunk variable for the ship to true
                    ship.setInitialSunk(false);

                    //else if statement that runs if the particular ship that was hit is a "submarine"
                    //the ship type is obtained by calling the getShipType
                } else if (ship.getShipType().equals("submarine")) {

                    //Prints out message informing user that they hit a ship
                    System.out.println("Hit!");

                    //Prints out message informing the user that they just sunk a ship of type submarine
                    System.out.println("You just sunk a submarine!");

                    //sets the setInitialSunk variable for the ship to true
                    ship.setInitialSunk(false);

                    //else statement that runs if the conditions of the prior two if statements were not met
                } else {
                    //Prints out message informing user that they hit a ship
                    System.out.println("Hit!");
                }
            }
            //if statement that runs if the hit boolean is false
            if (!hit) {
                //if statement that runs if the ship is sunk and if it has already been initially sunken (identified by the getInitialSunk variable)
                if (ship.isSunk() && !ship.getInitialSunk()) {
                    //prints out informative message that the ship has already been sunken
                    System.out.println("Ship has already been sunk!");
                    //prints out a new line
                    System.out.println();
                    //else statement that runs if the conditions of the prior if statement were not met
                } else {
                    //prints out informative message that the shot was a miss
                    System.out.println("Miss!");
                    //prints out a new line
                    System.out.println();
                }
            }
        }
        //prints out informative message that informs the user that they have sunken all the enemy ships
        System.out.println("CONGRATULATIONS. YOU SUNK ALL THE ENEMY SHIPS!");
        //prints out informative message that informs the user how many hits they fired were hits
        System.out.println("Total hit count: " + ocean.getHitCount());
        //prints out informative message that informs the user how many shots they fired in total
        System.out.println("Total shots fired: " + ocean.getShotsFired());
    }
}