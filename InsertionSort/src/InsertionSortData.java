import java.util.Arrays;

public class InsertionSortData
{
    private int[] numbers;
    public int orderedIndex = -1;
    public int currentIndex = -1;
    public enum Type {
        Default,
        NearlyOrdered
    }

    public InsertionSortData(int n, int randomBound, Type dataType)
    {
        numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = (int)(Math.random() * randomBound) + 1;
        }

        if (dataType == Type.NearlyOrdered)
        {
            Arrays.sort(numbers);
            int swapTime = (int)(0.02 * n);
            for (int i = 0; i < swapTime; i++) {
                int a = (int)(Math.random() * n);
                int b = (int)(Math.random() * n);
                swap(a, b);
            }
        }
    }

    public InsertionSortData(int n, int randomBound)
    {
        this(n, randomBound, Type.Default); }

    public int size() { return numbers.length; }

    public int get(int index)
    {
        if (index < 0 || index >= numbers.length)
            throw new IllegalArgumentException("Invalid index to access Sort Data!");
        return numbers[index];
    }

    public void swap(int i, int j)
    {
        if (i < 0 || i >= numbers.length || j < 0 || j >= numbers.length)
            throw new  IllegalArgumentException("Invalid index to access Sort Data!");
        int t = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = t;
    }
}
