import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

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
        boolean meteorRem = false;
        ArrayList <Meteoroid> oldM = new ArrayList<>();
        // Arraylist to gather all meteors to remove
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
        //Removes bullet if the timer is less than zero

        for(Bullet b: bullets) {
            for(Meteoroid m: meteors) {
                if(isCircleCollision(b,m)){
                    oldM.add(m);
                    meteorRem = true;
                }
            }
        }
        for(Meteoroid m: meteors)
            bullets.removeIf(b -> isCircleCollision(m, b));

        if(meteorRem && oldM.get(0).getSize() > 0) {
            double randAngle = ((Math.random() * 70 + 10) * (Math.random() * 3 + 1));
            meteors.add(new Meteoroid(oldM.get(0).getX(), oldM.get(0).getY(), oldM.get(0).getSize()-1, randAngle));
            meteors.add(new Meteoroid(oldM.get(0).getX(), oldM.get(0).getY(), oldM.get(0).getSize()-1, randAngle - Math.random() * 40));
        }
        meteors.removeAll(oldM);
    }


    public boolean isCircleCollision(SpaceObject a,SpaceObject b){
        int ax = a.getX();
        int bx = b.getX();
        int ay = a.getY();
        int by = b.getY();
        int radiusSum = a.wid + b.wid;
        double distSquared = Math.pow((bx - ax),2)+ Math.pow((by - ay),2);
        return distSquared < Math.pow(radiusSum,2);
    }

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