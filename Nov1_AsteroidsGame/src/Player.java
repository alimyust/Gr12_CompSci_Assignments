import java.awt.*;
import java.awt.event.KeyEvent;

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
        this.boost = KeyEvent.VK_UP;
        this.left = KeyEvent.VK_LEFT;
        this.right = KeyEvent.VK_RIGHT;
        this.angle = 0;
    }

    public void movePlayer(boolean[] keys) {
        double acc = 0.1;
        if (keys[this.boost]) {
            this.vx += acc * Math.cos(Math.toRadians(this.angle));
            this.vy += acc * Math.sin(Math.toRadians(this.angle));
        }
        if (keys[this.left])
            this.angle -= 2;
        if (keys[this.right])
            this.angle += 2;

        if (this.vy > 3)
            this.vy = 3;
        if (this.vx > 3)
            this.vx = 3;

        this.x += this.vx;
        this.y += this.vy;
//        this.vx += (this.vx > acc/5) ? -acc / 2 : acc / 2;
//        this.vy += (this.vy > acc/5) ? -acc / 2 : acc / 2;
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
        g.fillRect(this.x, this.y, 20, 20);
        g.setColor(Color.GREEN);
        g.drawLine(this.x, this.y,
                this.x + (int) (this.x * Math.cos(Math.toRadians(angle))), this.y + (int) (this.y * Math.sin(Math.toRadians(angle))));
    }

    public Rectangle getRect() {
        return new Rectangle(this.x, this.y, 20, 20);
    }

}
