public class MazeData
{
    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private int row, col;
    public char[][] maze;
    public boolean[][] visited;
    public boolean[][] inMist;
    public boolean[][] path;

    private int entranceX, entranceY;
    private int exitX, exitY;

    public MazeData(int row, int col)
    {
        if (row % 2 == 0 || col % 2 == 0)
            throw new IllegalArgumentException("Our Maze Generation Algorithm requires the width and height of the maze are odd numbers");
        this.row = row;
        this.col = col;

        maze = new char[row][col];
        visited = new boolean[row][col];
        inMist = new boolean[row][col];
        path = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i % 2 == 1 && j % 2 == 1)
                    maze[i][j] = ROAD;
                else
                    maze[i][j] = WALL;
                inMist[i][j] = true;
            }
        }

        entranceX = 1;
        entranceY = 0;
        exitX = row - 2;
        exitY = col - 1;

        maze[entranceX][entranceY] = ROAD;
        maze[exitX][exitY] = ROAD;
    }

    public int row() { return row; }
    public int col() { return col; }
    public int getEntranceX() { return entranceX; }
    public int getEntranceY() { return entranceY; }
    public int getExitX() { return exitX; }
    public int getExitY() { return exitY; }

    public boolean inArea(int x, int y)
    {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    public void openMist(int x, int y)
    {
        if (!inArea(x, y))
            throw new IllegalArgumentException("x or y is out of index in openMist function");
        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (inArea(i, j))
                    inMist[i][j] = false;
    }
}
