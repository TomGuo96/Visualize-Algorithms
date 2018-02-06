public class Experiment
{
    private int n;

    public Experiment(int n)
    {
        if (n <= 0)
            throw new IllegalArgumentException("n must be larger than 0!");
        this.n = n;
    }

    public void run(boolean changeDoor)
    {
        int wins = 0;
        for (int i = 0; i < n; i++)
        {
            if (play(changeDoor))
                wins++;
        }
        System.out.println(changeDoor ? "Change" : "Not Change");
        System.out.println("winning rate: " + (double)wins / n);
    }

    private boolean play(boolean changeDoor)
    {
        int prizeDoor = (int)(Math.random() * 3);
        int playerChoice = (int)(Math.random() * 3);

        if (playerChoice == prizeDoor)
            return changeDoor ? false : true;
        else
            return changeDoor ? true : false;
    }

    public static void main(String[] args)
    {
        int n = 10000000;
        Experiment exp = new Experiment(n);
        exp.run(true);
        System.out.println();
        exp.run(false);

    }
}
