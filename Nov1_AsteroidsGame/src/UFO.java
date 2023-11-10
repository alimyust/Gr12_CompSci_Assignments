import java.awt.*;

public class UFO extends SpaceObject{
    private int bullCounter;
    private double[][] ufoPts = {{-50,50,20,-20,-50,-20,-10,10,20,-20,20,50},{0,0,20,20,0,-20,-30,-30,-20,-20,-20,0}};
    private double[][] currPts;

    private final static int[] WID = {50,20};
    public UFO(int x, int y, int size, double angle) {
        super(x, y, WID[size], angle);
        this.bullCounter = 0;
        double[][] bigUfoPts = ufoPts;
        double[][] smlUfoPts = new double[2][ufoPts[0].length];
        for(int i=0; i < ufoPts[0].length; i++) {
            smlUfoPts[0][i] = bigUfoPts[0][i] / 2;
            smlUfoPts[1][i] = bigUfoPts[1][i] / 2;
        }
        currPts = (size == 1)? smlUfoPts: bigUfoPts;

    }
    public void drawUFO(Graphics g, Player p1){
        int [][]rotatePoints = rotatePoints(currPts,0,x,y);
        ufoShoot(p1);
        g.setColor(Color.RED);
        g.drawOval(x-wid/2,y-wid/2,wid,wid);
        g.drawPolygon(rotatePoints[0],rotatePoints[1],12);
        g.setColor(Color.WHITE);
    }
    private void ufoShoot(Player p1){
        bullCounter++;
        double shootingAngle = Math.atan2((p1.getY() - y),(p1.getX()-x ));// Math.PI/2;
//        System.out.println((double)wid/1.5);
        if(bullCounter % 100 == 0){
            AsteroidsPanel.bullets.add(new Bullet( x+(int) ((double)wid*1.1*Math.cos(shootingAngle)),
                    y+(int) ((double)wid*1.1*Math.sin(shootingAngle)),Math.toDegrees(shootingAngle + Math.PI) +Math.random()*90-45,5));
//            x+(int) (wid*Math.cos(shootingAngle)),
//
        }

    }
}
