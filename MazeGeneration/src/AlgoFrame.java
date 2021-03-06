import javax.swing.*;
import java.awt.*;

public class AlgoFrame extends JFrame {

    private int canvasWidth;
    private int canvasHeight;

    public AlgoFrame(String title, int canvasWidth, int canvasHeight)
    {
        super(title);

        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        AlgoCanvas canvas = new AlgoCanvas();

        setContentPane(canvas);
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public AlgoFrame(String title) {
        this(title, 1024, 768);
    }

    public int getCanvasWidth() { return canvasWidth; }
    public int getCanvasHeight() { return canvasHeight; }

    private MazeData data;
    public void render(MazeData data) {
        this.data = data;
        repaint();
    }

    private class AlgoCanvas extends JPanel {

        // 双缓存
        public AlgoCanvas() {
            super(true);
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D)g;

            // 抗锯齿
            RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(hints);

            // 具体绘制
            int w = canvasWidth / data.row();
            int h = canvasHeight / data.col();
            for (int i = 0; i < data.row(); i++) {
                for (int j = 0; j < data.col(); j++) {
                    if (data.inMist[i][j])
                        AlgoVisHelper.setColor(g2d, AlgoVisHelper.BLack);
                    else {
                        if (data.maze[i][j] == MazeData.WALL)
                            AlgoVisHelper.setColor(g2d, AlgoVisHelper.LightBlue);
                        else
                            AlgoVisHelper.setColor(g2d, AlgoVisHelper.White);
                        if(data.path[i][j])
                            AlgoVisHelper.setColor(g2d, AlgoVisHelper.Yellow);
                    }
                    AlgoVisHelper.fillRectangle(g2d, j * w, i * h, w, h);
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }

    }

}
