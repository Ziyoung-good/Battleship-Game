package battleship;

/**
 * This is the Destroyer class, which is a subclass of ship
 * @author Ziyang Luo, Wendi Kuang
 *
 */
public class Destroyer extends Ship {
	/**
	 * Create a variable to store the ship type.
	 */
	private static final String shipType = "Destroyer";

	/**
	 * Construct a Destroyer.
	 * By default, length = 2.
	 */
	public Destroyer() {
		super(2);
	}

	@Override
	/**
	 * Return the ship type
	 * @return the ship type
	 */
	public String getShipType() {
		return Destroyer.shipType;
	}

}
