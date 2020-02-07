package battleship;

/**
 * This is the EmptySea class, which is a subclass of Ship.
 * @author Ziyang Luo, Wendi Kuang
 *
 */
public class EmptySea extends Ship{
	/**
	 * Create a variable to store the ship type.
	 */
	private String shipType;

	/**
	 * Construct an EmptySea.
	 * By default, length = 1.
	 */
	public EmptySea() {
		super(1);
		this.shipType = "EmptySea";
		this.getHit()[0] = false;
	}

	/**
	 * Check if the EmptySea ship is hit
	 * @param row is the row coordinate
	 * @param column is the column coordinate
	 * @return a boolean value
	 */
	@Override
	boolean shootAt(int row, int column) {
		this.getHit()[0] = true;
		return false;
	}

	/**
	 * EmptySea will never be sunk
	 * @return a boolean to indicate if a ship is sunk.
	 */
	@Override
	boolean isSunk() {
		return false;
	}

	/**
	 * Output a string based on the condition of the EmptySea.
	 * @return a string to the map to record the hitting situation.
	 */
	@Override
	public String toString() {
		return this.getHit()[0]? "-" : ".";
	}


	/**
	 * Return the ship type
	 * @return the ship type
	 */
	@Override
	public String getShipType() {
		return this.shipType;
	}
}
