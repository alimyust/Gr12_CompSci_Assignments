import java.awt.*;
import java.awt.event.KeyEvent;
//import java.awt.geom.AffineTransform;

public class Player extends SpaceObject{
    private int x;
    private int y;
    private double vx;
    private double vy;
    private final int boost;
    private final int left;
    private final int right;
    private final int score;
    private final int lives;
    private double angle;

    private final int legDist; // angle legs are apart from middle (2*legDist = total dist)
    private final int legD ;
    private final int legLength ;
    private int bullCounter;
//    public Player(int x, int y, double angle) {
//        super(x, y, angle);
//    }


    public Player() {
        super();
        this.x = AsteroidsPanel.getWIDTH() / 2;
        this.y = AsteroidsPanel.getHEIGHT() / 2;
        this.wid = 60;
        this.vx = 0;
        this.vy = 0;
        this.score = 0;
        this.angle = 0;
        this.lives = 3;
        this.boost = KeyEvent.VK_W;
        this.left = KeyEvent.VK_A;
        this.right = KeyEvent.VK_D;
        this.legDist = 50;
        this.legD = 10;
        this.legLength=30;
        this.bullCounter = 40;
    }
    public static double getCos(double angle){
        return Math.cos(Math.toRadians(angle));
    }
    public static double getSin(double angle){
        return Math.sin(Math.toRadians(angle));
    }
    public void movePlayer(boolean[] keys) {
        double acc = 1;
        bullCounter--;
        if(keys[KeyEvent.VK_SPACE] && bullCounter < 0) {
            AsteroidsPanel.bullets.add(new Bullet(this.x - (int) (legLength* getCos(angle)), this.y - (int) (legLength * getSin(angle)), angle));
            bullCounter = 60;
        }
        if (keys[this.boost]) {
            this.vx -= acc * getCos(angle);
            this.vy -= acc * getSin(angle);
        }
        if (keys[this.left])
            this.angle -= 6;
        if (keys[this.right])
            this.angle += 6;

        if (Math.abs(this.vy) > acc*3)
            this.vy = (this.vy > 0)?acc*3: - acc*3;
        if (Math.abs(this.vx) > acc*3)//speed limit
            this.vx = (this.vx > 0)?acc*3: - acc*3;
//        System.out.println(vx +" , "+ vy);
        this.x += (int) this.vx;
        this.y += (int) this.vy;
        this.vx += (this.vx > acc/30) ? -acc / 30 : acc / 30;
        this.vy += (this.vy > acc/30) ? -acc / 30 : acc / 30;
        playerBoundary();
    }
    public Rectangle playerRect(){ // Special case for player
        return new Rectangle(x-wid/2,y-wid/2,wid,wid);
    }
    private void playerBoundary() {
        if (this.x > AsteroidsPanel.getWIDTH())
            this.x = 0;
        if (this.x < 0)
            this.x = AsteroidsPanel.getWIDTH();
        if (this.y > AsteroidsPanel.getHEIGHT())
            this.y = 0;
        if (this.y < 0)
            this.y = AsteroidsPanel.getHEIGHT();
    }

    public void draw(Graphics g) {

        g.setColor(Color.WHITE);
//        g.fillRect(this.x, this.y, 20, 20);
        g.setColor(Color.red);
        g.drawLine(this.x - (int) (legLength* getCos(angle)), this.y - (int) (legLength * getSin(angle)),
                legD+x+(int) (legLength*getCos(angle-legDist)), legD+y+(int) (legLength*getSin(angle-legDist)));
        g.drawLine(this.x - (int) (legLength* getCos(angle)), this.y - (int) (legLength * getSin(angle)),
                legD+x+(int) (legLength*getCos(angle+legDist)),legD+y+(int) (legLength*getSin(angle+legDist)));

//        g.drawLine(legD+x+(int) (legLength*getCos(angle+legDist)),legD+y+(int) (legLength*getSin(angle+legDist)), (int) (x+50*getCos(angle)), (int) (y+50*getSin(angle)));
//        g.drawLine(legD+x+(int) (legLength*getCos(angle-legDist)), legD+y+(int) (legLength*getSin(angle-legDist)),(int) (x+50*getCos(angle)), (int) (y+50*getSin(angle)));
        g.setColor(Color.GREEN);
        g.drawLine(x,y,this.x - (int) (legLength* getCos(angle)), this.y - (int) (legLength * getSin(angle)));
      g.drawRect(x-wid/2,y-wid/2,wid,wid);
//        g.setColor(Color.GREEN);
//        g.drawLine(this.x - (int) (30* getCos(angle)), this.y - (int) (30 * getSin(angle)),
//                this.x + (int) (50* getCos(angle)), this.y + (int) (50 * getSin(angle)));
    }
}
