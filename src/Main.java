public class Main {
    public static void main(String[] args) {
        int level = 10;
        MazeGenerator maze = new MazeGenerator(level, level);
        Player player = new Player(maze);
        new MazeGUI(level, maze, player);
    }
}
