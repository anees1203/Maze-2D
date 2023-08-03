import javax.swing.*;
import java.awt.*;

public class RandomMazeGeneratorGUI extends JPanel {
    static final int CELL_SIZE = 30;
    static final int BORDER_SIZE = 2;

    private final int width;
    private final int height;
    private final int[][] maze;
    private final RandomMazeGenerator mazeGenerator;

    public RandomMazeGeneratorGUI(int width, int height) {
        this.width = width;
        this.height = height;
        this.mazeGenerator = new RandomMazeGenerator(width, height);
        this.maze = mazeGenerator.getMaze();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int cellValue = maze[y][x];
                Color color = cellValue == 1 ? Color.BLACK : Color.WHITE;
                g.setColor(color);
                g.fillRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public void regenerateMaze() {
        mazeGenerator.generateMaze();
        System.arraycopy(mazeGenerator.getMaze(), 0, maze, 0, mazeGenerator.getMaze().length);
    }
}
