import java.awt.*;
import java.awt.event.KeyEvent;
//player class that inherits from spaceObj
//Contains special movement, painting and shooting methods since it behaves somewhat differently from everything else
public class Player extends SpaceObject {
    private final int boost;
    private final int left;
    private final int right;
    private double angle;
    private int bullCounter;
    private int invinceCounter;
    private boolean isBoost;
    private final double[][] DEFAULT_SHIP = new double[][]{
            {30, -30, 30, 20, 20, 35, 20, 35, 20, 20}, {-15, 0, 15, 12, -12, -6, 0, 6, 12, -12}};
    //points of ship centered around origin when angle is zero. Base ship that will be transformed
    private boolean oldSpace;
    private boolean space;
    private final Music fireSound = new Music();
    //music object

    public Player() {
        //constructed from a more specific parent constructor that doesn't set velocity randomly
        super(AsteroidsPanel.getWIDTH() / 2, AsteroidsPanel.getHEIGHT() / 2, 0.0, 0, 0, 9);
        this.boost = KeyEvent.VK_W;
        this.left = KeyEvent.VK_A;
        this.right = KeyEvent.VK_D;
        this.bullCounter = 10;
        this.invinceCounter = 90;
        this.isBoost = false;
        this.space = false;
    }

    public void movePlayer(boolean[] keys) {
        double acc = 1;
        bullCounter--;
        space = keys[KeyEvent.VK_SPACE];
        int[][] rotatedPoints = rotatePoints(DEFAULT_SHIP, this.angle, this.x, this.y);
        //creates a set of points around the current x and y, rotated by this.angle
//        if (keys[KeyEvent.VK_SPACE] && bullCounter < 0) {
//            AsteroidsPanel.bullets.add(new Bullet(rotatedPoints[0][1], rotatedPoints[1][1], angle, 10));
//            bullCounter = 10;
//        }
        if (oldSpace != space && !space){
            //if you pressed space and currently aren't add a new bullet and make a shooting noise
            AsteroidsPanel.bullets.add(new Bullet(rotatedPoints[0][1], rotatedPoints[1][1], angle, 10));
            fireSound.setFile("Nov1_AsteroidsGame/sound/fire.wav");
            fireSound.play();
        }
        if (keys[this.boost]) {
            final Music thrustSound = new Music();
            thrustSound.setFile("Nov1_AsteroidsGame/sound/thrust.wav");
            thrustSound.play();
            //when boosting add to velocity
            this.vx -= acc * getCos(angle);
            this.vy -= acc * getSin(angle);
            this.isBoost = true;
        } else {
            this.isBoost = false;
        }
        if (keys[this.left])
            this.angle -= 6;//turning speed
        if (keys[this.right])
            this.angle += 6;
        int maxSpeed = 3; // limits max speed
        if (Math.abs(this.vy) > maxSpeed)
            this.vy = (this.vy > 0) ? maxSpeed : -maxSpeed;
        if (Math.abs(this.vx) > maxSpeed)//speed limit
            this.vx = (this.vx > 0) ? maxSpeed : -maxSpeed;

        this.x += (int) this.vx;
        this.y += (int) this.vy; //"space friction" that slows ship to stop if there isn't input
        this.vx += (this.vx > acc / 100) ? -acc / 100 : acc / 100;
        this.vy += (this.vy > acc / 100) ? -acc / 100 : acc / 100;
        oldSpace = space;
        spaceObjectBoundary();
        //calls boundary
    }

    public void draw(Graphics g) {
        invinceCounter--;

        int[][] rotatedPoints = rotatePoints(DEFAULT_SHIP, this.angle, this.x, this.y);
        g.setColor(Color.WHITE);
        if (invinceCounter < 0 || invinceCounter % 10 == 0) {//creates the flashing while invincible effect while spawning
            if (this.isBoost && bullCounter % 5 == 0)//creates flashing booster trail
                g.drawPolygon(rotatedPoints[0], rotatedPoints[1], 10);
            else
                g.drawPolygon(rotatedPoints[0], rotatedPoints[1], 5);
            //the last 5 points include the boost trail, so by changing the ammount of points used in drawing you can
            //toggle the trail on and off
        }
    }

    public int getInvinceCounter() {
        return invinceCounter;
    }
//setter and getters
    public void setInvinceCounter(int invinceCounter) {
        this.invinceCounter = invinceCounter;
    }
}