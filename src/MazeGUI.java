import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.*;

/**
 * The MazeGUI class represents the graphical user interface for the maze game.
 * It initializes and manages the game elements including the maze, player, timer, and user interactions.
 */
public class MazeGUI {

    private JFrame frame = new JFrame("Maze");
    private JLabel levelFrame = new JLabel("Level");
    private JPanel p = new JPanel(new BorderLayout());
    private JPanel p2 = new JPanel(new BorderLayout());
    private JPanel p3 = new JPanel(new BorderLayout());
    private int level;
    private JLabel timerLabel;
    private Timer gameTimer;
    private int remainingTime = 60;
    private MazeGenerator maze;
    private Player player;

    /**
     * Constructs a MazeGUI instance with the specified level, maze, and player.
     *
     * @param level  The level of the maze game.
     * @param maze   The maze generator used for the game.
     * @param player The player in the maze game.
     */
    public MazeGUI(int level, MazeGenerator maze, Player player) {
        this.level = level;
        this.maze = maze;
        this.player = player;
        initializeGame();
    }

    /**
     * Initializes the maze game by creating the maze, player, and starting the timer.
     */
    private void initializeGame() {
        createMazeAndPlayer();
        setupFrame();
        startTimer();
    }

    /**
     * Creates a new maze and player for the game.
     */
    public void createMazeAndPlayer() {
        maze = new MazeGenerator(level, level);
        player = new Player(maze);
        // gameTimer.start();
    }

    /**
     * Sets up the graphical frame for the maze game, including maze display, buttons, and timer.
     */
    private void setupFrame() {

        p.add(maze, BorderLayout.CENTER);
        //int displayLevel = 1;
        levelFrame.setText("Level " + level);
        p.setFocusable(true);
        frame.add(p, BorderLayout.CENTER);
        frame.add(p2, BorderLayout.SOUTH);
        frame.add(p3, BorderLayout.NORTH);
        p3.add(levelFrame);

        JButton newGame = new JButton("New Game");
        newGame.setFocusable(false);
        p2.add(newGame, BorderLayout.CENTER);

        MazeController c = new MazeController(maze, player);
        newGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                createMazeAndPlayer();
                c.actionPerformed(ae, level + 1);
            }
        });

        p.addKeyListener(new MazeController(maze, player));
        timerLabel = new JLabel(formatTime(remainingTime));
        p3.add(timerLabel, BorderLayout.NORTH);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    /**
     * Starts the timer for the game countdown.
     */
    private void startTimer() {
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainingTime--;
                timerLabel.setText(formatTime(remainingTime));

                if (remainingTime == 60) {
                    timerLabel.setForeground(Color.RED);
                }

                if (remainingTime <= 0) {
                    gameTimer.stop();

                    int choice = JOptionPane.showConfirmDialog(
                            null,
                            "Game Over! You ran out of time. Do you want to restart?",
                            "Game Over",
                            JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        remainingTime = 60;
                        MazeController c = new MazeController(maze, player);
                        frame.dispose();

                        createMazeAndPlayer();
                        c.actionPerformed(e, level);
                        // gameTimer.start();
                    } else {
                        frame.dispose();
                    }
                }
            }
        });
        gameTimer.start();
    }

    /**
     * Formats the time in seconds into a human-readable time format (MM:SS).
     *
     * @param seconds The time in seconds.
     * @return The formatted time string.
     */
    private String formatTime(int seconds) {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d", minutes, secs);
    }
}
