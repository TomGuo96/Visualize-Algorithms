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

    // TODO：创建自己的数据data
    private Object data;
    public void render(Object data) {
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
            // TODO: 绘制自己的数据data
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(canvasWidth, canvasHeight);
        }

    }

}
