/**
 * 
 */
package mazegame;

/**
 * @author Raunak
 * Representation of a Mobile Banana that inherits Banana and implements
 * Moveable
 */
public class MobileBanana extends Banana implements Moveable {
	/**
	 * 
	 * @param symbol
	 * @param row
	 * @param col
	 * @param value
	 * constructor for MobileBanana class
	 */
	public MobileBanana(char symbol, int row, int col, int value) {
		super(symbol, row, col, value);
	}
	/**
	 * @param row
	 * @param col
	 * changes coordinates for MobileBanana to row and col
	 */
	@Override
	public void move(int row, int col) {
		this.row = row;
		this.column = col;
		
	}

}
