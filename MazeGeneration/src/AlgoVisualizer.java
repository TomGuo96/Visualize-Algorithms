import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Stack;

public class AlgoVisualizer
{
    private static int blockSide = 8;
    private static int DELAY = 5;
    private static final int dir[][] = {{-1,0}, {0,1}, {1,0}, {0,-1}};

    private MazeData data;// 数据
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(int row, int col)
    {
        data = new MazeData(row, col);
        int sceneHeight = data.row() * blockSide;
        int sceneWidth = data.col() * blockSide;
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Random Maze Generation Visualization", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run()
    {
        setData(-1, -1);

//        // 深度优先算法
//        go(data.getEntranceX(), data.getEntranceY() + 1);

//        // 深度优先算法（非独归实现）
//        Stack<Position> stack = new Stack<Position>();
//        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
//        stack.push(first);
//        data.visited[first.getX()][first.getY()] = true;
//
//        while (!stack.empty())
//        {
//            Position curPos = stack.pop();
//            for (int i = 0; i < 4; i++) {
//                int newX = curPos.getX() + dir[i][0] * 2;
//                int newY = curPos.getY() + dir[i][1] * 2;
//                if (data.inArea(newX, newY) && !data.visited[newX][newY])
//                {
//                    data.visited[newX][newY] = true;
//                    stack.push(new Position(newX, newY));
//                    setData(curPos.getX() + dir[i][0], curPos.getY() + dir[i][1]);
//                }
//            }
//        }

//        // 广度优先算法
//        LinkedList<Position> queue = new LinkedList<Position>();
//        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
//        queue.addLast(first);
//        data.visited[first.getX()][first.getY()] = true;
//
//        while (queue.size() != 0)
//        {
//            Position curPos = queue.removeFirst();
//            for (int i = 0; i < 4; i++)
//            {
//                int newX = curPos.getX() + dir[i][0] * 2;
//                int newY = curPos.getY() + dir[i][1] * 2;
//                if (data.inArea(newX, newY) && !data.visited[newX][newY])
//                {
//                    data.visited[newX][newY] = true;
//                    queue.addLast(new Position(newX, newY));
//                    setData(curPos.getX() + dir[i][0], curPos.getY() + dir[i][1]);
//                }
//            }
//        }

        // 随机队列
        RandomQueue<Position> queue = new RandomQueue<Position>();
        Position first = new Position(data.getEntranceX(), data.getEntranceY() + 1);
        queue.add(first);
        data.visited[first.getX()][first.getY()] = true;
        data.openMist(first.getX(), first.getY());

        while (queue.size() != 0)
        {
            Position curPos = queue.remove();
            for (int i = 0; i < 4; i++)
            {
                int newX = curPos.getX() + dir[i][0] * 2;
                int newY = curPos.getY() + dir[i][1] * 2;
                if (data.inArea(newX, newY) && !data.visited[newX][newY])
                {
                    data.visited[newX][newY] = true;
                    queue.add(new Position(newX, newY));
                    data.openMist(newX, newY);
                    setData(curPos.getX() + dir[i][0], curPos.getY() + dir[i][1]);
                }
            }
        }
        setData(-1, -1);
    }

    private boolean go(int x, int y)
    {
        if (!data.inArea(x, y))
            throw new IllegalArgumentException("x, y are out of index in go function");

        data.visited[x][y] = true;
        setPathData(x, y, true);

        if (x == data.getExitX() && y == data.getExitY()) return true;

        for (int i = 0; i < 4; i++)
        {
            int newX = x + dir[i][0];
            int newY = y + dir[i][1];
            if (data.inArea(newX, newY) && data.maze[newX][newY] == MazeData.ROAD && !data.visited[newX][newY])
            {
                if (go(newX, newY))
                    return true;
            }
        }

        setPathData(x, y, false);
        return false;
    }

    private void setPathData(int x, int y, boolean isPath)
    {
        if (data.inArea(x, y))
            data.path[x][y] = isPath;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private void setData(int x, int y)
    {
        if (data.inArea(x, y))
            data.maze[x][y] = MazeData.ROAD;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar() == ' ')
            {
                for (int i = 0; i < data.row(); i++) {
                    for (int j = 0; j < data.col(); j++) {
                        data.visited[i][j] = false;
                    }
                }
                new Thread(() -> {
                    go(data.getEntranceX(), data.getEntranceY());
                }).start();
            }
        }
    }

    public static void main(String[] args)
    {
        int col = 101;
        int row = 101;

        AlgoVisualizer visualizer = new AlgoVisualizer(row, col);
    }
}
