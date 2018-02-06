public class SelectionSortData
{
    private int[] numbers;
    public int orderedIndex = - 1;
    public int currentMinIndex = -1;
    public int currentCompareIndex = -1;

    public SelectionSortData(int n, int randomBound)
    {
        numbers = new int[n];
        for (int i = 0; i < n; i++)
            numbers[i] = (int)(Math.random() * randomBound) + 1;
    }

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
