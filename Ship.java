package battleship;

/**
 * This is the ship class.
 * @author Ziyang Luo, Wendi Kuang
 *
 */
abstract class Ship {
	/**
	 * A variable to store the row coordinate
	 */
	private int bowRow;
	
	/**
	 * A variable to store the column coordinate
	 */
	private int bowColumn;
	
	/**
	 * A variable to store the length of ship
	 */
	private int length;
	
	/**
	 * A variable to store the orientation
	 */
	private boolean horizontal;
	
	/**
	 * A variable to store the hitting information
	 */
	private boolean[] hit;

	/**
	 * Ship constructor: set the length of a ship and the hit vector to record the damage situation of it
	 * @param length: length of a ship
	 */
	public Ship(int length) {
		this.length = length;
		this.hit = new boolean[length];
	}

	/**
	 * return the length of the ship
	 * @return the length of a ship
	 */
	public int getLength() {
		return this.length;
	}

	/**
	 * return the bow row location
	 * @return the bow row coordinate
	 */
	public int getBowRow() {
		return this.bowRow;
	}

	/**
	 * return the bow column location
	 * @return the bow column coordinate
	 */
	public int getBowColumn() {
		return this.bowColumn;
	}

	/**
	 * return the hit array
	 * @return the hit array
	 */
	public boolean[] getHit() {
		return this.hit;
	}

	/**
	 * check whether the ship is horizontal placed
	 * @return a boolean value indicating the orientation
	 */
	public boolean isHorizontal() {
		return this.horizontal;
	}

	/**
	 * sets the value of bowRow
	 * @param row: This is the place to specify the row coordinate
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}

	/**
	 * sets the value bowColumn
	 * @param column: This is the place to specify the column coordinate
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}

	/**
	 * sets the value of the instance variable horizontal
	 * @param horizontal: set if the ship is horizontal or not.
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}

	/**
	 * This is an abstract method and will be implemented later.
	 * @return the type of ship as a string.
	 */
	public abstract String getShipType();

	/**
	 * This function determines if it is OK to place the ship at a certain location.
	 * @param row the row coordinate
	 * @param column the column coordinate
	 * @param horizontal a boolean indicating horizontal or not
	 * @param ocean ocean object
	 * @return a boolean indicating if you may place a ship here
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// Horizontal ship
		if (horizontal) {
			if (column < this.length - 1) return false;
			// Check the surrounding blocks and the current blocks
			for (int i = row + 1; i >= row - 1; i--) {
				for (int j = column + 1; j >= column - this.length; j--) {
					try {
						// If any blocks are not the EmptySea, there must be a ship there
						if (!ocean.getShipArray()[i][j].getShipType().equals("EmptySea")) return false;
					} catch (Exception e) {continue;}
				}
			}
		}
		// Vertical ship
		if (!horizontal) {
			if (row < this.length - 1) return false;
			// Check the surrounding blocks and the current blocks
			for (int j = column + 1; j >= column - 1; j--) {
				for (int i = row + 1; i >= row - this.length; i--) {
					try {
						// If any blocks are not the EmptySea, there must be a ship there
						if (!ocean.getShipArray()[i][j].getShipType().equals("EmptySea")) return false;
					} catch (Exception e) {continue;}
				}
			}
		}
		// All other situations should return true
		return true;
	}

	/**
	 * This function will put the ship at a certain location within the ship array
	 * @param row row coordinate
	 * @param column column coordinate
	 * @param horizontal orientation
	 * @param ocean the ocean object
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// Determine whether it is a valid location
		if (this.okToPlaceShipAt(row, column, horizontal, ocean)) {
			// Set the bow location and orientation.
			this.setBowRow(row);
			this.setBowColumn(column);
			this.setHorizontal(horizontal);
			// For horizontal ships
			if (horizontal) {
				for (int j = column; j > column - this.length; j--) {
					ocean.getShipArray()[row][j] = this;
				}
				// For vertical ships
			} else {
				for (int i = row; i > row - this.length; i--) {
					ocean.getShipArray()[i][column] = this;
				}
			}
		}
		return;
	}

	/**
	 * This function will check if the ship is shot, and where the shot is.
	 * @param row: row coordinate
	 * @param column: column coordinate
	 * @return a boolean to indicate if a shot can be fired here.
	 */
	boolean shootAt(int row, int column) {
		// Check if the ship has already been sunk.
		if (!this.isSunk()) {
			// If not, find the location that has been shot. Also, let the player know that the ship was shot.
			if (this.isHorizontal() && row == this.getBowRow() && column <= this.getBowColumn() && column >= this.getBowColumn() - this.getLength()) {
				this.hit[this.bowColumn - column] = true;
				return true;
			} else if (!this.isHorizontal() && column == this.getBowColumn() && row <= this.getBowRow() && row >= this.getBowRow() - this.getLength()) {
				this.hit[this.bowRow - row] = true;
				return true;
			}
		}
		// Otherwise, let the player know that the ship was not shot.
		return false;
	}

	/**
	 * The function will check if a ship has been sunk.
	 * @return True/False
	 */
	boolean isSunk() {
		// This integer variable will record the times that a ship was shot at.
		int countHit = 0;
		for (boolean i : this.hit) {
			if (i) countHit ++;
		}
		// If all parts were shot at, the ship is sunk
		if (countHit == this.length) {
			return true;
		}
		return false;
	}

	/**
	 * Return certain string based on the condition of the ship
	 */
	@Override
	public String toString() {
		return this.isSunk()? "s" : "x";
	}

	/**
	 * This function will check if a ship was shot at certain location
	 * @param row: row coordinate
	 * @param column: column coordinate
	 * @return the boolean value about if a ship has been shot.
	 */
	public boolean wasShoot(int row, int column) {
		// For horizontal ship
		if(this.horizontal) {
			return this.hit[this.bowColumn - column];
			// For vertical ship
		} else {
			return this.hit[this.bowRow - row];
		}
	}
}
