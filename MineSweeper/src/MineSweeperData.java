public class MineSweeperData
{
    public static final String blockImageURL = "resources/block.png";
    public static final String flagImageURL = "resources/flag.png";
    public static final String mineImageURL = "resources/mine.png";
    public static String numberImageURL(int num)
    {
        if (num < 0 || num >= 8)
            throw new IllegalArgumentException("No such a number image!");
        return "resources/" + num + ".png";
    }

    private int row, col;
    private boolean mines[][];
    private int[][] numbers;
    public boolean open[][];
    public boolean flags[][];

    public MineSweeperData(int row, int col, int mineNumber)
    {
        if (row <= 0 || col <= 0)
            throw new IllegalArgumentException("Mine sweeper size is invalid!");
        if (mineNumber < 0 || mineNumber >= row * col)
            throw new IllegalArgumentException("Mine number is larger than the size of mine sweeper board!");

        this.row = row;
        this.col = col;
        mines = new boolean[row][col];
        open = new boolean[row][col];
        flags = new boolean[row][col];
        numbers = new int[row][col];

        generateMines(mineNumber);
        calculateNumbers();
    }

    public int row() { return row; }
    public int col() { return col; }

    public int getNumber(int x, int y)
    {
        if (!inArea(x, y))
            throw new IllegalArgumentException("Out of index in getNumber function!");
        return numbers[x][y];
    }

    public boolean isMine(int x, int y)
    {
        if (!inArea(x, y))
            throw new IllegalArgumentException("Out of index in isMine function!");
        return mines[x][y];
    }

    public boolean inArea(int x, int y) { return x >= 0 && x < row && y >= 0 && y <col; }

    private void generateMines(int mineNumber)
    {
        for (int i = 0; i < mineNumber; i++)
        {
            int x = i / col;
            int y = i % col;
            mines[x][y] = true;
        }

        for (int i = row * col - 1; i >= 0; i--)
        {
            int iX = i / col;
            int iY = i % col;
            int randNumber = (int)(Math.random() * (i + 1));
            int randX = randNumber / col;
            int randY = randNumber % col;
            swap(iX, iY, randX, randY);
        }
    }

    private void calculateNumbers()
    {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (mines[i][j])
                    numbers[i][j] = -1;
                numbers[i][j] = 0;
                for (int ii = i - 1; ii <= i + 1; ii++) {
                    for (int jj = j - 1; jj <= j + 1; jj++) {
                        if (inArea(ii, jj) && mines[ii][jj])
                            numbers[i][j] ++;
                    }
                }
            }
        }
    }

    public void open(int x, int y)
    {
        if (!inArea(x, y))
            throw new IllegalArgumentException("Out of index in open function!");
        if (isMine(x, y))
            throw new IllegalArgumentException("Cannot open an mine block in open function!");

        open[x][y] = true;

        if (numbers[x][y] > 0)
            return;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (inArea(i, j) && !open[i][j] && !mines[i][j])
                    open(i, j);
            }
        }
    }

    private void swap(int x1, int y1, int x2, int y2)
    {
        boolean t = mines[x1][y1];
        mines[x1][y1] = mines[x2][y2];
        mines[x2][y2] = t;
    }
}
