import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer
{
    private static int DELAY = 40;

    private FractalData data;// 数据
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(int maxDepth, int side)
    {
        // 初始化数据
        data = new FractalData(maxDepth);
//        int sceneWidth = (int)(Math.pow(2, maxDepth));
//        int sceneHeight = (int)(Math.pow(2, maxDepth));
        int sceneWidth = side;
        int sceneHeight = side / 3;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Fractal Basics Visualizer", sceneWidth, sceneHeight);
            frame.addKeyListener(new AlgoKeyListener());
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run()
    {
        setData(data.depth);
    }

    private void setData(int depth)
    {
        if (depth >= 0)
            data.depth = depth;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoKeyListener extends KeyAdapter
    {
        @Override
        public void keyReleased(KeyEvent event)
        {
            if (event.getKeyChar() >= '0' && event.getKeyChar() <= '9')
            {
                int depth = event.getKeyChar() - '0';
                setData(depth);
            }
        }
    }

    public static void main(String[] args)
    {
        int maxDepth = 6;

        AlgoVisualizer visualizer = new AlgoVisualizer(maxDepth, 1000);
    }
}
