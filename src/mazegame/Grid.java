/**
 * 
 */
package mazegame;

/**
 * @author Raunak
 * An interface for Grid
 */
public interface Grid<T>{
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @param item
	 * inserts item at position of row and col
	 */
	public void setCell(int row, int col, T item);
	
	/**
	 * 
	 * @param row
	 * @param col
	 * @return item T
	 * returns item T at this position
	 */
	public T getCell(int row, int col);
	
	/**
	 * 
	 * @return number of rows
	 */
	public int getNumRows();
	
	/**
	 * 
	 * @return number of columns
	 */
	public int getNumCols();
	
	/**
	 * 
	 * @param other
	 * @return true if Grid<T> is equal to another grid
	 */
	public boolean equals(Grid<T> other);
	
	/**
	 * 
	 * @return a String representation of grid
	 */
	public String toString();
	
}


