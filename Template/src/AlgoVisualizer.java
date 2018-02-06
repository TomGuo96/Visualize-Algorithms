import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer
{
    // TODO：创建自己的数据
    private Object data;// 数据
    private static int DELAY = 40;
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight)
    {
        // 初始化数据
        // TODO：初始化数据

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Welcome", sceneWidth, sceneHeight);
            // TODO：添加事件
            frame.addKeyListener(new AlgoKeyListener());
            frame.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run()
    {
        // TODO：编写自己的动画逻辑
    }

    // TODO: 根据自己的情况决定是否实现鼠标键盘等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {}
    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args)
    {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 10;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight);
    }
}
