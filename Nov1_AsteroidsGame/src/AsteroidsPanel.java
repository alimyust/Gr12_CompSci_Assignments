import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

class AsteroidsPanel extends JPanel implements KeyListener, ActionListener, MouseListener {
    private static final int WIDTH = 800, HEIGHT = 900, DELAY = 10;
    private static final int INTRO = 0, GAME = 1, GAMEOVER = 2;
    private final boolean PARTICLE = false, LINE = true;
    private int gameState = INTRO;
    private int lives;//{{50,60,40},{50,75,75}};
    private final int[][] lifeIcon = {{40, 50, 60, 58, 42}, {75, 50, 75, 71, 71}};
    private int score;
    private int lvl; //amount of meteors
    private int pDestroyedCount;
    private boolean[] keys;
    static Timer timer;
    Player p1;

    private final ArrayList<DustParticles> dustParticles;
    private final ArrayList<Meteoroid> meteors;
    public static ArrayList<Bullet> bullets;
    private final ArrayList<UFO> ufos;

    public AsteroidsPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        timer = new Timer(DELAY, this);//Listens to this panel (makes loop)
        p1 = new Player();
        meteors = new ArrayList<Meteoroid>();
        bullets = new ArrayList<Bullet>();
        dustParticles = new ArrayList<DustParticles>();
        ufos = new ArrayList<UFO>();
        lvl = 3;
        lives = 3;
        score = 0;
        pDestroyedCount = 90;
        timer.start();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        collision();
        move();
        repaint();
    }

    public void paint(Graphics g) {
        if (gameState == INTRO) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            for (Meteoroid j : meteors)
                j.drawMeteoroid(g);
        } else if (gameState == GAME) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            if (pDestroyedCount >= 90)
                p1.draw(g);
            for (Meteoroid j : meteors)
                j.drawMeteoroid(g);
            for (DustParticles j : dustParticles)
                j.drawDust(g);
            for (Bullet j : bullets)
                j.drawBullet(g);
            for (UFO j : ufos)
                j.drawUFO(g, p1);
            for (int i = 0; i < lives; i++) {
                int[] xPts = lifeIcon[0].clone();
                for (int j = 0; j < xPts.length; j++)
                    xPts[j] += 25 * i;
                g.drawPolygon(xPts, lifeIcon[1], 5);
            }
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Score: " + score, 30, 40);
        } else if (gameState == GAMEOVER) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            for (Meteoroid j : meteors)
                j.drawMeteoroid(g);
            for (Bullet j : bullets)
                j.drawBullet(g);
            for (DustParticles j : dustParticles)
                j.drawDust(g);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER", WIDTH / 2 - 40, HEIGHT / 2);
            g.drawString("Score: " + score, WIDTH / 2 - 50, HEIGHT / 2 - 40);
        }
    }

    private void move() {
        if (pDestroyedCount >= 90)
            p1.movePlayer(keys);
        for (Meteoroid j : meteors)
            j.moveSpaceObject();
        for (Bullet j : bullets)
            j.moveSpaceObject();
        for (DustParticles j : dustParticles)
            j.moveSpaceObject();
        for (UFO j : ufos)
            j.moveSpaceObject();
    }

    public void collision() {
        ArrayList<Meteoroid> oldM = new ArrayList<>();
        ArrayList<Bullet> oldB = new ArrayList<>();
        ArrayList<UFO> oldU = new ArrayList<>();
        int xMeteorRange, yMeteorRange;
        do {xMeteorRange = (int) ((Math.random() > 0.5) ? Math.random() * (p1.getX() - 200) : p1.getX() + 200 + Math.random() * WIDTH);
        } while (!(xMeteorRange < 0 || xMeteorRange > WIDTH));
        do {yMeteorRange = (int) ((Math.random() > 0.5) ? Math.random() * (p1.getY() - 200) : p1.getY() + 200 + Math.random() * WIDTH);
        } while (!(yMeteorRange < 0 || yMeteorRange > WIDTH));
        double newAngle = Math.random() * 40 + 30;
        // Arraylist to gather all meteors to remove
        if (pDestroyedCount < 90)
            pDestroyedCount--;
        if (meteors.isEmpty()) {
            lvl++;
            for (int i = 0; i < lvl; i++)
                meteors.add(new Meteoroid(xMeteorRange, yMeteorRange, 2, newAngle));
        }
        if (Math.random() * 5000 < 2 * lvl && ufos.isEmpty())
            ufos.add(new UFO(xMeteorRange, yMeteorRange, 50, newAngle));

        bullets.forEach(b -> {
            meteors.stream()
                    .filter(m -> isCircleCollision(m, b))
                    .forEach(m -> {
                        oldM.add(m);
                        oldB.add(b);
                    });
            meteors.forEach(m -> spaceObjectDestroyed( b, m, 5*lvl+m.getSize() + 1));
            meteors.removeIf(m ->isCircleCollision(m, b));
            ufos.forEach(u -> spaceObjectDestroyed( b, u, 100));
            ufos.removeIf(u ->isCircleCollision(u, b));
            if (isCircleCollision(p1, b) &&b.getBullDecay() <25 && p1.getInvinceCounter() < 0)
                playerDestroyed();
        });
        bullets.removeIf(b -> b.getBullDecay() < 0);
        bullets.removeIf(b -> isCircleCollision(p1, b) && b.getBullDecay() <25);
        dustParticles.removeIf(DustParticles::getTime);
        for (Meteoroid m : meteors)
            if (m.getRect().intersects(p1.playerRect()) && p1.getInvinceCounter() < 0 && pDestroyedCount >= 90)
                    playerDestroyed();
        bullets.removeAll(oldB);
        meteors.removeAll(oldM);
        ufos.removeAll(oldU);
        if (!oldM.isEmpty() &&oldM.get(0).getSize() > 0) {
            Meteoroid m=oldM.get(0);
            double randAngle = Math.random() * 40 + 30;
            meteors.add(new Meteoroid(m.getX(), m.getY(), m.getSize() - 1, randAngle - Math.random() * 40));
            meteors.add(new Meteoroid(m.getX(), m.getY(), m.getSize() - 1, randAngle - Math.random() * 40));
        }
        newPlayer();
    }

    private void newPlayer() {
        if (lives == 0 && pDestroyedCount < 0)
            gameState = GAMEOVER;
        if (lives > 0 && pDestroyedCount < 0) {
            p1 = new Player();
            pDestroyedCount = 90;
            p1.setInvinceCounter(60);
        }

    }
    private void playerDestroyed(){
        lives--;
        pDestroyedCount--;
        double newAngle = Math.random() * 360;
        for (int i = 0; i < 3; i++)
            dustParticles.add(new DustParticles((int) (p1.getX() + (Math.random() * 30)),
                    (int) (p1.getY() + (Math.random() * 30)), newAngle, LINE));
    }

    private void spaceObjectDestroyed(SpaceObject b, SpaceObject u, int scorePlus){
        if (isCircleCollision(b, u)) {
            score += scorePlus;
            double newAngle = Math.random() * 360;
            for (int i = 0; i < Math.random() * 5 + 2; i++)
                dustParticles.add(new DustParticles((int) (u.getX() + (Math.random() * 30)),
                        (int) (u.getY() + (Math.random() * 30)), newAngle, PARTICLE));
        }
    }
    
    private void newMeteors(Meteoroid m){
        double randAngle = Math.random() * 40 + 30;
        meteors.add(new Meteoroid(m.getX(), m.getY(), m.getSize() - 1, randAngle - Math.random() * 40));
        meteors.add(new Meteoroid(m.getX(), m.getY(), m.getSize() - 1, randAngle - Math.random() * 40));
    }
    private boolean isCircleCollision(SpaceObject a, SpaceObject b) {
        int ax = a.getX();
        int ay = a.getY();
        int bx = b.getX();
        int by = b.getY();
        int radiusSum = a.wid + b.wid;
        double distSquared = Math.pow((bx - ax), 2) + Math.pow((by - ay), 2);
        return distSquared < Math.pow(radiusSum, 2);
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