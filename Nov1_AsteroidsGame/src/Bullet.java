import java.awt.*;
//Bullets class that inherits from spaceObj
//has a set constant velocity (speed) and a limted lifespan (decay)
public class Bullet extends SpaceObject{
    private int bullDecay;

    public Bullet(int x, int y, double angle, int speed) {
        super(x, y, angle,speed,speed,2);
        bullDecay = 70;
        //Bullets range before despawning
    }
    public void drawBullet(Graphics g){
        bullDecay--;
        g.setColor(Color.RED);
        g.drawOval(this.getX(),this.getY(),this.wid,this.wid);
    }
    //getter to remove bullet when it is less than zero
    public int getBullDecay() {
        return bullDecay;
    }
}