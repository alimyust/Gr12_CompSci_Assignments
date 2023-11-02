import java.awt.*;

public class Bullet extends SpaceObject{
    public Bullet(int x, int y, int wid) {
        super(x, y, wid);
    }
    public void drawBullet(Graphics g){
        g.drawOval(this.x,this.y,this.wid,this.wid);
    }
}
