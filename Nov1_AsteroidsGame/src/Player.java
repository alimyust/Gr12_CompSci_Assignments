import java.awt.*;
import java.awt.event.KeyEvent;
//import java.awt.geom.AffineTransform;

public class Player extends SpaceObject{
    private final int boost;
    private final int left;
    private final int right;
    private double angle;
    private int bullCounter;
    private int invinceCounter;
    //    public Player(int x, int y, double angle) {
//        super(x, y, angle);
//    }
    private double[][] blownShipPts;
    private double[][] DEFAULT_SHIP;

    public Player(int x, int y, double angle, int vx, int vy,int wid) {
        super(x,y,angle,vx,vy,wid);
        this.boost = KeyEvent.VK_W;
        this.left = KeyEvent.VK_A;
        this.right = KeyEvent.VK_D;
        this.bullCounter = 40;
        this.invinceCounter = 60;
        DEFAULT_SHIP = new double[][]{{-30,30,30}, {0,-15,15}};
    }
    public void movePlayer(boolean[] keys) {
        double acc = 1;
        bullCounter--;
        int[][] rotatedPoints = rotatePoints(DEFAULT_SHIP, this.angle,this.x,this.y);
        if(keys[KeyEvent.VK_SPACE] && bullCounter < 0) {
            AsteroidsPanel.bullets.add(new Bullet(rotatedPoints[0][0],rotatedPoints[1][0], angle));
            bullCounter = 2;
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
        spaceObjectBoundary();
    }
    public Rectangle playerRect(){ // Special case for player
        return new Rectangle(x-wid/2,y-wid/2,wid,wid);
    }
    public void spaceObjectBoundary() {
        if (this.x - wid> AsteroidsPanel.getWIDTH())
            this.x = -wid;
        if (this.x+wid < -wid)
            this.x = AsteroidsPanel.getWIDTH()+wid;
        if (this.y- wid > AsteroidsPanel.getHEIGHT())
            this.y = -wid;
        if (this.y +wid< -wid)
            this.y = AsteroidsPanel.getHEIGHT()+wid;
    }

    public void draw(Graphics g) {
        invinceCounter --;

        int[][] rotatedPoints = rotatePoints(DEFAULT_SHIP, this.angle,this.x,this.y);
        g.setColor(Color.WHITE);
        if(invinceCounter < 0 || invinceCounter % 10 == 0)
            g.drawPolygon(rotatedPoints[0],rotatedPoints[1],3);
//        g.drawOval(this.x-this.wid/2,this.y-this.wid/2,this.wid,this.wid);
//        g.setColor(Color.RED);
//        g.drawRect(x-wid/2,y-wid/2,wid,wid);
    }
    public void deathAnimation(Graphics g){
//        int x = this.blownShipPts[0];
//        int y = this.blownShipPts[1];
        g.setColor(Color.WHITE);
    for(int i=0; i < 3; i++)
        g.drawLine((int)blownShipPts[i][0],(int)blownShipPts[i][1],(int)blownShipPts[i][2],(int)blownShipPts[i][3]);
    blownShipPts[0][0]+=0.5;
    blownShipPts[0][1]+=0.5;
    blownShipPts[0][2]+=0.5;
    blownShipPts[0][3]+=0.5;
    blownShipPts[1][0]-=0.5;
    blownShipPts[1][1]-=0.5;
    blownShipPts[1][2]-=0.5;
    blownShipPts[1][3]-=0.5;
    blownShipPts[2][0]+=0.5;
    blownShipPts[2][1]+=0.5;
    blownShipPts[2][2]+=0.5;
    blownShipPts[2][3]+=0.5;

    }

    public int getInvinceCounter() {
        return invinceCounter;
    }

    public void setInvinceCounter(int invinceCounter) {
        this.invinceCounter = invinceCounter;
    }

    public void setBlownShipPts(double[][] blownShipPts) {
        this.blownShipPts = blownShipPts;
    }
}




//        g.drawLine(x,y,this.x - (int) (legLength* getCos(angle)), this.y - (int) (legLength * getSin(angle)));
//        g.drawRect(x-wid/2,y-wid/2,wid,wid);
//        g.setColor(Color.GREEN);
//        g.drawLine(this.x - (int) (30* getCos(angle)), this.y - (int) (30 * getSin(angle)),
//                this.x + (int) (50* getCos(angle)), this.y + (int) (50 * getSin(angle)));



//        g.drawLine((int) (x-outer*getCos(angle)), (int) (y-outer*getSin(angle)),
//                (int) (x+outer*getCos(angle+legSpread)), (int) (y+outer*getSin(angle+legSpread)));
//
//                g.drawLine((int) (x-outer*getCos(angle)), (int) (y-outer*getSin(angle)),
//                (int) (x+outer*getCos(angle-legSpread)), (int) (y+outer*getSin(angle-legSpread)));
////        g.setColor(Color.GREEN);
//        g.drawLine(x,y, (int) (x-outer*getCos(angle)), (int) (y-outer*getSin(angle)));