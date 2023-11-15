import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class AsteroidsPanel extends JPanel implements KeyListener, ActionListener, MouseListener {

    private static final int WIDTH = 800, HEIGHT = 900, DELAY = 10;
    private static final int INTRO = 0, GAME = 1, GAMEOVER = 2, SCORE = 3;
    private final boolean PARTICLE = false, LINE = true;
    private boolean isHighScore = false;
    private int gameState = INTRO;
    private int lives;//{{50,60,40},{50,75,75}};
    private final int[][] lifeIcon = {{40, 50, 60, 58, 42}, {75, 50, 75, 71, 71}};
    private int score;
    private int lvl; //amount of meteors
    private int pDestroyedCount;
    private int highScoreCount;
    private final boolean[] keys;
    static Timer timer;
    Player p1;

    private final ArrayList<DustParticles> dustParticles;
    private final ArrayList<Meteoroid> meteors;
    public static ArrayList<Bullet> bullets;
    private final ArrayList<UFO> ufos;
    private final int[] PLAYGAME_RECT = {WIDTH / 2 - 125, HEIGHT / 2+20, 250, 50};
    private final int[] TITLE_RECT = {WIDTH / 2 - 260, HEIGHT / 2 - 150, 520, 75};
    private final int[] GAMEOVER_RECT = {WIDTH / 2 - 170, HEIGHT / 2 - 100, 350, 50};
    private final int[] SCORE_RECT = {WIDTH / 2 - 90, HEIGHT / 2 + 70, 180, 50};
    private final int[] TOPSCORE_RECT = {WIDTH / 2 - 14, HEIGHT / 2 + 70, 28, 50};

    private final int[] MENU_RECT = {WIDTH / 2 - 55, HEIGHT / 2 + 130, 110, 50};
    private final int[] SCORE_MENU_RECT = {WIDTH / 2 - 55, HEIGHT-200, 110, 50};
    private final int[] HIGHSCORE_RECT = {WIDTH / 2 - 183, HEIGHT/2, 366, 40};


    public AsteroidsPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        timer = new Timer(DELAY, this);//Listens to this panel (makes loop)
        p1 = new Player();
        meteors = new ArrayList<Meteoroid>();
        bullets = new ArrayList<Bullet>();
        dustParticles = new ArrayList<DustParticles>();
        ufos = new ArrayList<UFO>();

        lvl = 3;
        lives = 1;
        score = 0;
        pDestroyedCount = 90;
        highScoreCount = 0;
        timer.start();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (gameState == GAME) {
            collision();
            spawnUFO();
        }
        spawnMeteoroids();
        spawnUFO();
        move();
        repaint();
        if (pDestroyedCount < 90)
            pDestroyedCount--;
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

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        for (Meteoroid j : meteors)
            j.drawMeteoroid(g);
        for (DustParticles j : dustParticles)
            j.drawDust(g);
        switch (gameState) {
            case INTRO -> drawIntro(g);
            case GAME -> drawGame(g);
            case GAMEOVER -> drawGameOver(g);
            case SCORE -> drawScore(g);
        }
    }

    private void drawIntro(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Hyperspace", Font.BOLD, 90));
        g.drawString("ASTEROIDS", TITLE_RECT[0], TITLE_RECT[1] + TITLE_RECT[3]);
//        g.drawRect(TITLE_RECT[0], TITLE_RECT[1], TITLE_RECT[2], TITLE_RECT[3]);
        g.setFont(new Font("Hyperspace", Font.BOLD, 45));
        g.drawString("PLAY GAME", PLAYGAME_RECT[0], PLAYGAME_RECT[1] + PLAYGAME_RECT[3]);
//        g.drawRect(PLAYGAME_RECT[0], PLAYGAME_RECT[1], PLAYGAME_RECT[2], PLAYGAME_RECT[3]);
        g.drawString("SCORES", SCORE_RECT[0], SCORE_RECT[1] + SCORE_RECT[3]);
//            g.drawRect(SCORE_RECT[0],SCORE_RECT[1],SCORE_RECT[2],SCORE_RECT[3]);
    }

    private void drawGame(Graphics g) {
        if (pDestroyedCount >= 90)
            p1.draw(g);
        for (Bullet j : bullets)
            j.drawBullet(g);
        for (UFO j : ufos)
            j.drawUFO(g, p1);
        drawLifeIcon(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Hyperspace", Font.BOLD, 30));
        g.drawString("Score: " + score, 30, 40);
    }

    private void drawGameOver(Graphics g) {
        highScoreCount ++;
        highScoreCount = (Math.abs(highScoreCount) == 50)?-highScoreCount: highScoreCount;
//        if (pDestroyedCount >= 90)
//            p1.draw(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Hyperspace", Font.BOLD, 60));
        g.drawString("GAME OVER", GAMEOVER_RECT[0], GAMEOVER_RECT[1] + GAMEOVER_RECT[3]);
//            g.drawRect(GAMEOVER_RECT[0],GAMEOVER_RECT[1],GAMEOVER_RECT[2],GAMEOVER_RECT[3]);
        g.setFont(new Font("Hyperspace", Font.BOLD, 45));
        g.drawString("Score:" + score, SCORE_RECT[0] - (score + "").length() * 14, SCORE_RECT[1] + SCORE_RECT[3]);
//            g.drawRect(SCORE_RECT[0],SCORE_RECT[1],SCORE_RECT[2],SCORE_RECT[3]);
        g.drawString("MENU", MENU_RECT[0], MENU_RECT[1] + MENU_RECT[3]);
//            g.drawRect(MENU_RECT[0],MENU_RECT[1],MENU_RECT[2],MENU_RECT[3]);
        if(orderedScores().size() > 1 && (orderedScores().get(1) < score && highScoreCount >0)){
            g.drawString("NEW HIGHSCORE", HIGHSCORE_RECT[0], HIGHSCORE_RECT[1] + HIGHSCORE_RECT[3]);
//            g.drawRect(HIGHSCORE_RECT[0],HIGHSCORE_RECT[1],HIGHSCORE_RECT[2],HIGHSCORE_RECT[3]);
        }
    }
    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Hyperspace", Font.BOLD, 45));
        g.drawString("MENU", SCORE_MENU_RECT[0], SCORE_MENU_RECT[1] + SCORE_MENU_RECT[3]);
//        g.drawRect(SCORE_MENU_RECT[0],SCORE_MENU_RECT[1],SCORE_MENU_RECT[2],SCORE_MENU_RECT[3]);
        for(int i = 0; i < (Math.min(orderedScores().size(), 5)); i++){
            String currScore = orderedScores().get(i) + "";
            g.drawString(currScore, TOPSCORE_RECT[0] - currScore.length() * 10,
                    TOPSCORE_RECT[1] + TOPSCORE_RECT[3]+ i*(TOPSCORE_RECT[3] + 5)-200);
//            g.drawRect(TOPSCORE_RECT[0],TOPSCORE_RECT[1] + i*(TOPSCORE_RECT[3] + 5)-200,TOPSCORE_RECT[2],TOPSCORE_RECT[3]);
        }

    }


    public void spawnMeteoroids() {
        if (meteors.isEmpty()) {
            lvl++;
            for (int i = 0; i < lvl; i++)
                meteors.add(new Meteoroid(getMeteorRange(WIDTH), getMeteorRange(HEIGHT), 2, newAngle()));
        }
    }

    private void spawnUFO() {
        if (Math.random() * 10000 < lvl && ufos.isEmpty())
            ufos.add(new UFO(getMeteorRange(WIDTH), getMeteorRange(HEIGHT),
                    (int) (Math.random() * 2), newAngle()));
    }

    public void collision() {
        // Arraylist to gather all meteors to remove
        ArrayList<Meteoroid> oldM = new ArrayList<>();
        ArrayList<Bullet> oldB = new ArrayList<>();
        bullets.forEach(b -> {
            meteors.stream()
                    .filter(m -> isCircleCollision(m, b))
                    .forEach(m -> {
                        oldM.add(m);
                        oldB.add(b);
                    });
            meteors.forEach(m -> spaceObjectDestroyed(b, m, 5 * lvl + m.getSize() + 1));
            meteors.removeIf(m -> isCircleCollision(m, b));
            ufos.forEach(u -> spaceObjectDestroyed(b, u, 100));
            ufos.removeIf(u -> isCircleCollision(u, b));
            if (isCircleCollision(p1, b) && b.getBullDecay() <60 && p1.getInvinceCounter() < 0)
                playerDestroyed();
        });
        bullets.removeIf(b -> b.getBullDecay() < 0);
        bullets.removeIf(b -> isCircleCollision(p1, b) && b.getBullDecay() < 35);
        for (Meteoroid m : meteors)
//            if (m.getRect().intersects(p1.playerRect()) && p1.getInvinceCounter() < 0 && pDestroyedCount >= 90)
            if (isCircleCollision(p1,m)&& p1.getInvinceCounter() < 0 && pDestroyedCount >= 90)
                playerDestroyed();
        dustParticles.removeIf(DustParticles::getTime);
        bullets.removeAll(oldB);
        meteors.removeAll(oldM);
        if (!oldM.isEmpty() && oldM.get(0).getSize() > 0) {
            Meteoroid m = oldM.get(0); // ^ If there is something to remove in oldM, and it isn't the smallest size
            meteors.add(new Meteoroid(m.getX(), m.getY(), m.getSize() - 1, newAngle()));
            meteors.add(new Meteoroid(m.getX(), m.getY(), m.getSize() - 1, newAngle()));
        } //^ add 2 meteors with new angles and a smaller size
        newPlayer();
    }

    private void newPlayer() {
        if (lives <= 0 && pDestroyedCount < 0) {
            gameState = GAMEOVER;
            addScore(String.valueOf(score));
        }
        if (lives > 0 && pDestroyedCount < 0) {
            p1 = new Player();
            pDestroyedCount = 90;
            p1.setInvinceCounter(90);
        }

    }

    private void playerDestroyed() {
        lives--;
        pDestroyedCount--;
        for (int i = 0; i < 3; i++)
            dustParticles.add(new DustParticles((int) (p1.getX() + (Math.random() * 30)),
                    (int) (p1.getY() + (Math.random() * 30)), newAngle(), LINE));
    }

    private void spaceObjectDestroyed(SpaceObject b, SpaceObject u, int scorePlus) {
        if (isCircleCollision(b, u)) {
            score += scorePlus;
            double newAngle = Math.random() * 360;
            for (int i = 0; i < Math.random() * 5 + 2; i++)
                dustParticles.add(new DustParticles((int) (u.getX() + (Math.random() * 30)),
                        (int) (u.getY() + (Math.random() * 30)), newAngle, PARTICLE));
        }
    }

    private double newAngle() {
        return Math.random() * 30 + 30 + (int) (Math.random() * 2) * 90;
    }

    private boolean isCircleCollision(SpaceObject a, SpaceObject b) {
        double xDif = b.getX() - a.getX();
        double yDif = b.getY() - a.getY();
        double distanceSquared = xDif * xDif + yDif * yDif;
        return distanceSquared < (a.wid + b.wid) * (a.wid + b.wid);
    }

    private void drawLifeIcon(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < lives; i++) {
            int[] xPts = lifeIcon[0].clone();
            for (int j = 0; j < xPts.length; j++)
                xPts[j] += 25 * i;
            g.drawPolygon(xPts, lifeIcon[1], 5);
        }
    }

    public int getMeteorRange(int dir) {
        int xMeteorRange;
        do {
            xMeteorRange = (int) ((Math.random() > 0.5) ? Math.random() * (p1.getX() - 200) : p1.getX() + 200 + Math.random() * dir);
        } while (!(xMeteorRange < 0 || xMeteorRange > dir));
        return xMeteorRange;
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
        Point mousePos = getMousePosition().getLocation();
        if (inRect(mousePos, PLAYGAME_RECT) && gameState == INTRO)
            gameState = GAME;
        if(inRect(mousePos, SCORE_RECT) && gameState == INTRO)
            gameState = SCORE;
        if(inRect(mousePos, SCORE_MENU_RECT)&&gameState == SCORE)
            gameState = INTRO;
        if (inRect(mousePos, MENU_RECT) && gameState == GAMEOVER)
            resetGame();
    }
    private void resetGame(){
        gameState = INTRO;
        p1 = new Player();
        meteors.clear();
        ufos.clear();
        score = 0;
        lives = 3;
        lvl=3;
    }

    private boolean inRect(Point p, int[] arr) {
        return p.x > arr[0] && p.x < arr[0] + arr[2] &&
                p.y > arr[1] && p.y < arr[1] + arr[3];
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

    private void addScore(String content) {
        // Create a FileWriter object with the specified file path
        try {
            PrintWriter fileOut = new PrintWriter(new BufferedWriter(new FileWriter("scores.txt", true)));
            fileOut.println(content);
            fileOut.close();
            System.out.println("Content has been written to the file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Integer> orderedScores() {
        //looks at the scores file and returns an ordered arraylist of numbers
        Scanner fileIn;
        try {
            fileIn = new Scanner(new FileReader("scores.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Integer> arrOut = new ArrayList<>();
        while (fileIn.hasNextInt())
            arrOut.add(fileIn.nextInt());
        fileIn.close();
        arrOut.sort(Comparator.reverseOrder());
        return arrOut;
    }
}