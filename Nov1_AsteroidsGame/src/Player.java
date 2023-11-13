import java.awt.*;
import java.awt.event.KeyEvent;
//import java.awt.geom.AffineTransform;

public class Player extends SpaceObject {
    private final int boost;
    private final int left;
    private final int right;
    private double angle;
    private int bullCounter;
    private int invinceCounter;
    private boolean isBoost;
    private final double[][] DEFAULT_SHIP;

    public Player() {
        super(AsteroidsPanel.getWIDTH() / 2, AsteroidsPanel.getHEIGHT() / 2, 0.0, 0, 0, 60);
        this.boost = KeyEvent.VK_W;
        this.left = KeyEvent.VK_A;
        this.right = KeyEvent.VK_D;
        this.bullCounter = 10;
        this.invinceCounter = 60;
        this.isBoost = false;
        DEFAULT_SHIP = new double[][]{{30, -30, 30, 20, 20,28,20,28,20,20}, {-15, 0, 15, 12, -12,-6,0,6,12,-12}};
    }

    public void movePlayer(boolean[] keys) {
        double acc = 1;
        bullCounter--;
        int[][] rotatedPoints = rotatePoints(DEFAULT_SHIP, this.angle, this.x, this.y);
        if (keys[KeyEvent.VK_SPACE] && bullCounter < 0) {
            AsteroidsPanel.bullets.add(new Bullet(rotatedPoints[0][1], rotatedPoints[1][1], angle, 10));
            bullCounter = 10;
        }
        if (keys[this.boost]) {
            this.vx -= acc * getCos(angle);
            this.vy -= acc * getSin(angle);
            this.isBoost = true;
        } else {this.isBoost = false;}
        if (keys[this.left])
            this.angle -= 6;
        if (keys[this.right])
            this.angle += 6;
        int maxSpeed = 3;
        if (Math.abs(this.vy) > maxSpeed)
            this.vy = (this.vy > 0) ? maxSpeed : -maxSpeed;
        if (Math.abs(this.vx) > maxSpeed)//speed limit
            this.vx = (this.vx > 0) ? maxSpeed : -maxSpeed;

        this.x += (int) this.vx;
        this.y += (int) this.vy;
        this.vx += (this.vx > acc / 100) ? -acc / 100 : acc / 100;
        this.vy += (this.vy > acc / 100) ? -acc / 100 : acc / 100;
        spaceObjectBoundary();
    }

    public Rectangle playerRect() { // Special case for player
        return new Rectangle(x - wid / 2, y - wid / 2, wid, wid);
    }

    public void draw(Graphics g) {
        invinceCounter--;

        int[][] rotatedPoints = rotatePoints(DEFAULT_SHIP, this.angle, this.x, this.y);
        g.setColor(Color.WHITE);
        if (invinceCounter < 0 || invinceCounter % 10 == 0) {
            if(this.isBoost && bullCounter % 5 == 0)
                g.drawPolygon(rotatedPoints[0], rotatedPoints[1], 10);
            else
                g.drawPolygon(rotatedPoints[0], rotatedPoints[1], 5);
        }
//        g.drawOval(this.x-this.wid/2,this.y-this.wid/2,this.wid,this.wid);
//        g.setColor(Color.RED);
//        g.drawRect(x-wid/2,y-wid/2,wid,wid);
    }

    public int getInvinceCounter() {
        return invinceCounter;
    }

    public void setInvinceCounter(int invinceCounter) {
        this.invinceCounter = invinceCounter;
    }
}