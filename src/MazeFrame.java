import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * A class that encompasses the elements of the game board including
 * the maze GUI and the buttons for movement.
 */
public class MazeFrame {

    static JFrame frame = new JFrame("Maze");
    JPanel p = new JPanel(new BorderLayout());
    JPanel p2 = new JPanel(new BorderLayout());
    private int level;

    /**
     * Constructs the maze frame with a board and a player along with
     * all the button listeners for movement.
     *
     * @param level The difficulty level of the maze.
     */
    public MazeFrame(int level) {
        this.level = level;
        createGame();
    }

    /**
     * Reset the game by creating new instances of the board and player.
     */
    private void createGame() {
        Board test = new Board(level, level, level);
        Player player = new Player(test);

        p.add(test, BorderLayout.CENTER);
        p.setFocusable(true);
        frame.add(p, BorderLayout.CENTER);
        frame.add(p2, BorderLayout.SOUTH);

        JButton newGame = new JButton("New Game");
       newGame.setFocusable(false);
        p2.add(newGame, BorderLayout.CENTER);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                //new MazeFrame(level); // Start a new game with the same level
                frame.dispose();
                SwingUtilities.invokeLater(() -> new MazeFrame(level));
            }
        });
        // Other buttons and listeners
        // ...

        p.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT)
                    player.moveLeft(test);
                if (e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT)
                    player.moveRight(test);
                if (e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP)
                    player.moveUp(test);
                if (e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN)
                    player.moveDown(test);
            }
        });

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setBackground(Color.green);
        frame.setVisible(true);
    }


    private void createNewMaze(int level) {
        frame.getContentPane().removeAll(); // Remove the existing maze and buttons
        
        Board test = new Board(level, level, level);
        Player player = new Player(test);
        
        p = new JPanel(new BorderLayout());
        p.add(test, BorderLayout.CENTER);
        p.setFocusable(true);
        frame.add(p, BorderLayout.CENTER);
        
        p2 = new JPanel(new BorderLayout());
        JButton newGame = new JButton("New Game");
        newGame.setFocusable(false);
        p2.add(newGame, BorderLayout.CENTER);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                createNewMaze(level); // Start a new game with the same level
            }
        });
        frame.add(p2, BorderLayout.SOUTH);
        
        p.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (e.getKeyChar() == 'a' || keyCode == KeyEvent.VK_LEFT)
                    player.moveLeft(test);
                if (e.getKeyChar() == 'd' || keyCode == KeyEvent.VK_RIGHT)
                    player.moveRight(test);
                if (e.getKeyChar() == 'w' || keyCode == KeyEvent.VK_UP)
                    player.moveUp(test);
                if (e.getKeyChar() == 's' || keyCode == KeyEvent.VK_DOWN)
                    player.moveDown(test);
            }
        });
        
        frame.revalidate(); // Revalidate the frame to update the changes
        frame.repaint(); // Repaint the frame to reflect the new maze
            
    }


    /**
     * Constructor for maze frame that displays the win frame.
     *
     * @param level The size of the Frame
     * @param money The amount of coins collected by the player.
     */
    public MazeFrame(int level, int money) {
        frame.dispose();
        JFrame frame2 = new JFrame();
        JLabel textLabel = new JLabel("<html>Congratulations!<br>You got " + money + " coin(s)!</html>", JLabel.CENTER);
        textLabel.setFont(new Font("Verdana", Font.BOLD, 32));

        frame2.setBackground(Color.green);
        frame2.add(textLabel, BorderLayout.CENTER);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.setSize(500, 500);
        frame2.setLocationRelativeTo(null);
        frame2.setVisible(true);
    }

   
}
