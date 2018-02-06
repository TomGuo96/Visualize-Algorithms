import java.awt.*;

public class Experiment
{
    private int squareSide;
    private int n;
    private int outputInterval = 1000;

    public Experiment(int squareSide, int n)
    {
        if (squareSide <= 0 || n <= 0)
            throw new IllegalArgumentException("squareSide and n must larget than 0.");
        this.squareSide = squareSide;
        this.n = n;
    }

    public void run()
    {
        Circle circle = new Circle(squareSide / 2, squareSide / 2, squareSide / 2);
        MonteCarloPiData data = new MonteCarloPiData(circle);
        for (int i = 0; i < n; i++)
        {
            if (i % outputInterval == 0)
                System.out.println(data.estimatePi());
            int x = (int)(Math.random() * squareSide);
            int y = (int)(Math.random() * squareSide);
            data.addPoint(new Point(x, y));
        }
    }

    public void setOutputInterval(int interval)
    {
        if (interval <= 0)
            throw new IllegalArgumentException("interval must be larger than 0.");
        this.outputInterval = interval;
    }

    public static void main(String[] args)
    {
        int squareSide = 800;
        int n = 10000000;

        Experiment exp = new Experiment(squareSide, n);
        exp.setOutputInterval(10000);
        exp.run();
    }
}
