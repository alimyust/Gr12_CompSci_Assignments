import org.lwjgl.Sys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
/*
* Main game logic is contained here
* Responsible for calling the methods of all of the different objects to check for collision and
* remove objects based on the result, paint, play sounds, etc.
* */

class AsteroidsPanel extends JPanel implements KeyListener, ActionListener, MouseListener {

    private static final int WIDTH = 800, HEIGHT = 900, DELAY = 10;
    //Variables for gamestates and constants that make the code more readable
    private static final int INTRO = 0, GAME = 1, GAMEOVER = 2, SCORE = 3;
    private final boolean PARTICLE = false, LINE = true;


    private int gameState = INTRO;
    //main game control variable
    private int lives;//{{50,60,40},{50,75,75}};
    private final int[][] lifeIcon = {{40, 50, 60, 58, 42}, {75, 50, 75, 71, 71}};
    //the sets of points the drawPolygon method would use to draw icons
    private int score;
    private int lvl; //amount of meteors
    private int pDestroyedCount;
    //counter tied to play that controls when the player is shown after being destroyed
    private int count;
    //general count variable
    private final boolean[] keys;
    static Timer timer;
    Player p1;

    //Arraylists that will contain all the different objects related to the game
    private final ArrayList<DustParticles> dustParticles;
    private final ArrayList<Meteoroid> meteors;
    public static ArrayList<Bullet> bullets;
    private final ArrayList<UFO> ufos;
    //Rects that are centered and are used to draw text that is also centered
    private final int[] PLAYGAME_RECT = {WIDTH / 2 - 125, HEIGHT / 2+20, 250, 50};
    private final int[] TITLE_RECT = {WIDTH / 2 - 260, HEIGHT / 2 - 150, 520, 75};
    private final int[] GAMEOVER_RECT = {WIDTH / 2 - 170, HEIGHT / 2 - 100, 350, 50};
    private final int[] SCORE_RECT = {WIDTH / 2 - 90, HEIGHT / 2 + 70, 180, 50};
    private final int[] EXIT_RECT = {WIDTH / 2 - 55, HEIGHT / 2 + 120, 110, 50};
    private final int[] TOPSCORE_RECT = {WIDTH / 2 - 14, HEIGHT / 2 + 70, 28, 50};

    private final int[] MENU_RECT = {WIDTH / 2 - 55, HEIGHT / 2 + 130, 110, 50};
    private final int[] SCORE_MENU_RECT = {WIDTH / 2 - 55, HEIGHT-200, 110, 50};
    private final int[] HIGHSCORE_RECT = {WIDTH / 2 - 183, HEIGHT/2, 366, 40};

private final Music slowBeat = new Music();
    private final Music fastBeat = new Music();
    public AsteroidsPanel() {
        keys = new boolean[KeyEvent.KEY_LAST + 1];
        timer = new Timer(DELAY, this);//Listens to this panel (makes loop)
        p1 = new Player(); //init
        meteors = new ArrayList<Meteoroid>();
        bullets = new ArrayList<Bullet>();
        dustParticles = new ArrayList<DustParticles>();
        ufos = new ArrayList<UFO>();

        lvl = 3;
        lives = 3;
        score = 0;
        pDestroyedCount = 90;
        count = 0;
        timer.start();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
        addMouseListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        //Main game function that controls what is happening at a given time
        //Some methods separated for when the game actually starts
        count ++;
        if (gameState == GAME) {
            collision();
            spawnUFO();
            heartBeat();
        }
        spawnMeteoroids();
        spawnUFO();
        move();
        repaint();
        if (pDestroyedCount < 90)
            pDestroyedCount--;
        //To control how the player doesn't spawn back immediately after being destroyed. It also acts as a boolean
        //as well. When pDesCount >= 90 then the player is in normal game mode. When the player is destroyed pDesCount
        //is subtracted once which causes this to be true to let it approach zero. When it gets there it's set to 90
        //again and everything is reset.
    }

    private void move() {
        //calls the move method for each object
        if (pDestroyedCount >= 90)
            p1.movePlayer(keys); // only move the player if they are in the playable state : pDestroyedCount >= 90
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
            j.drawMeteoroid(g); // things that are always drawn
        for (DustParticles j : dustParticles)
            j.drawDust(g);
        switch (gameState) { // switch statement to what is drawn
            case INTRO -> drawIntro(g);
            case GAME -> drawGame(g);
            case GAMEOVER -> drawGameOver(g);
            case SCORE -> drawScore(g);
        }
    }

    private void drawIntro(Graphics g) {
        g.setColor(Color.WHITE); // set font as hyperspace which is separate from the imported fonts
        // (installer included)
        g.setFont(new Font("Hyperspace", Font.BOLD, 90));
        //Rect is drawn while centered, and then the text is drawn in the same space which makes it appear centered.
        g.drawString("ASTEROIDS", TITLE_RECT[0], TITLE_RECT[1] + TITLE_RECT[3]);
//        g.drawRect(TITLE_RECT[0], TITLE_RECT[1], TITLE_RECT[2], TITLE_RECT[3]);
        g.setFont(new Font("Hyperspace", Font.BOLD, 45));
        g.drawString("PLAY GAME", PLAYGAME_RECT[0], PLAYGAME_RECT[1] + PLAYGAME_RECT[3]);
//        g.drawRect(PLAYGAME_RECT[0], PLAYGAME_RECT[1], PLAYGAME_RECT[2], PLAYGAME_RECT[3]);
        g.drawString("SCORES", SCORE_RECT[0], SCORE_RECT[1] + SCORE_RECT[3]);
//            g.drawRect(SCORE_RECT[0],SCORE_RECT[1],SCORE_RECT[2],SCORE_RECT[3]);
        g.drawString("EXIT", EXIT_RECT[0],EXIT_RECT[1]+EXIT_RECT[3]);
//        g.drawRect(EXIT_RECT[0],EXIT_RECT[1],EXIT_RECT[2],EXIT_RECT[3]);
    }

    private void drawGame(Graphics g) {
        if (pDestroyedCount >= 90)
            p1.draw(g); // only draw player when the ship hasn't been destroyed
        for (Bullet j : bullets)
            j.drawBullet(g);
        for (UFO j : ufos)
            j.drawUFO(g, p1);
        drawLifeIcon(g); // method to draw life icons
        g.setColor(Color.WHITE);
        g.setFont(new Font("Hyperspace", Font.BOLD, 30));
        g.drawString("Score: " + score, 30, 40);
    }

    private void drawGameOver(Graphics g) {
        count = (Math.abs(count) % 50 == 0)?-count: count;
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
        if(orderedScores().size() > 1 && (orderedScores().get(1) < score && count >0)){
            //if the highest previous score is less then the current score start flashing new highscore
            g.drawString("NEW HIGHSCORE", HIGHSCORE_RECT[0], HIGHSCORE_RECT[1] + HIGHSCORE_RECT[3]);
//            g.drawRect(HIGHSCORE_RECT[0],HIGHSCORE_RECT[1],HIGHSCORE_RECT[2],HIGHSCORE_RECT[3]);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Hyperspace", Font.BOLD, 45));
        g.drawString("MENU", SCORE_MENU_RECT[0], SCORE_MENU_RECT[1] + SCORE_MENU_RECT[3]);
//        g.drawRect(SCORE_MENU_RECT[0],SCORE_MENU_RECT[1],SCORE_MENU_RECT[2],SCORE_MENU_RECT[3]);
        for(int i = 0; i < (Math.min(orderedScores().size(), 5)); i++){ //only displays amount of scores until there are 5
            String currScore = orderedScores().get(i) + ""; // gets the top 5 values and displays them
            g.drawString(currScore, TOPSCORE_RECT[0] - currScore.length() * 10,
                    TOPSCORE_RECT[1] + TOPSCORE_RECT[3]+ i*(TOPSCORE_RECT[3] + 5)-200);
//            g.drawRect(TOPSCORE_RECT[0],TOPSCORE_RECT[1] + i*(TOPSCORE_RECT[3] + 5)-200,TOPSCORE_RECT[2],TOPSCORE_RECT[3]);
        }

    }

    public void spawnMeteoroids() {
        if (meteors.isEmpty()) {
            lvl++; // if meteors list is empty 1 more is spawned
            for (int i = 0; i < lvl; i++)
                meteors.add(new Meteoroid(getMeteorRange(WIDTH), getMeteorRange(HEIGHT), 2, newAngle()));
        }
    }

    private void spawnUFO() {
        if (Math.random() * 10000 < lvl && ufos.isEmpty())
            //random chance for ufo to spawn increasing per lvl
            ufos.add(new UFO(getMeteorRange(WIDTH), getMeteorRange(HEIGHT),
                    (int) (Math.random() * 2), newAngle()));
    }

    public void collision() {
        //Method that does all collision calculations and removes unnecessary things from the arraylists
        ArrayList<Meteoroid> oldM = new ArrayList<>();
        ArrayList<Bullet> oldB = new ArrayList<>();
        bullets.forEach(b -> { // a lambda function that evaluates collisions for bullets and other objects
            meteors.stream()
                    .filter(m -> isCircleCollision(m, b))
                    .forEach(m -> { // filter all meteors that have a collision with a bullet
                        oldB.add(b); //add to temporary lists to prevent concurrent modification error
                        oldM.add(m);//that happens when b and m are removed directly
                        spaceObjectDestroyed(b, m, 5 * lvl + m.getSize() + 1);
                        //for each meteor, m, if it's destroyed add score depending on size and level
                        m.getMusic().play();
                    });
            ufos.forEach(u -> spaceObjectDestroyed(b, u, 100));
            ufos.removeIf(u -> isCircleCollision(u, b)&&b.getBullDecay() <60);
            // removes ufo is they intersect with bullets and there has been enough delay
            if (isCircleCollision(p1, b) && b.getBullDecay() <60 && p1.getInvinceCounter() < 0)
                playerDestroyed(); // if player isn't invincible and bullets are past their "buffer time"
        });
        bullets.removeIf(b -> b.getBullDecay() < 0);
        bullets.removeIf(b -> isCircleCollision(p1, b) && b.getBullDecay() < 35);
        for (Meteoroid meteor : meteors) {
            //if for any meteor that collides with player = true and player can be destroyed, it is
            if (isCircleCollision(p1, meteor) && p1.getInvinceCounter() < 0 && pDestroyedCount >= 90) {
                playerDestroyed();
                break; // breaks after player destroyed
            }
        }
        for (UFO ufo : ufos) {
            //if for any ufo that collides with player = true and player can be destroyed, it is
            if (isCircleCollision(p1, ufo) && p1.getInvinceCounter() < 0 && pDestroyedCount >= 90) {
                playerDestroyed();
                break; // breaks after player destroyed
            }
        }
        dustParticles.removeIf(DustParticles::getTime);
        bullets.removeAll(oldB); // removes dust particles if there time is less than zero (true)
        meteors.removeAll(oldM); // removes all collected objects from bullets and meteors
        if (!oldM.isEmpty() && oldM.get(0).getSize() > 0) {
            Meteoroid m = oldM.get(0); // ^ If there is something to remove in oldM, and it isn't the smallest size
            meteors.add(new Meteoroid(m.getX(), m.getY(), m.getSize() - 1, newAngle()));
            meteors.add(new Meteoroid(m.getX(), m.getY(), m.getSize() - 1, newAngle()));
        } //^ add 2 meteors with new angles and a smaller size
        newPlayer();
        //creates a new player when possible
    }

    private void newPlayer() {
        if (lives <= 0 && pDestroyedCount < 0) {
            gameState = GAMEOVER;
            addScore(String.valueOf(score));
        }
        if (lives > 0 && pDestroyedCount < 0) {
            p1 = new Player(); // if still in game then reset player
            pDestroyedCount = 90; // resets counter to 90 so that the "toggle" for players destroyed state is false;
            //No actual toggle; it's just controlled by whether pDesCount >= 90 or < 90
            p1.setInvinceCounter(90);
        }

    }

    private void playerDestroyed() {
        lives--;
        pDestroyedCount--;
        //creates 3 new lines when ship destroyed
        for (int i = 0; i < 3; i++)
            dustParticles.add(new DustParticles((int) (p1.getX() + (Math.random() * 30)),
                    (int) (p1.getY() + (Math.random() * 30)), newAngle(), LINE));
    }

    private void spaceObjectDestroyed(SpaceObject b, SpaceObject u, int scorePlus) {
        if (isCircleCollision(b, u)) {
            score += scorePlus;
            //if anything hits anything else, this function adds the dictated score and
            //creates a bunch of particles
            double newAngle = Math.random() * 360;
            for (int i = 0; i < Math.random() * 5 + 2; i++)
                dustParticles.add(new DustParticles((int) (u.getX() + (Math.random() * 30)),
                        (int) (u.getY() + (Math.random() * 30)), newAngle, PARTICLE));
        }
    }

    private double newAngle() {
        return Math.random() * 30 + 30 + (int) (Math.random() * 2) * 90;
    }
    //gets an angle that won't be close to any cardinal directions (to prevent things getting stuck offscreen)
    private boolean isCircleCollision(SpaceObject a, SpaceObject b) {
        //checks circle collision using general formula
        double xDif = b.getX() - a.getX();
        double yDif = b.getY() - a.getY();
        double distanceSquared = xDif * xDif + yDif * yDif;
        return distanceSquared < (a.wid + b.wid) * (a.wid + b.wid);
    }

    private void heartBeat() {
        if(count % 60 == 0 && lives > 1){
            // Audio seems to glitch if file is previously set before playing
            slowBeat.setFile("Nov1_AsteroidsGame/sound/beat1.wav");
            slowBeat.play();
        } // plays a different beat depending on lives
        if(count % 30 == 0 && lives == 1){
            fastBeat.setFile("Nov1_AsteroidsGame/sound/beat2.wav");
            fastBeat.play();
        }
    }

    private void drawLifeIcon(Graphics g) {
        g.setColor(Color.WHITE);
        for (int i = 0; i < lives; i++) {
            int[] xPts = lifeIcon[0].clone();
            //only the xPoints change when drawing multiple icons, so those are modified
            for (int j = 0; j < xPts.length; j++)
                xPts[j] += 25 * i; // adding 25 as distance between icons
            g.drawPolygon(xPts, lifeIcon[1], 5);
            // using polygon method to draw using an array of coords as input
        }
    }

    private int getMeteorRange(int dir) {
        //Method to get the possible ranges for something to spawn that is out of bounds. Creates the effect that
        //asteroids are falling into screen instead of just appearing there. Also creates a reason to not camp at the edge
        //of the screen as asteroids can now spawn right at the edge
        return(int) ((Math.random() > 0.5) ? Math.random()*-200 : dir+ Math.random() * 200);
    }

    public static int getWIDTH() {
        return WIDTH;
    }
    //getters for wid and height to use in other classes. Program should be able to handle all reasonable wid & hgt
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
        //detects if a button is clicked and sets the gameState
        Point mousePos = getMousePosition().getLocation();
        if(inRect(mousePos, EXIT_RECT) && gameState == INTRO)
            System.exit(0); // exits if exit is clicked
        if (inRect(mousePos, PLAYGAME_RECT) && gameState == INTRO)
            gameState = GAME;
        if(inRect(mousePos, SCORE_RECT) && gameState == INTRO)
            gameState = SCORE;
        if(inRect(mousePos, SCORE_MENU_RECT)&&gameState == SCORE)
            gameState = INTRO;
        if (inRect(mousePos, MENU_RECT) && gameState == GAMEOVER)
            resetGame(); // pressing menu back is the only way to replay

    }
    private void resetGame(){
        //Resets all values to replay the game
        gameState = INTRO;
        p1 = new Player();
        meteors.clear();
        ufos.clear();
        score = 0;
        lives = 3;
        lvl=3;
    }

    private boolean inRect(Point p, int[] arr) {
        //checks if the mouse position is inside the button border when it's clicked
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

    private void addScore(String newScore) {

        try {
            PrintWriter fileOut = new PrintWriter(new BufferedWriter(new FileWriter("scores.txt", true)));
            fileOut.println(newScore); // writes new score to the current file
            fileOut.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Integer> orderedScores() {
        //looks at the scores file and returns a sorted arraylist of numbers from descending order
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