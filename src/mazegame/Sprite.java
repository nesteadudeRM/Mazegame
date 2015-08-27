package mazegame;
/**
 * 
 * @author Raunak
 * Representation of a Sprite class
 */
public abstract class Sprite {
	
	protected char symbol;
	protected int row;
	protected int column;
	
	/**
	 * 
	 * @param symbol
	 * @param row
	 * @param col
	 * Constructor of Sprite
	 */
	public Sprite(char symbol, int row, int col){
		this.symbol = symbol;
		this.row = row;
		this.column = col;
	}

	/**
	 * @return the symbol of Sprite
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * @return the row of Sprite
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column of Sprite
	 */
	public int getCol() {
		return column;
	}

	/**
	 * @return a String representation of a Sprite
	 */
	@Override
	public String toString() {
		return Character.toString(symbol);
	}
	
	
	
	

}
