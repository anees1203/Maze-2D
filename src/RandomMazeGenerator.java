import java.util.Random;

public class RandomMazeGenerator {
    private final int width;
    private final int height;
    private final int[][] maze;
    private final Random random;

    public RandomMazeGenerator(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
        this.random = new Random();

        generateMaze();
    }

    public void generateMaze() {
        // Fill the maze with walls
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = 1;
            }
        }

        // Choose a random starting point
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);

        // Generate the maze using depth-first search
        generateMazeDFS(startX, startY);
    }

    private void generateMazeDFS(int x, int y) {
        // Mark the current cell as part of the path
        maze[y][x] = 0;

        // Create a random order of directions
        int[] directions = {0, 1, 2, 3};
        for (int i = directions.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = directions[index];
            directions[index] = directions[i];
            directions[i] = temp;
        }

        // Visit neighboring cells in random order
        for (int dir : directions) {
            int newX = x;
            int newY = y;

            switch (dir) {
                case 0: // Up
                    newY -= 2;
                    break;
                case 1: // Right
                    newX += 2;
                    break;
                case 2: // Down
                    newY += 2;
                    break;
                case 3: // Left
                    newX -= 2;
                    break;
            }

            // Check if the new cell is within bounds and unvisited
            if (newX > 0 && newX < width - 1 && newY > 0 && newY < height - 1 && maze[newY][newX] == 1) {
                // Remove the wall between the current cell and the new cell
                maze[newY][newX] = 0;
                maze[(newY + y) / 2][(newX + x) / 2] = 0;

                // Recursively visit the new cell
                generateMazeDFS(newX, newY);
            }
        }
    }

    public int[][] getMaze() {
        return maze;
    }
}
