package battleship;

import java.util.Scanner;

/**
 * This is the main class for this game.
 * @author Ziyang Luo, Wendi Kuang
 *
 */
public class BattleshipGame {
	/**
	 * The main body of this game.
	 * @param args arguments for main function
	 */
	public static void main(String[] args) {
		// Initiate a scanner to input the coordinates.
		Scanner scanner = new Scanner(System.in);
		// Create a new ocean object for this game.
		Ocean ocean = new Ocean();
		// Place the ships across the entire ocean.
		ocean.placeAllShipsRandomly();
		// Prompt the welcome message
		System.out.println(" Welcome to the battleship Game! ");

		// The game will keep going until the player says no.
		while(true) {
			System.out.println("-------------------------------------");
			System.out.println("Your shot fired: "+ ocean.getShotsFired());
			System.out.println("Your hits: " + ocean.getHitCount());
			System.out.println("The number of sunk ships: " + ocean.getShipsSunk());
			// Print the current situation.
			ocean.print();
			String input;
			// If the input coordinates are invalid, keep asking the user to input a valid coordinates.
			while (true) {
				try {
					System.out.println("Where do you want to shoot? ");
					System.out.print("Use ',' to split the coordinates: ");
					input = scanner.nextLine();
					String[] locations = input.split(",");
					System.out.println("Fired at: (" + locations[0] + ", " + locations[1] + ")");
					if (ocean.shootAt(Integer.parseInt(locations[0]), Integer.parseInt(locations[1]))) {
						System.out.println("------- Hit! -------");
					} else {
						System.out.println("------- Miss! -------");
					}
					break;
				}
				catch (Exception e) {
					System.out.println("\n------- Invalid input! -------");
					continue;
				}
			}
			// Check to see if the game is over, and let the user to decide if he/she wants to play one more time.
			if (ocean.isGameOver()) {
				ocean.print();
				System.out.println("Congrats, you have sunk all 10 ships.");
				System.out.print("Game over! Play again? y or n: ");
				input = scanner.nextLine();
				// If the answer is yes, one more round.
				if (input.equals("y")) {
					ocean = new Ocean();
					ocean.placeAllShipsRandomly();
					System.out.println("\n Welcome to the battleship Game!");
					// If not, end the game.
				} else {
					System.out.println("Your total shot fired: " + ocean.getShotsFired());
					break;
				}
			}
		}
		scanner.close();
	}
}
