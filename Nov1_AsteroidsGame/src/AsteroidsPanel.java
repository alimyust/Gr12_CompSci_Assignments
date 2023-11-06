import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

class AsteroidsPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    private static final int WIDTH = 800, HEIGHT = 600, DELAY = 10;
    private static final int INTRO = 0, GAME = 1;
    int gameState = INTRO;

    private int lvl; //amount of meteors
    private boolean[] keys;
    static Timer timer;
    Player p1;
    ArrayList<Meteoroid> meteors;
    public static ArrayList<Bullet> bullets;

    public AsteroidsPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        timer = new Timer(10, this);//Listens to this panel (makes loop)
        p1 = new Player();
        meteors = new ArrayList<Meteoroid>();
        bullets = new ArrayList<Bullet>();
        lvl = 3;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
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
            for(Meteoroid j: meteors)
                j.drawMeteoroid(g);
            for(Bullet j: bullets)
                j.drawBullet(g);
        }
    }

    private void move() {
        p1.movePlayer(keys);
        for(Meteoroid j: meteors)
            j.moveSpaceObject();
        for(Bullet j: bullets)
            j.moveSpaceObject();
    }
    public void collision(){
        final AtomicBoolean[] mAdd = {new AtomicBoolean(false)};
        final int[][] m1 = new int[1][1];
        if(meteors.isEmpty()) {
            lvl ++;
            for (int i = 0; i < lvl; i++) {
                int xMeteorRange = (int) ((Math.random()>0.5)?Math.random()*(p1.getX()-200):
                        p1.getX()+200+Math.random()*WIDTH);
                int yMeteorRange = (int) ((Math.random()>0.5)?Math.random()*(p1.getY()-200):
                        p1.getY()+200+Math.random()*WIDTH);
                meteors.add(new Meteoroid(xMeteorRange, yMeteorRange, 2, (int)((Math.random() *70+10) *(int)(Math.random()*3+1))));
            }
        }

        bullets.removeIf(b -> b.getBullDecay() < 0);
        //Removes bullet if the timer is less then zero


        meteors.removeIf(m -> { // lambda function to check for collision between asteroids and bullets
            boolean intersectsMeteor = bullets.stream().anyMatch(b -> m.getRect().intersects(b.getRect()));
            //returns true if any bullets from the stream intersect with a meteoroid
            if (intersectsMeteor) { // if there is an intersection the bullet is removed
                bullets.removeIf(b -> m.getRect().intersects(b.getRect()));
                if(m.getSize() > 0) {
                    mAdd[0].set(true);
                    m1[0] = new int[]{m.x, m.y, m.getSize() - 1, 0};
                }
            }
            return intersectsMeteor; // if there is an intersect this would return true to the lambdas removeIf call
        });
        if(mAdd[0].get()) {
            double randAngle = ((Math.random() *70+10)*(Math.random()*3+1));
            meteors.add(new Meteoroid(m1[0][0],m1[0][1],m1[0][2], randAngle));
            meteors.add(new Meteoroid(m1[0][0],m1[0][1],m1[0][2], randAngle-Math.random()*40));
        }

//        }
    }
    //    private void checkCollision(ArrayList<SpaceObject>, Objects){
//
//    }
    public void actionPerformed(ActionEvent e) {
        collision();
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