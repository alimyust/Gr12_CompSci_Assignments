import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AsteroidsPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    private static final int WIDTH = 800, HEIGHT = 600, DELAY = 10;
    private static final int INTRO = 0, GAME = 1;
    int gameState = INTRO;

    private boolean[] keys;
    Timer timer;
    Player p1;

    public AsteroidsPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        timer = new Timer(10, this);//Listens to this panel (makes loop)
        p1 = new Player();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true); // turns into loop.
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
    }

    public void paint(Graphics g) {
        if (gameState == INTRO) {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, WIDTH, HEIGHT);
        } else if (gameState == GAME) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            p1.draw(g);

        }
    }

    private void move() {
        p1.movePlayer(keys);

    }
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = true;
        //When the key is pressed the corresponding key is set to true
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = false;
        //When the key is released the corresponding key is set to false
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        timer.start();
        gameState = GAME;
    }

    @Override
    public void keyTyped(KeyEvent e) {
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

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }
}