package battleship;

import java.util.Random;

/**
 * This class generates an Ocean object
 * @author Ziyang Luo, Wendi Kuang
 *
 */
public class Ocean {
	/**
	 * This ship array will do a book keeping of all ship in the ocean
	 */
	private Ship[][] ships = new Ship[10][10];
	
	/**
	 * Record the number of shot fired
	 */
	private int shotsFired;
	
	/**
	 * Record the number of blocks that belong to any ships that are shot at.
	 */
	private int hitCount;
	
	/**
	 * Record the number of ship that are sunk
	 */
	private int shipsSunk;
	
	/**
	 * Ocean constructor. Initiate hitCount, shotsFired, and shipsSunk. Moreover, it places all ships.
	 */
	public Ocean() {
		this.hitCount = 0;
		this.shotsFired = 0;
		this.shipsSunk = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				EmptySea emptySea = new EmptySea();
				emptySea.placeShipAt(i, j, true, this);
			}
		}
	}
	
	/**
	 * This function will create 10 ships and place them at random locations.
	 */
	void placeAllShipsRandomly(){
		Random random = new Random();
		// Create an vector to store all the ships
		Ship[] ships = new Ship[10];
		// Create different ships
		for (int i = 0; i<10 ; i++) {
			if (i == 0) {
				ships[i] = new Battleship();
			} else if (i < 3) {
				ships[i] = new Cruiser();
			} else if (i < 6) {
				ships[i] = new Destroyer();
			} else if (i < 10) {
				ships[i] = new Submarine();
			}
		}
		
		
		// Place each ship at a random location
		for (Ship ship: ships) {
			// The process will keeping going if a valid location is not found
			while (true) {
				int row1 = random.nextInt(10);
				int column1 = random.nextInt(10);
				boolean horizontal = random.nextBoolean();
				if (ship.okToPlaceShipAt(row1, column1, horizontal, this)) {
					ship.placeShipAt(row1,column1,horizontal, this);
					break;
				}
			}
		}
	}
	
	/**
	 * Determine whether a location in ocean is occupied
	 * @param row the row you want to determine whether it is occupied
	 * @param column the column you want to determine whether it is occupied
	 * @return return true if it is occupied, otherwise return false
	 */
	public boolean isOccupied(int row, int column) {
		// If a block is occupied by EmptySea, then it is not occupied.
		if (this.ships[row][column].getShipType().equals("EmptySea")) {
			return false;
		}
		else {
			return true;
		}
	}
	
	/**
	 * Determine whether you can shoot a ship at this location
	 * @param row the row you want shoot at
	 * @param column the column you want to shoot at 
	 * @return return true  you can shoot a ship at this place, otherwise return false
	 */
	public boolean shootAt(int row, int column) {
		this.shotsFired ++;
		// If shot at a place where there is a valid ship.
		if (isOccupied(row, column)) {
			if (this.ships[row][column].shootAt(row,column)) {
				if (this.ships[row][column].isSunk()) {
					System.out.println("You just sunk a " + this.ships[row][column].getShipType());
					this.shipsSunk++;
			    }
				this.hitCount ++;
				return true;
			}
			return false;
		} else {
			ships[row][column].shootAt(row, column);
		}
	return false;
	}
	
	/**
	 * A getter function that will return the total shots fired.
	 * @return return total shotsfired in this game
	 */
	public int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * A getter function that will return the total hit
	 * @return total number of hit in this game
	 */
	public int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * A getter function that will return total ships that are sunk.
	 * @return the amount of sunk ships in this game
	 */
	public int getShipsSunk() {
		return this.shipsSunk;
	}
	
	/**
	 * A function that will check if the game is over.
	 * @return return true if game is over, otherwise false
	 */
	public boolean isGameOver() {
		return this.shipsSunk == 10;
	}
	
	/**
	 * A function that will return the ship array.
	 * @return return ship array
	 */
	public Ship[][] getShipArray(){
		return this.ships;
	}
	
	/**
	 * A print function that will display the current ocean.
	 */
	public void print() {
		// Instantiate a string for display
		String result = "  ";
		// Display the column coordinate
		for (int i = 0; i < 10; i++) {
			result += (i + " ");
		}
		result += "\n";
		// Display the row coordinate
		for (int i = 0; i < 10; i++) {
			result += (i + " ");
			// Check what to output.
			// If not shot at, output "."
			// If shot at a ship, that has not been sunk, output "X"
			// If shot at a ship, that has been sunk, output "S"
			// If shot at the sea, output "-"
			for (int j = 0; j < 10; j++) {
				if (!this.ships[i][j].wasShoot(i, j)) {
					result += ".";
				} else {
					result += this.ships[i][j].toString();
				}
				result += " ";
			}
			result += "\n";
		}
		System.out.println(result);
	}
}