import java.awt.*;
import java.awt.event.KeyEvent;
//import java.awt.geom.AffineTransform;

public class Player {
    private int x;
    private int y;
    private double vx;
    private double vy;
    private int boost;
    private int left;
    private int right;
    private int score;
    private int lives;
    private double angle;

    public Player() {
        this.x = AsteroidsPanel.getWIDTH() / 2;
        this.y = AsteroidsPanel.getHEIGHT() / 2;
        this.vx = 0;
        this.vy = 0;
        this.score = 0;
        this.lives = 3;
        this.boost = KeyEvent.VK_W;
        this.left = KeyEvent.VK_A;
        this.right = KeyEvent.VK_D;
        this.angle = 0;
    }
    public static double getCos(double angle){
        return Math.cos(Math.toRadians(angle));
    }
    public static double getSin(double angle){
        return Math.sin(Math.toRadians(angle));
    }
    public void movePlayer(boolean[] keys) {
        double acc = 1;
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

        g.drawLine(this.x - (int) (30* getCos(angle)), this.y - (int) (30 * getSin(angle)), 10+x+(int) (50*getCos(angle-15)), 10+y+(int) (50*getSin(angle-15)));
        g.drawLine(this.x - (int) (30* getCos(angle)), this.y - (int) (30 * getSin(angle)),10+x+(int) (50*getCos(angle+15)),10+y+(int) (50*getSin(angle+15)));

//        g.setColor(Color.GREEN);
//        g.drawLine(this.x - (int) (30* getCos(angle)), this.y - (int) (30 * getSin(angle)),
//                this.x + (int) (50* getCos(angle)), this.y + (int) (50 * getSin(angle)));
    }

    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, 20, 20);
    }

}
