import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer
{
    private static int DELAY = 40;
    private static int blockSide = 80;

    private GameData data;// 数据
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(String filename)
    {
        // 初始化数据
        data = new GameData(filename);
        int sceneWidth = data.M() * blockSide;
        int sceneHeight = data.N() * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Move the Box", sceneWidth, sceneHeight);
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
        setData();
        if (data.solve())
            System.out.println("The game has a solution.");
        else
            System.out.println("The game does NOT have a solution.");
    }

    private void setData()
    {
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    // TODO: 根据自己的情况决定是否实现鼠标键盘等交互事件监听器类
    private class AlgoKeyListener extends KeyAdapter {}
    private class AlgoMouseListener extends MouseAdapter {}

    public static void main(String[] args)
    {
        String filename = "level/boston_17.txt";

        AlgoVisualizer visualizer = new AlgoVisualizer(filename);
    }
}
