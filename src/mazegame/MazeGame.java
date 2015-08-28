package mazegame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class that represents the basic functionality of the maze game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class MazeGame {

    /** A random number generator to move the MobileBananas. */
    private Random random;
    
    /** The maze grid. */
    private Grid<Sprite> maze;
    
    /** The first player. */
    private Monkey player1;
    
    /** The second player. */
    private Monkey player2;

    /** The bananas to eat. */
    private List<Banana> bananas;
        
    
    /**
     * @param layoutFileName
     */
    public MazeGame(String layoutFileName) throws IOException {
        random = new Random();
        bananas = new ArrayList<Banana>();
        int[] dimensions = getDimensions(layoutFileName);
        maze = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);
               
        BufferedReader br = new BufferedReader(new FileReader(layoutFileName));
        String line;
        Banana regularB;
        MobileBanana mobileB;
        
		for(int r=0; r<dimensions[0]; r++){ //loops through rows of file
			line = br.readLine(); //creates String from a line in file

			/*loops through columns of file
			 * set the cells of grid with the following Sprites
			*/
			for(int c=0; c<dimensions[1]; c++){ 
				if (line.charAt(c) == MazeConstants.WALL){
					maze.setCell(r, c, new Wall(MazeConstants.WALL, r, c));
					
				}else if(line.charAt(c) == MazeConstants.BANANA){
					regularB = new Banana(MazeConstants.BANANA,r, c, 
							MazeConstants.BANANA_SCORE);
					maze.setCell(r, c, regularB);
					bananas.add(regularB); //add to list of bananas

				}else if(line.charAt(c) == MazeConstants.MOBILE_BANANA){
					mobileB = new MobileBanana(MazeConstants.MOBILE_BANANA, r, c,
							MazeConstants.MOBILE_BANANA_SCORE);
					maze.setCell(r, c, mobileB);
					bananas.add(mobileB); //add to list of bananas
					
				}else if(line.charAt(c) == MazeConstants.P1){
					player1 = new Monkey(MazeConstants.P1, r, c);
					maze.setCell(r, c, player1);
					
				}else if(line.charAt(c) == MazeConstants.P2){
					player2 = new Monkey(MazeConstants.P2, r, c);
					maze.setCell(r, c, player2);
					
				}else if(line.charAt(c) == MazeConstants.VACANT){
					maze.setCell(r, c, 
							new UnvisitedHallway(MazeConstants.VACANT, r, c));

				}
		}
		}
	/* INITIALIZE THE GRID HERE */

        br.close();
    }
   
    private int[] getDimensions(String layoutFileName) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(layoutFileName));

        // find the number of columns
        String nextLine = br.readLine();
        int numCols = nextLine.length();

        int numRows = 0;

        // find the number of rows
        while (nextLine != null && nextLine != "") {
            numRows++;
            nextLine = br.readLine();
        }
        
        br.close();
        return new int[]{numRows, numCols};
    }

	/**
	 * @return the maze
	 */
	public Grid<Sprite> getMaze() {
		return maze;
	}

	/**
	 * @return player1
	 */
	public Monkey getPlayerOne() {
		return player1;
	}

	/**
	 * @return player2
	 */
	public Monkey getPlayerTwo() {
		return player2;
	}
	/**
	 * 
	 * @return the number of rows in maze
	 */
	public int getNumRows() {
		return maze.getNumRows();
	}
	
	/**
	 * 
	 * @return the number of columns in maze
	 */
	public int getNumCols() {
		return maze.getNumCols();
	}
    
	/**
	 * 
	 * @param i row
	 * @param j column
	 * @return Sprite at this location
	 */
	public Sprite get(int i, int j){
		return maze.getCell(i, j);
	}
	
	/**
	 * 
	 * @param nextMove
	 * moves players according to key stroke nextMove
	 */
	public void move(char nextMove){

		/*only move if move is valid
		after each valid move, call moveMobiles method to also move
		mobile bananas if they exist in maze
		*/
		if(validMove(nextMove)){
			//dimensions for positions of players
			int p1Row = player1.getRow();
			int p1Col = player1.getCol();
			int p2Row = player2.getRow();
			int p2Col = player2.getCol();
			if (nextMove == MazeConstants.P1_UP){
				maze.setCell(p1Row + MazeConstants.UP, p1Col, player1);	
				maze.setCell(p1Row, p1Col, 
					new VisitedHallway(MazeConstants.VISITED, p1Row, p1Col));
				player1.move(p1Row + MazeConstants.UP, p1Col);
				moveMobiles();
			}
			else if (nextMove == MazeConstants.P1_RIGHT){
				maze.setCell(p1Row, p1Col + MazeConstants.RIGHT, player1);
				maze.setCell(p1Row, p1Col,
					new VisitedHallway(MazeConstants.VISITED, p1Row, p1Col));
				player1.move(p1Row, p1Col + MazeConstants.RIGHT);
				moveMobiles();
			}
			else if (nextMove == MazeConstants.P1_DOWN){
				maze.setCell(p1Row + MazeConstants.DOWN, p1Col, player1);
				maze.setCell(p1Row, p1Col,
					new VisitedHallway(MazeConstants.VISITED, p1Row, p1Col));
				player1.move(p1Row + MazeConstants.DOWN, p1Col);
				moveMobiles();
			}
			else if (nextMove == MazeConstants.P1_LEFT){
				maze.setCell(p1Row, p1Col + MazeConstants.LEFT, player1);
				maze.setCell(p1Row, p1Col, 
					new VisitedHallway(MazeConstants.VISITED, p1Row, p1Col));
				player1.move(p1Row, p1Col + MazeConstants.LEFT);
				moveMobiles();
			}
			else if (nextMove == MazeConstants.P2_UP){
				maze.setCell(p2Row + MazeConstants.UP, p2Col, player2);
				maze.setCell(p2Row, p2Col, 
					new VisitedHallway(MazeConstants.VISITED, p2Row, p2Col));
				player2.move(p2Row + MazeConstants.UP, p2Col);
				moveMobiles();
			}
			else if(nextMove == MazeConstants.P2_RIGHT){
				maze.setCell(p2Row, p2Col + MazeConstants.RIGHT, player2);	
				maze.setCell(p2Row, p2Col, 
					new VisitedHallway(MazeConstants.VISITED, p2Row, p2Col));
				player2.move(p2Row, p2Col + MazeConstants.RIGHT);
				moveMobiles();
			}
			else if (nextMove == MazeConstants.P2_DOWN){
				maze.setCell(p2Row + MazeConstants.DOWN, p2Col, player2);
				maze.setCell(p2Row, p2Col,
					new VisitedHallway(MazeConstants.VISITED, p2Row, p2Col));
				player2.move(p2Row + MazeConstants.DOWN, p2Col);
				moveMobiles();
			}
			else if(nextMove == MazeConstants.P2_LEFT){
				maze.setCell(p2Row, p2Col + MazeConstants.LEFT, player2);				
				maze.setCell(p2Row, p2Col,
					new VisitedHallway(MazeConstants.VISITED, p2Row, p2Col));
				player2.move(p2Row, p2Col + MazeConstants.LEFT);
				moveMobiles();
			}
		}	
	}
	/**
	 * 
	 * @param nextM
	 * @return true if the move nextM that the player is trying is allowed
	 * checks if move nextM is allowed before moving
	 * helper method for move
	 */
	private boolean validMove(char nextM){
		int p1Row = player1.getRow();
		int p1Col = player1.getCol();
		int p2Row = player2.getRow();
		int p2Col = player2.getCol();

		/*if a banana or mobile banana is in the position the player wants
		to move, it is valid
		so this makes the player eat the banana
		*/
		if (nextM == MazeConstants.P1_UP){	
			if(maze.getCell(p1Row + MazeConstants.UP, 
					p1Col).getSymbol() == MazeConstants.VACANT){
				return true;
			}else if(maze.getCell(p1Row + MazeConstants.UP, 
					p1Col).getSymbol() == MazeConstants.BANANA){
				player1.eatBanana(MazeConstants.BANANA_SCORE);
				bananas.remove(maze.getCell(p1Row + MazeConstants.UP, p1Col));
				return true;
			}else if(maze.getCell(p1Row + MazeConstants.UP, 
					p1Col).getSymbol() == MazeConstants.MOBILE_BANANA){
				player1.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
				bananas.remove(maze.getCell(p1Row + MazeConstants.UP, p1Col));
				return true;
			}else{
				return false;
			}
		}

		else if (nextM == MazeConstants.P1_RIGHT){
			if(maze.getCell(p1Row, p1Col + 
					MazeConstants.RIGHT).getSymbol() == MazeConstants.VACANT){
				return true;
			}else if(maze.getCell(p1Row, p1Col + 
					MazeConstants.RIGHT).getSymbol() == MazeConstants.BANANA){
				player1.eatBanana(MazeConstants.BANANA_SCORE);
				bananas.remove(maze.getCell(p1Row, 
						p1Col + MazeConstants.RIGHT));
				return true;
			}else if(maze.getCell(p1Row, 
					p1Col + MazeConstants.RIGHT).getSymbol() 
							== MazeConstants.MOBILE_BANANA){
				player1.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
				bananas.remove(maze.getCell(p1Row, 
						p1Col + MazeConstants.RIGHT));
				return true;
			}else{
				return false;
			}
		}
		else if (nextM == MazeConstants.P1_DOWN){
			if(maze.getCell(p1Row + MazeConstants.DOWN, 
					p1Col).getSymbol() == MazeConstants.VACANT){
				return true;
			}else if(maze.getCell(p1Row + MazeConstants.DOWN, 
					p1Col).getSymbol() == MazeConstants.BANANA){
				player1.eatBanana(MazeConstants.BANANA_SCORE);
				bananas.remove(maze.getCell(p1Row + MazeConstants.DOWN, 
						p1Col));
				return true;
			}else if(maze.getCell(p1Row + MazeConstants.DOWN, 
					p1Col).getSymbol() == MazeConstants.MOBILE_BANANA){
				player1.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
				bananas.remove(maze.getCell(p1Row + MazeConstants.DOWN, 
						p1Col));
				return true;
			}else{
				return false;
			}
		}
		else if (nextM == MazeConstants.P1_LEFT){
			if(maze.getCell(p1Row, p1Col + 
					MazeConstants.LEFT).getSymbol() == MazeConstants.VACANT){
				return true;
			}else if(maze.getCell(p1Row, p1Col + 
					MazeConstants.LEFT).getSymbol() 
							== MazeConstants.BANANA){
				player1.eatBanana(MazeConstants.BANANA_SCORE);
				bananas.remove(maze.getCell(p1Row, 
						p1Col + MazeConstants.LEFT));
				return true;
			}else if(maze.getCell(p1Row, p1Col + 
					MazeConstants.LEFT).getSymbol() 
							== MazeConstants.MOBILE_BANANA){
				player1.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
				bananas.remove(maze.getCell(p1Row, 
						p1Col + MazeConstants.LEFT));
				return true;
			}else{
				return false;
			}
		}
		else if (nextM == MazeConstants.P2_UP){
			if(maze.getCell(p2Row + MazeConstants.UP, 
					p2Col).getSymbol() == MazeConstants.VACANT){
				return true;
			}else if(maze.getCell(p2Row + MazeConstants.UP, 
					p2Col).getSymbol() == MazeConstants.BANANA){
				player2.eatBanana(MazeConstants.BANANA_SCORE);
				bananas.remove(maze.getCell(p2Row + MazeConstants.UP, p2Col));
				return true;
			}else if(maze.getCell(p2Row + MazeConstants.UP, 
					p2Col).getSymbol() == MazeConstants.MOBILE_BANANA){
				player2.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
				bananas.remove(maze.getCell(p2Row + MazeConstants.UP, p2Col));
				return true;
			}else{
				return false;
			}
		}

		else if (nextM == MazeConstants.P2_RIGHT){
			if(maze.getCell(p2Row, 
					p2Col + MazeConstants.RIGHT).getSymbol() 
							== MazeConstants.VACANT){
				return true;
			}else if(maze.getCell(p2Row, 
					p2Col + MazeConstants.RIGHT).getSymbol() 
							== MazeConstants.BANANA){
				player2.eatBanana(MazeConstants.BANANA_SCORE);
				bananas.remove(maze.getCell(p2Row, 
						p2Col + MazeConstants.RIGHT));
				return true;
			}else if(maze.getCell(p2Row, 
					p2Col + MazeConstants.RIGHT).getSymbol() 
							== MazeConstants.MOBILE_BANANA){
				player2.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
				bananas.remove(maze.getCell(p2Row, 
						p2Col + MazeConstants.RIGHT));
				return true;
			}else{
				return false;
			}
		}
		
		else if (nextM == MazeConstants.P2_DOWN){
			if(maze.getCell(p2Row + MazeConstants.DOWN, 
					p2Col).getSymbol() == MazeConstants.VACANT){
				return true;
			}else if(maze.getCell(p2Row + MazeConstants.DOWN, 
					p2Col).getSymbol() == MazeConstants.BANANA){
				player2.eatBanana(MazeConstants.BANANA_SCORE);
				bananas.remove(maze.getCell(p2Row + MazeConstants.DOWN, 
						p2Col));
				return true;
			}else if(maze.getCell(p2Row + MazeConstants.DOWN, 
					p2Col).getSymbol() == MazeConstants.MOBILE_BANANA){
				player2.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
				bananas.remove(maze.getCell(p2Row + MazeConstants.DOWN, 
						p2Col));
				return true;
			}else{
				return false;
			}
		}
		else if(nextM == MazeConstants.P2_LEFT){
			if(maze.getCell(p2Row, 
					p2Col + MazeConstants.LEFT).getSymbol() 
							== MazeConstants.VACANT){
				return true;
			}else if(maze.getCell(p2Row, 
					p2Col + MazeConstants.LEFT).getSymbol()
							== MazeConstants.BANANA){
				player2.eatBanana(MazeConstants.BANANA_SCORE);
				bananas.remove(maze.getCell(p2Row, 
						p2Col + MazeConstants.LEFT));
				return true;
			}else if(maze.getCell(p2Row, 
					p2Col + MazeConstants.LEFT).getSymbol() 
						== MazeConstants.MOBILE_BANANA){
				player2.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
				bananas.remove(maze.getCell(p2Row, 
						p2Col + MazeConstants.LEFT));
				return true;
			}else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	/**
	 * 
	 * @return number of player that won, 
	 * return 3 if it is a tie, and 0 otherwise(both can't move)
	 */
	public int hasWon(){
		int p1Score = player1.getScore();
		int p2Score = player2.getScore();

		//list bananas must be empty if one of players win or tie
		if (bananas.isEmpty() && (p1Score > p2Score)){
			return 1;
		}else if (bananas.isEmpty() && (p1Score < p2Score)){
			return 2;
		}else if (bananas.isEmpty() && (p1Score == p2Score)){
			return 3;
		}else{
			return 0;
		}
	}
	/**
	 * 
	 * @return true if both players can not move
	 */
	public boolean isBlocked(){

		//uses helper method validMove to check if both players can not move
		boolean p1LeftRight = (validMove(MazeConstants.P1_LEFT) && validMove(MazeConstants.P1_RIGHT));
		boolean p1UpDown = (validMove(MazeConstants.P1_UP) && validMove(MazeConstants.P1_DOWN));
		boolean p2LeftRight = (validMove(MazeConstants.P2_LEFT) && validMove(MazeConstants.P2_RIGHT));
		boolean p2UpDown = (validMove(MazeConstants.P2_UP) && validMove(MazeConstants.P2_DOWN));
		if (!p1LeftRight && !p1UpDown && !p2LeftRight && !p2UpDown){
			return true;
		}else{
			return false;
		}

	}
	/**
	 * move the mobile bananas in random directions
	 */
	private void moveMobiles(){

		//first check if there are any bananas in the list of bananas
		if (bananas.size() > 0){

			//loop through the list
			for(int b = 0; b < bananas.size(); b++){

				//check if banana at position x is a Mobile Banana
				if (bananas.get(b).getSymbol() == MazeConstants.MOBILE_BANANA){
					int mobileRow = bananas.get(b).row;
					int mobileCol = bananas.get(b).column;

					//4 directions so use random numbers between 0 and 3
					int randomMove = random.nextInt(4);

					//randomly move Mobile Banana
					//0 is up, 1 is right, 2 is down and 3 is left
					if (randomMove == 0){
						if (maze.getCell(mobileRow + MazeConstants.UP, 
								mobileCol).getSymbol() 
								== MazeConstants.VACANT){
							maze.setCell(mobileRow + MazeConstants.UP, 
									mobileCol, bananas.get(b));
							maze.setCell(mobileRow, mobileCol, 
									new UnvisitedHallway(MazeConstants.VACANT, 
											mobileRow, mobileCol));
							((MobileBanana) bananas.get(b)).move(mobileRow 
									+ MazeConstants.UP, mobileCol);
						}
					}
					else if(randomMove == 1){
						if (maze.getCell(mobileRow, mobileCol 
								+ MazeConstants.RIGHT).getSymbol() 
								== MazeConstants.VACANT){
							maze.setCell(mobileRow, mobileCol 
									+ MazeConstants.RIGHT, bananas.get(b));
							maze.setCell(bananas.get(b).row, mobileCol, 
									new UnvisitedHallway(MazeConstants.VACANT, 
											mobileRow, mobileCol));
							((MobileBanana) bananas.get(b)).move(mobileRow, 
									mobileCol + MazeConstants.RIGHT);
						}
					}else if(randomMove == 2){
						if (maze.getCell(mobileRow + MazeConstants.DOWN, 
								mobileCol).getSymbol() == MazeConstants.VACANT){
							maze.setCell(mobileRow + MazeConstants.DOWN, 
									mobileCol, bananas.get(b));
							maze.setCell(mobileRow, mobileCol, 
									new UnvisitedHallway(MazeConstants.VACANT, 
											mobileRow, mobileCol));
							((MobileBanana) bananas.get(b)).move(mobileRow 
									+ MazeConstants.DOWN, mobileCol);
						}
					}else{
						if (maze.getCell(mobileRow, mobileCol 
								+ MazeConstants.LEFT).getSymbol() 
								== MazeConstants.VACANT){
							maze.setCell(mobileRow, mobileCol 
									+ MazeConstants.LEFT, bananas.get(b));
							maze.setCell(mobileRow, mobileCol, 
									new UnvisitedHallway(MazeConstants.VACANT, 
											mobileRow, mobileCol));
							((MobileBanana) bananas.get(b)).move(mobileRow, 
									mobileCol + MazeConstants.LEFT);
						}
					
					}
					
				}
			}
		}
	}
}