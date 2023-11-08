import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class AsteroidsPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    private static final int WIDTH = 800, HEIGHT = 600, DELAY = 10;
    private static final int INTRO = 0, GAME = 1, GAMEOVER = 2;
    private int gameState = INTRO;
    private int lives;
    private int score;
    private int lvl; //amount of meteors
    private int pDestroyedCount;
    private boolean[] keys;
    static Timer timer;
    Player p1;
    ArrayList<Meteoroid> meteors;
    public static ArrayList<Bullet> bullets;

    public AsteroidsPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        timer = new Timer(DELAY, this);//Listens to this panel (makes loop)
        p1 = new Player(WIDTH / 2, HEIGHT / 2, 0.0, 0, 0,60);
        meteors = new ArrayList<Meteoroid>();
        bullets = new ArrayList<Bullet>();
        lvl = 3;
        lives= 3;
        score = 0;
        pDestroyedCount = 60;
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
            if(pDestroyedCount >= 60)
                p1.draw(g);
            if(pDestroyedCount < 60 && pDestroyedCount > 0)
                p1.deathAnimation(g);
            for(Meteoroid j: meteors)
                j.drawMeteoroid(g);
            for(Bullet j: bullets)
                j.drawBullet(g);
            g.setFont(new Font("Arial", Font.BOLD,30));
            g.drawString("Score: " +score, 30,40);
            g.drawString("Lives: " + lives , 30,90);
        } else if (gameState == GAMEOVER) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            for(Meteoroid j: meteors)
                j.drawMeteoroid(g);
            for(Bullet j: bullets)
                j.drawBullet(g);
            g.setFont(new Font("Arial", Font.BOLD,30));
            g.drawString("GAME OVER", WIDTH/2-40, HEIGHT/2);
            g.drawString("Score: " +score, WIDTH/2 - 50,HEIGHT/2-40);
        }
    }

    private void move() {
        if(pDestroyedCount >= 60)
            p1.movePlayer(keys);
        for(Meteoroid j: meteors)
            j.moveSpaceObject();
        for(Bullet j: bullets)
            j.moveSpaceObject();
    }
    public void collision(){
        boolean meteorRem = false;
        ArrayList <Meteoroid> oldM = new ArrayList<>();
        int xMeteorRange, yMeteorRange;
        // Arraylist to gather all meteors to remove
        if(pDestroyedCount < 60)
            pDestroyedCount --;
        if(meteors.isEmpty()) {
            lvl ++;
            for (int i = 0; i < lvl; i++) {
                do {xMeteorRange = (int) ((Math.random() > 0.5) ? Math.random() * (p1.getX() - 200) : p1.getX() + 200 + Math.random() * WIDTH);
                } while (!(xMeteorRange < 0 || xMeteorRange > WIDTH));
                do {yMeteorRange = (int) ((Math.random() > 0.5) ? Math.random() * (p1.getY() - 200) : p1.getY() + 200 + Math.random() * WIDTH);
                }while(!(yMeteorRange < 0 || yMeteorRange > WIDTH));
                meteors.add(new Meteoroid(xMeteorRange, yMeteorRange, 2, (int) ((Math.random() * 70 + 10) * (int) (Math.random() * 3 + 1))));
            }
        }
        bullets.removeIf(b -> b.getBullDecay() < 0);
        //Removes bullet if the timer is less than zero

        for(Bullet b: bullets) {
            for(Meteoroid m: meteors) {
                if(isCircleCollision(b,m)){
                    oldM.add(m);
                    score += 15*(m.getSize()+1);
                    meteorRem = true;
                }
            }
        }
        for(Meteoroid m: meteors) {
            bullets.removeIf(b -> isCircleCollision(m, b));
            if(m.getRect().intersects(p1.playerRect()) && p1.getInvinceCounter() < 0){
                if(pDestroyedCount >= 60) {
                    lives--;
                    pDestroyedCount--;
                    p1.setBlownShipPts(new double[][]{
                    {(int)(p1.getX() + (Math.random() * 30)),(int)(p1.getY() + (Math.random() * 30)),
                            (int)(p1.getX() + (Math.random() * 30+50)),(int)(p1.getY() + (Math.random() * 30+50))},
                    {(int)(p1.getX() + (Math.random() * 30)),(int)(p1.getY() + (Math.random() * 30)),
                            (int)(p1.getX() + (Math.random() * 30+50)),(int)(p1.getY() + (Math.random() * 30+50))},
                    {(int)(p1.getX() + (Math.random() * 30)),(int)(p1.getY() + (Math.random() * 30)),
                            (int)(p1.getX() + (Math.random() * 30+50)),(int)(p1.getY() + (Math.random() * 30+50))}
                    });
                }
            }
        }
        meteors.removeAll(oldM);
        if(meteorRem && oldM.get(0).getSize() > 0) {
            double randAngle = ((Math.random() * 70 + 10) * (Math.random() * 3 + 1));
            meteors.add(new Meteoroid(oldM.get(0).getX(), oldM.get(0).getY(), oldM.get(0).getSize()-1, randAngle));
            meteors.add(new Meteoroid(oldM.get(0).getX(), oldM.get(0).getY(), oldM.get(0).getSize()-1, randAngle - Math.random() * 40));
        }
        newPlayer();
    }

    private void newPlayer() {
        if(lives == 0 && pDestroyedCount < 0)
            gameState = GAMEOVER;
        if(lives > 0 && pDestroyedCount < 0) {
            p1 = new Player(WIDTH / 2, HEIGHT / 2, 0.0, 0, 0,60);
            pDestroyedCount = 60;
            p1.setInvinceCounter(60);
        }

    }

    public void actionPerformed(ActionEvent e) {
        collision();
        move();
        repaint();
    }

    private boolean isCircleCollision(SpaceObject a,SpaceObject b){
        int ax = a.getX();
        int ay = a.getY();
        int bx = b.getX();
        int by = b.getY();
        int radiusSum = a.wid + b.wid;
        double distSquared = Math.pow((bx - ax),2)+ Math.pow((by - ay),2);
        return distSquared < Math.pow(radiusSum,2);
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        keys[key] = true;
        //When the key is pressed the corresponding key is set to true
    }
    @Override
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
}