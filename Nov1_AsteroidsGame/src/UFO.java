import java.awt.*;

public class UFO extends SpaceObject{
    private int bullCounter;
    public UFO(int x, int y, int wid, double angle) {
        super(x, y, wid, angle);
        this.bullCounter = 0;
    }
    public void drawUFO(Graphics g, Player p1){
        ufoShoot(p1);
        g.setColor(Color.RED);
        g.drawOval(x-wid/2,y-wid/2,wid,wid);
        g.setColor(Color.WHITE);
    }
    private void ufoShoot(Player p1){
        bullCounter++;
        double shootingAngle = Math.atan2((p1.getY() - y),(p1.getX()-x ));// Math.PI/2;
//        System.out.println((double)wid/1.5);
        if(bullCounter % 100 == 0){
            AsteroidsPanel.bullets.add(new Bullet( x+(int) ((double)wid*1.1*Math.cos(shootingAngle)),
                    y+(int) ((double)wid*1.1*Math.sin(shootingAngle)),Math.toDegrees(shootingAngle + Math.PI)));
//            x+(int) (wid*Math.cos(shootingAngle)),
//
        }

    }
}
