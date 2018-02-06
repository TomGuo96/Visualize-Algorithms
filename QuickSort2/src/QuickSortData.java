import com.sun.xml.internal.bind.v2.model.annotation.Quick;

import java.util.Arrays;

public class QuickSortData
{
    public enum Type {
        Default,
        NearlyOrderd,
        Identical
    }
    public int[] numbers;
    public int l, r;
    public int curPivot;
    public int curL, curR;
    public boolean[] fixedPivot;

    public QuickSortData(int n, int randomBound, Type dataType)
    {
        numbers = new int[n];
        fixedPivot = new boolean[n];

        int lBound = 1;
        int rBound = randomBound;
        if (dataType == Type.Identical)
        {
            int avgNumber = (lBound + rBound) / 2;
            lBound = avgNumber;
            rBound = avgNumber;
        }

        for (int i = 0; i < n; i++) {
            numbers[i] = (int)(Math.random() * (rBound - lBound + 1)) + lBound;
            fixedPivot[i] = false;
        }

        if (dataType == Type.NearlyOrderd)
        {
            Arrays.sort(numbers);
            int swapTime = (int)(0.01 * n);
            for (int i = 0; i < swapTime; i++)
            {
                int a = (int)(Math.random() * n);
                int b = (int)(Math.random() * n);
                swap(a, b);
            }
        }
    }

    public QuickSortData(int n, int randomBound) { this(n, randomBound, Type.Default); }

    public int size() { return numbers.length; }

    public int get(int index)
    {
        if (index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data");
        return numbers[index];
    }

    public void swap(int i, int j)
    {
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
