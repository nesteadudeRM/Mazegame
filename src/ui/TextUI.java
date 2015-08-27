/**
 * 
 */
package ui;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import mazegame.MazeConstants;
import mazegame.MazeGame;
 
/**
 *
 * @author Raunak
 * Representation of TextUI. Plays game in console.
 */
public class TextUI implements UI{
       
        private MazeGame game;
        
        public TextUI(MazeGame game){
        	this.game = game;
        }
        
        @Override
        public void launchGame() {
                BufferedReader br 
                = new BufferedReader(new InputStreamReader(System.in));
                System.out.println(game.getMaze().toString());
                while(game.hasWon() == 0 && !game.isBlocked()){
	                try {
	                    String s = br.readLine(); //read user input
	                    char move = s.charAt(0);
	                    if (move == MazeConstants.P1_DOWN 
	                    		|| move == MazeConstants.P1_LEFT 
	                    		|| move ==MazeConstants.P1_UP  
	                    		|| move == MazeConstants.P1_RIGHT 
	                    		|| move == MazeConstants.P2_DOWN 
	                    		|| move == MazeConstants.P2_LEFT 
	                    		|| move ==MazeConstants.P2_UP  
	                    		|| move == MazeConstants.P2_RIGHT){
	                            game.move(move); //call move method from MazeGame

	                            //print maze after each valid move
	                            System.out.println(game.getMaze().toString());
	                    }
	                } catch (IOException e) {
	                        System.out.println(game.getMaze().toString());
	        }
        }
        }
 
        @Override
        public void displayWinner() {
        if (game.isBlocked()) { // no winners
            System.out.println("Game over! Both players are stuck.");
        } else {
            if (game.hasWon() == 0) { // game is still on
                return;
            } else if (game.hasWon() == 1) {
                System.out.println("Congratulations Player 1! You won the " 
                		+ "maze in " + game.getPlayerOne().getNumMoves() 
                		+ " moves.");
            } else if (game.hasWon() == 2) {
                System.out.println("Congratulations Player 2! " +
                		"You won the maze in " 
                		+ game.getPlayerTwo().getNumMoves() + " moves.");
            } else { // it's a tie
                System.out.println("It's a tie!");
            }
        }    
        }
}
