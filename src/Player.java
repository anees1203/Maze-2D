import java.awt.event.*;
import javax.swing.*;

/**
 * A class that creates all the elements necessary for a player
 * to traverse the maze.
 */
public class Player extends JPanel{
	/**
     * The horizontal position of the player.
     */
	private int x_Position=0;
	/**
     * The vertical position of the player.
     */
	private int y_Position=0;
	/**
     * Indicates whether the player has won the game.
     */
	private boolean hasWon = false;
	
	/**
     * The maze associated with the player.
     */
	private final MazeGenerator maze;
	
	/**
     * Constructs a Player object with the specified maze.
     *
     * @param maze The maze generator that the player interacts with.
     */
	public Player(MazeGenerator maze){
		this.maze = maze;
		x_Position = 1;
		y_Position = 1;
	}	
	
	/**
     * Moves the player to the left in the maze.
     *
     * @param maze The maze generator representing the maze.
     */
	public void moveLeft(MazeGenerator maze){
		if((maze.get(x_Position-1, y_Position) != '#') && (maze.get(x_Position-1, y_Position) != '=')){
			maze.set(x_Position, y_Position, 'O');
			if(maze.get(x_Position-=1, y_Position) == '8'){
				win();
			}else{
				maze.set(x_Position, y_Position, 'X');
			} 	
		}
	}
	
	/**
     * Moves the player to the right in the maze.
     *
     * @param maze The maze generator representing the maze.
     */
	public void moveRight(MazeGenerator maze){
		if((maze.get(x_Position+1, y_Position) != '#') && (maze.get(x_Position+1, y_Position) != '=')){
			maze.set(x_Position, y_Position, 'O');
			if(maze.get(x_Position+=1, y_Position) == '8'){
				win();
			}else{
				maze.set(x_Position, y_Position, 'X');
			}
		}
	}
	
	/**
     * Moves the player upwards in the maze.
     *
     * @param maze The maze generator representing the maze.
     */
	public void moveUp(MazeGenerator maze){
		if((maze.get(x_Position, y_Position-1) != '#') && (maze.get(x_Position, y_Position-1) != '=')){
			maze.set(x_Position, y_Position, 'O');
			if(maze.get(x_Position, y_Position-=1) == '8'){
				win();
			}else{
				maze.set(x_Position, y_Position, 'X');
			}
		}
	}

	/**
     * Moves the player downwards in the maze.
     *
     * @param maze The maze generator representing the maze.
     */
	public void moveDown(MazeGenerator maze){
		if((maze.get(x_Position, y_Position+1) != '#') && (maze.get(x_Position, y_Position+1) != '=')){
			maze.set(x_Position, y_Position, 'O');
			if(maze.get(x_Position, y_Position+=1) == '8'){
				win();
			}else{
				maze.set(x_Position, y_Position, 'X');
			}
		}
	}

	/**
     * Marks the player's victory and displays a congratulatory message.
     */
	public void win(){
        hasWon = true;
		displayCongratulations(); 
    }

	/**
     * Checks if the player has won the game.
     *
     * @return True if the player has won, false otherwise.
     */
	public boolean hasWon() {
        return hasWon;
    }
	
	/**
     * Displays a congratulatory message to the player.
     */
	private void displayCongratulations() {
        JOptionPane.showMessageDialog(null, "Congratulations! You won!");
    }

	
}