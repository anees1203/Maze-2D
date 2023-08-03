import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        int width = 30; 
        int height = 30; 

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Random 2D Maze Generator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setPreferredSize(new Dimension(width * RandomMazeGeneratorGUI.CELL_SIZE + RandomMazeGeneratorGUI.BORDER_SIZE,
                    height * RandomMazeGeneratorGUI.CELL_SIZE + RandomMazeGeneratorGUI.BORDER_SIZE));
            RandomMazeGeneratorGUI mazePanel = new RandomMazeGeneratorGUI(width, height);
            frame.add(mazePanel);

            JButton regenerateButton = new JButton("Regenerate Maze");
            regenerateButton.addActionListener(e -> {
                mazePanel.regenerateMaze();
                mazePanel.repaint();
            });
            frame.add(regenerateButton, BorderLayout.SOUTH);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
