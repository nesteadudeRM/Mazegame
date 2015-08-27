/**
 * 
 */
package mazegame;

/**
 * @author Raunak
 * A representation of the Sprite object Banana
 */
public class Banana extends Sprite{

	protected int value;
	/**
	 * 
	 * @param symbol symbol of Banana
	 * @param row row of Banana
	 * @param col column of Banana
	 * @param value value of Banana
	 * constructor for Banana and inherits from Sprite class
	 */
	public Banana(char symbol, int row, int col, int value) {
		super(symbol, row, col);
	}
	/**
	 * 
	 * @return value
	 * returns value of Banana
	 */
	public int getValue(){
		return value;
	}
		
	}

