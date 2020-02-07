package battleship;

/**
 * This is the Cruiser class, which is a subclass of ship
 * @author Ziyang Luo, Wendi Kuang
 *
 */
public class Cruiser extends Ship {
	/**
	 * Create a variable to store the ship type.
	 */
	private static final String shipType = "Cruiser";

	/**
	 * Construct a cruiser.
	 * By default length = 3.
	 */
	public Cruiser() {
		super(3);
	}

	@Override
	/**
	 * Return the ship type
	 * @return the ship type
	 */
	public String getShipType() {
		return Cruiser.shipType;
	}

}
