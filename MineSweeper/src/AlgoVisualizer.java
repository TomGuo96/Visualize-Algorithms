import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AlgoVisualizer
{
    private static int blockSide = 32;
    private static int DELAY = 5;

    private MineSweeperData data;// 数据
    private AlgoFrame frame; // 视图

    public AlgoVisualizer(int row, int col, int mineNumber)
    {
        // 初始化数据
        data = new MineSweeperData(row, col, mineNumber);
        int sceneWidth = col * blockSide;
        int sceneHeight = row * blockSide;

        // 初始化视图
        EventQueue.invokeLater(() -> {
            frame = new AlgoFrame("Mine Sweeper", sceneWidth, sceneHeight);
            frame.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                run();
            }).start();
        });
    }

    private void run()
    {
        setData(true, -1, -1);
    }

    private void setData(boolean isLeftClicked, int x, int y)
    {
        if (data.inArea(x, y))
        {
            if (isLeftClicked)
            {
                if (data.isMine(x, y))
                {
                    // Game Over
                    data.open[x][y] = true;
                }
                else
                    data.open(x, y);
            }
            else
                data.flags[x][y] = !data.flags[x][y];
        }
        frame.render(data);
        AlgoVisHelper.pause(DELAY);
    }

    private class AlgoMouseListener extends MouseAdapter
    {
      @Override
      public void mouseReleased(MouseEvent event)
      {
          event.translatePoint(-(int)(frame.getBounds().width - frame.getCanvasWidth()), -(int)(frame.getBounds().height - frame.getCanvasHeight()));

          Point pos = event.getPoint();

          int w = frame.getCanvasWidth() / data.col();
          int h = frame.getCanvasHeight() / data.row();

          int x = pos.y / h;
          int y = pos.x / w;

          if (SwingUtilities.isLeftMouseButton(event))
              setData(true, x, y);
          else
              setData(false, x, y);
      }
    }

    public static void main(String[] args)
    {
        int row = 20;
        int col = 20;
        int mineNumber = 10;

        AlgoVisualizer visualizer = new AlgoVisualizer(row, col, mineNumber);
    }
}
