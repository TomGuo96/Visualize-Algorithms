import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class AlgoVisualizer
{
    private MergeSortData data;// 数据
    private static int DELAY = 40;
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(int sceneWidth, int sceneHeight, int n)
    {
        // 初始化数据
         data = new MergeSortData(n, sceneHeight);

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Merge Sort Visualization", sceneWidth, sceneHeight);
            new Thread(() -> {
                run();
            }).start();
        });
    }

    public void run()
    {
        setData(-1, -1, -1);
        mergeSort(0, data.size() - 1);
        setData(0, data.size() -  1, data.size() - 1);
    }

    private void mergeSort(int l, int r)
    {
        if (l >= r) return;

        setData(l, r, -1);
        int mid = l + (r - l) / 2;
        mergeSort(l, mid);
        mergeSort(mid + 1, r);
        merge(l, mid, r);
    }

    private void merge(int l, int mid, int r)
    {
        int[] aux = Arrays.copyOfRange(data.numbers, l, r + 1);
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++)
        {
            if (i > mid) {
                data.numbers[k] = aux[j - l]; j++;
            } else if (j > r) {
                data.numbers[k] = aux[i - l]; i++;
            }
            else if (aux[i - l] < aux[j - l]) {
                data.numbers[k] = aux[i - l]; i++;
            } else {
                data.numbers[k] = aux[j - l]; j++;
            }
            setData(l, r, k);
        }
    }

    private void setData(int l, int r, int mergeIndex)
    {
        data.l = l;
        data.r = r;
        data.mergeIndex = mergeIndex;
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
