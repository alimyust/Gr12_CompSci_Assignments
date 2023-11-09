import java.awt.*;

public class UFO extends SpaceObject{
    public UFO(int x, int y, int wid, double angle) {
        super(x, y, wid, angle);
    }
    public void drawUFO(Graphics g){
        g.setColor(Color.RED);
        g.drawOval(x-wid/2,y-wid/2,wid,wid);
        g.setColor(Color.WHITE);
    }
}
