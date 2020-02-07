package battleship;

/**
 * This is the battleship, which is a subclass of ship
 * @author Ziyang Luo, Wendi Kuang
 *
 */
public class Battleship extends Ship {
	/**
	 * This variable stores the ship type
	 */
	private static final String shipType = "Battleship";

	/**
	 * Construct a bettleship.
	 * By default, length = 4.
	 */
	public Battleship() {
		super(4);
	}

	@Override
	/**
	 * Return the ship type
	 * @return the ship type
	 */
	public String getShipType() {
		return Battleship.shipType;
	}
}
