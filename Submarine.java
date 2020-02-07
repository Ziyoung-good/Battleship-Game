package battleship;

/**
 * This is the Submarine class, which is a subclass of ship
 * @author Ziyang Luo, Wendi Kuang
 *
 */
public class Submarine extends Ship {
	/**
	 * Create a variable to store the ship type.
	 */
	private static final String shipType = "Submarine";

	/**
	 * Construct a submarine.
	 * By default, length = 1.
	 */
	public Submarine() {
		super(1);
	}

	@Override
	/**
	 * Return the ship type
	 */
	public String getShipType() {
		// TODO Auto-generated method stub
		return Submarine.shipType;
	}

}
