import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CoordinateCapture extends JFrame implements KeyListener, MouseListener {
    private final List<Integer> xCoordinates = new ArrayList<>();
    private final List<Integer> yCoordinates = new ArrayList<>();
    private ArrayList<List<Integer>> out = new ArrayList<>();
    private final int DIM = 200; // Dimensions
    public CoordinateCapture() {
        setTitle("Coordinate Capture");
        setSize(DIM, DIM);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener( this);
        addKeyListener(this);
        setFocusable(true);
        out.add(xCoordinates);
        out.add(yCoordinates);

    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(DIM/2,DIM/2,125-DIM/2,125-DIM/2);
        for (int i = 1; i < xCoordinates.size(); i++) {
            int x1 = xCoordinates.get(i - 1);
            int y1 = yCoordinates.get(i - 1);
            int x2 = xCoordinates.get(i);
            int y2 = yCoordinates.get(i);
            g.drawLine(x1 + DIM/2, y1+ DIM/2, x2+ DIM/2, y2+ DIM/2);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        xCoordinates.add(e.getX() - DIM/2);
        yCoordinates.add(e.getY() - DIM/2);
        repaint();
        System.out.println("Clicked coordinates:");
        System.out.println(out);
    }
    public static void main(String[] args) {
            CoordinateCapture frame = new CoordinateCapture();
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
