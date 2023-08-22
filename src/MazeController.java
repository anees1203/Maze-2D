import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

/**
 * This class handles user input and game actions in the maze game.
 */
public class MazeController extends KeyAdapter {
    private MazeGenerator maze;
    private Player player;

    /**
     * Constructs a MazeController with the specified maze and player.
     *
     * @param maze   The maze generator used in the game.
     * @param player The player object representing the user's character.
     */
    public MazeController(MazeGenerator maze, Player player) {
        this.maze = maze;
        this.player = player;
    }

    /**
     * Called when a key is pressed by the user.
     *
     * @param e The KeyEvent representing the key press event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT) {
            player.moveLeft(maze);
        }
        if (e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT) {
            player.moveRight(maze);
        }
        if (e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP) {
            player.moveUp(maze);
        }
        if (e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN) {
            player.moveDown(maze);
        }
    }

    /**
     * Performs an action when triggered by a GUI element's action event.
     *
     * @param ae    The ActionEvent triggered by a GUI element.
     * @param level The level of the maze game.
     */
    public void actionPerformed(ActionEvent ae, int level) {
        MazeGUI m = new MazeGUI(level, maze, player);
        m.createMazeAndPlayer();
    }

}
