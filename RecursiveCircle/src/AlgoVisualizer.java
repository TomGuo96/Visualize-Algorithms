import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer
{
    private static int DELAY = 40;

    private CircleData data;// 数据
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight)
    {
        // 初始化数据
        int R = Math.min(sceneWidth, sceneHeight) / 2 - 2;
        data = new CircleData(sceneWidth / 2, sceneHeight / 2, R, R / 2, 2);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Recursive Circle", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run()
    {
        setData();
    }

    private void setData()
    {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args)
    {
        int sceneWidth = 800;
        int sceneHeight = 800;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
