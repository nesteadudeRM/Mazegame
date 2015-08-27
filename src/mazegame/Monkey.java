/**
 * 
 */
package mazegame;

/**
 * @author Raunak
 * Representation of a Monkey
 */
public class Monkey extends Sprite implements Moveable{
	
	private int score;
	private int numMoves;
	
	/**
	 * 
	 * @param symbol
	 * @param row
	 * @param col
	 * constructor for Monkey class inherits from Sprite and implements
	 * Moveable
	 */
	public Monkey(char symbol, int row, int col) {
		super(symbol, row, col);
	}
	
	/**
	 * 
	 * @param score
	 * increment the score of Monkey when a banana is eaten
	 */
	public void eatBanana(int score){
		this.score += score;
	}
	
	/**
	 * 
	 * @return the score
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * 
	 * @return number of moves
	 */
	public int getNumMoves(){
		return numMoves;
	}
	/**
	 * @param row
	 * @param col
	 * Change the coordinates of Monkey to row and col
	 * and increment the number of moves
	 */
	@Override
	public void move(int row, int col) {
		this.row = row;
		this.column = col;
		this.numMoves += 1;
	}

}
