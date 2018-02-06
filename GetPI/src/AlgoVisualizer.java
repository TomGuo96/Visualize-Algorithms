import java.awt.*;

public class AlgoVisualizer
{
    private static int DELAY = 40;
    private int n;
    private MonteCarloPiData data;
    private AlgoFrame frame;

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n)
    {
        // 初始化数据
        if (sceneWidth != sceneWidth)
            throw new IllegalArgumentException("This demo must be run in a square window!");
        this.n = n;
        Circle circle = new Circle(sceneWidth / 2, sceneHeight / 2, sceneHeight / 2);
        data = new MonteCarloPiData(circle);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Get PI with Monte Carlo", sceneWidth, sceneHeight);

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run()
    {
        for (int i = 0; i < n; i++)
        {
            if (i % 100 == 0)
            {
                frame.render(data);
                AlgoVisHelper.pause(DELAY);
                System.out.println(data.estimatePi());
            }

            int x = (int)(Math.random() * frame.getCanvasWidth());
            int y = (int)(Math.random() * frame.getCanvasHeight());

            data.addPoint(new Point(x, y));
        }
    }

    public static void main(String[] args)
    {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int n = 20000;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, n);
    }
}
