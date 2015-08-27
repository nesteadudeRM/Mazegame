/**
 * 
 */
package mazegame;

/**
 * @author Raunak
 * representation of an ArrayGrid class that implements Grid
 */
public class ArrayGrid<T> implements Grid<T>{
	
	private int numRows;
	private int numCols;
	private T[][] grid;
	
	/**
	 * 
	 * @param numRows
	 * @param numCols
	 * Constructor for ArrayGrid
	 */

	@SuppressWarnings("unchecked")
	public ArrayGrid(int numRows, int numCols){
		this.numRows = numRows;
		this.numCols = numCols;
		this.grid = (T[][]) new Object[numRows][numCols];
	}
	/**
	 * @param row
	 * @param col
	 * @param item
	 * inserts T item at given row and col of grid
	 */

	@Override
	public void setCell(int row, int col, T item) {
		grid[row][col] = item;
		
	}
	/**
	 * @param row
	 * @param col
	 * @return item T returns item T from this row and col
	 */
	@Override
	public T getCell(int row, int col) {
		return grid[row][col];
		
	}
	/**
	 * return number of rows
	 */
	@Override
	public int getNumRows() {
		return numRows;
	}
	/**
	 * return number of columns
	 */
	@Override
	public int getNumCols() {
		return numCols;
	}
	/**
	 * @param other Grid<T>
	 * return true if grid equals other Grid<T>
	 */
	@Override
	public boolean equals(Grid<T> other) {
		
		return grid.equals(other);
	}

	/** 
	 * return a String representation of the grid
	 */
	@Override
	public String toString() {
		String createGrid = "";
		for(int x=0; x<numRows; x++){

			for(int y=0; y<numCols; y++){
				createGrid += grid[x][y];
				
			}
			createGrid += "\n";
		}
		
		return createGrid;
	}

}
