import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.awt.*;

public class AlgoVisualizer
{
    private QuickSortData data;// 数据
    private static int DELAY = 40;
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n, QuickSortData.Type dataType)
    {
        // 初始化数据
         data = new QuickSortData(n, sceneHeight, dataType);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Quick Sort3 Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n) { this(sceneWidth, sceneHeight, n, QuickSortData.Type.NearlyOrderd); }

    private void run()
    {
        setData(-1, -1, -1, -1, -1, -1);
        quickSort(0, data.size() - 1);
        setData(-1, -1, -1, -1, -1, -1);
    }

    private void quickSort(int l, int r)
    {
        if (l > r) return;
        if (l == r)
        {
            setData(l, r, l, -1, -1, -1);
            return;
        }
        int p = (int)(Math.random() * (r - l + 1)) + l;
        setData(l, r, -1, p, -1, -1);
        data.swap(l, p);
        setData(l, r, -1, l, -1, -1);
        int v = data.get(l);

        // partition
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        setData(l, r, -1, l, lt, gt);

        while (i < gt)
        {
            if (data.get(i) < v)
            {
                data.swap(i, lt + 1);
                i++;
                lt++;
            }
            else if (data.get(i) > v)
            {
                data.swap(i, gt - 1);
                gt--;
            }
            else i++;
            setData(l, r, -1, l, i, gt);
        }
        data.swap(l, lt);
        setData(l, r, lt, -1, -1, -1);

        quickSort(l, lt - 1);
        quickSort(gt, r);
    }

    private void setData(int l, int r, int fixedPivot, int curPivot, int curL, int curR)
    {
        data.l = l;
        data.r = r;
        if (fixedPivot != -1)
        {
            int i = fixedPivot;
            while (i < data.size() && data.get(i) == data.get(fixedPivot))
            {
                data.fixedPivots[i] = true;
                i++;
            }
        }
        data.curL = curL;
        data.curR = curR;
        data.curPivot = curPivot;

        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    public static void main(String[] args)
    {
        int sceneWidth = 800;
        int sceneHeight = 800;
        int N = 100;

        AlgoVisualizer visualizer = new AlgoVisualizer(sceneWidth, sceneHeight, N, QuickSortData.Type.Identical);
    }
}
