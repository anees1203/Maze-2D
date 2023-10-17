import java.awt.*;
import javax.swing.*;
import java.util.*;

/**
 * This class responsible for creating and managing a maze.
 */
public class MazeGenerator extends JPanel {

	/**
     * The maze data structure.
     */
	private char[][] Maze;

	/**
     * The size of the maze.
     */
	private int size;

	/**
     * The number of remaining tiles in the maze.
     */
	private int remainingTiles;

	/**
     * The scale factor for rendering the maze.
     */
	private int scale=1; 

	/**
     * A list of positions used in maze generation.
     */
	LinkedList<Position> positionList = new LinkedList<Position>();
	
	/**
     * Constructs a maze generator with the specified dimensions.
     *
     * @param x The length of the maze.
     * @param y The height of the maze.
     */
	public MazeGenerator(int x, int y){
		remainingTiles = (x*x);
		x *= 2; 
		y *= 2;
		x++; 
		y++;
		scale = y;	
		Maze = new char [x][y];
		size = x;
		GenerateMaze();
	}	

	/**
     * Generates the maze layout by initializing the cells and walls.
     * The outer walls are represented with '#', inner walls are '=', and free space is 'u'.
     */
	public void GenerateMaze(){
		for (int i=0; i < size; i++){
			for (int k=0; k < size; k++){
				Maze[i][k] = 'u';
			}
		}	
		for (int i=0; i < size; i+=2){
			for (int k=0; k < size; k++){
				Maze[i][k] = '=';
				Maze[k][i] = '=';
			}
		}
		for (int i=0; i < size; i++){
			Maze[i][0] = '#';
			Maze[0][i] = '#';
			Maze[size-1][i] = '#';
			Maze[i][size-1] = '#';
		}
		generate(1,1);
	}

	/**
     * Paints the maze on the graphics canvas.
     *
     * @param g The graphics object used for drawing.
     */
	public void paint(Graphics g){
		super.paint(g);
		int n = 500/(scale+10);
		
		for(int i = 0; i < size; i++){
			for( int k = 0; k < size; k++){
				if((Maze[i][k] == '#')){
					g.setColor(Color.black);
					g.fillRect(i*n, k*n, n, n);
				} else if(Maze[i][k] == '='){
					g.setColor(Color.black);
					g.fillRect(i*n, k*n, n, n);
				} else if(Maze[i][k] == '8'){
					g.setColor(Color.green);
					g.fillRect(i*n, k*n, n, n);
				} else if(Maze[i][k] == 'X'){
					g.setColor(Color.blue);
					g.fillRect(i*n, k*n, n, n);
				}
			}
		}
	}	
	
	/**
     * Gets the value of a cell in the maze.
     *
     * @param x The x-coordinate of the cell.
     * @param y The y-coordinate of the cell.
     * @return The value of the cell.
     */
	public char get(int x, int y){
		return Maze[x][y];
	}
	
	/**
     * Sets the value of a cell in the maze and triggers a repaint.
     *
     * @param x     The x-coordinate of the cell.
     * @param y     The y-coordinate of the cell.
     * @param value The value to set in the cell.
     */
	public void set(int x, int y, char value){
		Maze[x][y] = value;
		repaint();
	}
	
	/**
     * Prints the current state of the maze to the console.
     */
	public void printBoard(){
		for (int i=0; i < size; i++){
			for (int k=0; k < size; k++){
				System.out.print(Maze[i][k]);
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	/**
     * Updates and retrieves the possible movement directions from a given tile.
     *
     * @param tile The tile for which to update directions.
     * @return An array of characters representing the possible directions.
     */
	public char[] updateDirection(Position tile){
		char north=0,south=0,east=0,west=0;
		
		if (canMoveEast(tile)) {
            east = get(tile.getX(), tile.getY() + 2);
        }
        if (canMoveWest(tile)) {
            west = get(tile.getX(), tile.getY() - 2);
        }
        if (canMoveNorth(tile)) {
            north = get(tile.getX() - 2, tile.getY());
        }
        if (canMoveSouth(tile)) {
            south = get(tile.getX() + 2, tile.getY());
        }		
		char direction[] = {west,east,south,north};
		return direction;
	}


	/**
     * Checks if the player can move east from a given position.
     *
     * @param tile The current position of the player.
     * @return True if the player can move east, false otherwise.
     */
	private boolean canMoveEast(Position tile) {
        return get(tile.getX(), tile.getY() + 1) != '#';
    }

	/**
     * Checks if the player can move west from a given position.
     *
     * @param tile The current position of the player.
     * @return True if the player can move west, false otherwise.
     */
    private boolean canMoveWest(Position tile) {
        return get(tile.getX(), tile.getY() - 1) != '#';
    }

	/**
     * Checks if the player can move north from a given position.
     *
     * @param tile The current position of the player.
     * @return True if the player can move north, false otherwise.
     */
    private boolean canMoveNorth(Position tile) {
        return get(tile.getX() - 1, tile.getY()) != '#';
    }

	/**
     * Checks if the player can move south from a given position.
     *
     * @param tile The current position of the player.
     * @return True if the player can move south, false otherwise.
     */
    private boolean canMoveSouth(Position tile) {
        return get(tile.getX() + 1, tile.getY()) != '#';
    }
	
	/**
     * An array of positions used in maze generation.
     */
	Position posList[] = new Position[(2*(getX()/2))];

	/**
     * The starting position for maze generation.
     */
	Position tile = new Position(5,5);
	
	/**
     * Retrieves the size of the maze (number of cells).
     *
     * @return The size of the maze.
     */
	public int getMazeSize() {
        return size;
    }

	/**
     * Generates the maze using a recursive backtracking algorithm.
     *
     * @param position_x The starting X-coordinate for maze generation.
     * @param position_y The starting Y-coordinate for maze generation.
     */
	public void generate(int position_x, int position_y){
		tile = new Position(position_x,position_y);
		set(tile.getX(),tile.getY(), 'v');
		remainingTiles-=1;
		
		char north=0,south=0,east=0,west=0;
		char direction[] = {west,east,south,north};
		
		direction = updateDirection(tile);

		while(remainingTiles != 0){
			int free = 0;
			if((direction[0] == 'u') || (direction[1] == 'u') || (direction[2] == 'u') || (direction[3] == 'u'))
				free = 1;
			
			Random generator = new Random();
			int random = generator.nextInt(4);
			set(tile.getX(),tile.getY(), 'v');
		
			if((random == 0) && (direction[0] == 'u')){ 
				if (get(tile.getX(),tile.getY()-1) != '#'){
					set(tile.getX(), tile.getY()-1, 'v');
					tile = new Position(tile.getX(), tile.getY()-2);
					positionList.push(tile);
					direction = updateDirection(tile);
					remainingTiles--;
					
				}
			}
			else if((random == 1) && (direction[1] == 'u')){ 
				if (get(tile.getX(),tile.getY()+1) != '#'){
					set(tile.getX(), tile.getY()+1, 'v');
					tile = new Position(tile.getX(), tile.getY()+2);
					positionList.push(tile);
					direction = updateDirection(tile);
					remainingTiles--;
				}
			}
			
			else if((random == 2) && (direction[2] == 'u')){ 
				if (get(tile.getX()+1,tile.getY()) != '#'){
					set(tile.getX()+1, tile.getY(), 'v');
					tile = new Position(tile.getX()+2, tile.getY());
					positionList.push(tile);
					direction = updateDirection(tile);
					remainingTiles--;
					
				}
			}
			else if((random == 3) && (direction[3] == 'u')){ 
				if (get(tile.getX()-1,tile.getY()) != '#'){
					set(tile.getX()-1, tile.getY(), 'v');
					tile = new Position(tile.getX()-2, tile.getY());
					positionList.push(tile);
					direction = updateDirection(tile);
					remainingTiles--;
					
				}
			} else {
				if(free == 0 && positionList.size() != 0){
					tile = positionList.get(positionList.size()-1);
					positionList.remove(positionList.size()-1);
					direction = updateDirection(tile);
				}
			}
		}
		set(tile.getX(),tile.getY(),'8');
		set(1,1,'X');
	}
}