import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class MazeData
{
    public static final char ROAD = ' ';
    public static final char WALL = '#';

    private char[][] maze;
    private int ROW, COL;

    private int entranceX, entranceY;
    private int exitX, exitY;

    public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] result;

    public MazeData(String filename)
    {
        if (filename == null)
            throw new IllegalArgumentException("Filename can not be null!");
        Scanner scanner = null;
        try {
            File file = new File(filename);
            if (!file.exists())
                throw new IllegalArgumentException("File " + filename + " doesn't exist");
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");

            String sizeLine = scanner.nextLine();
            String[] rc = sizeLine.trim().split("\\s+");
            ROW = Integer.parseInt(rc[0]);
            COL = Integer.parseInt(rc[1]);

            maze = new char[ROW][COL];
            visited = new boolean[ROW][COL];
            path = new boolean[ROW][COL];
            result = new boolean[ROW][COL];

            for (int i = 0; i < ROW; i++) {
                String line = scanner.nextLine();
                if (line.length() != COL)
                    throw new IllegalArgumentException("Maze file " + filename + " is invalid!");
                for (int j = 0; j < COL; j++)
                    maze[i][j] = line.charAt(j);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) scanner.close();
        }

        entranceX = 1;
        entranceY = 0;
        exitX = ROW - 2;
        exitY = COL - 1;
    }

    public int row() { return ROW; }
    public int col() { return COL; }
    public int getEntranceX() { return entranceX; }
    public int getEntranceY() { return entranceY; }
    public int getExitX() { return exitX; }
    public int getExitY() { return exitY; }
    public char getMaze(int i, int j)
    {
        if (!inArea(i, j))
            throw new IllegalArgumentException("i or j is out of index in getMaze!");
        return maze[i][j];
    }

    public boolean inArea(int x, int y)
    {
        return x >= 0 && x < ROW && y >= 0 && y < COL;
    }

    public void print()
    {
        System.out.println(ROW + " " + COL);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++)
                System.out.print(maze[i][j]);
            System.out.println();
        }
        return;
    }
}
