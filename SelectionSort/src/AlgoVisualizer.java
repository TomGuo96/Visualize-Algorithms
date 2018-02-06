import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer
{
    private static int DELAY = 50;
    private SelectionSortData data; // 数据
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n)
    {
        // 初始化数据
        data = new SelectionSortData(n, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Selection Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run()
    {
        setData(0, -1, -1);
        for (int i = 0; i < data.size(); i++) {
            int minIndex = i;
            setData(i, -1, minIndex);
            for (int j = i + 1; j < data.size(); j++) {
                setData(i, j, minIndex);
                if (data.get(j) < data.get(minIndex)) {
                    minIndex = j;
                    setData(i, j, minIndex);
                }
            }
            data.swap(i, minIndex);
            setData(i + 1, -1, - 1);
        }
        setData(data.size(), -1, -1);

    }

    private void setData(int orderedIndex, int currentCompareIndex, int currentMinIndex)
    {
        data.orderedIndex = orderedIndex;
        data.currentCompareIndex = currentCompareIndex;
        data.currentMinIndex = currentMinIndex;
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args)
    {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N);
    }
}
