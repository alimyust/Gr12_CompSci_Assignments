import java.awt.*;

public class Bullet extends SpaceObject{
    private int bullDecay;
    public Bullet(int x, int y, double angle) {
        super(x, y, angle);
        bullDecay = 30;
        //Bullets range before despawning
    }
    public void drawBullet(Graphics g){
        bullDecay--;
        g.drawOval(this.getX(),this.getY(),this.wid,this.wid);
    }

    public int getBullDecay() {
        return bullDecay;
    }
}