import javax.swing.*;
import java.awt.*;

public class SpaceObject {
    protected int x;
    protected int y;
    protected double vx;
    protected double vy;
    private final int angle;
    protected int wid;
    private int[][] dustParticles;
    public SpaceObject(int x, int y,double angle,int vx, int vy, int wid) {
        this.x = x;
        this.y = y;
        this.angle = (int)angle;
        this.vx = vx;
        this.vy = vy;
        this.wid = wid;
    }

    public SpaceObject(int x, int y, int wid, double angle) {
        this.x = x;
        this.y = y;
        this.wid = wid;
        this.angle = (int) angle;
        this.vx = (Math.random() > 0.5)? (Math.random()*3+2):- (Math.random()*3+2);
        this.vy = this.vx;//(Math.random() > 0.5)? (Math.random()*23+2):- (Math.random()*23+2);
//        System.out.println(angle + " , "+ vx + " , "+vy);
    }
    public void moveSpaceObject(){
        this.x-= (int) (this.vx*Player.getCos(angle));
        this.y-= (int) (this.vy*Player.getSin(angle));
        spaceObjectBoundary();
    }

    public void spaceObjectBoundary() {
        //Allows object to get out of frame before going to the other side
//        System.out.println(this.x +" , " + this.y);
        if (this.x - wid> AsteroidsPanel.getWIDTH())
            this.x = -wid;
        if (this.x+wid < -wid)
            this.x = AsteroidsPanel.getWIDTH()+wid;
        if (this.y- wid > AsteroidsPanel.getHEIGHT())
            this.y = -wid;
        if (this.y +wid< -wid)
            this.y = AsteroidsPanel.getHEIGHT()+wid;
    }
//    public Rectangle getRect(){
//        return new Rectangle(this.x,this.y,this.wid,this.wid);
//    }
    public static double getCos(double angle){
        return Math.cos(Math.toRadians(angle));
    }
    public static double getSin(double angle){
        return Math.sin(Math.toRadians(angle));
    }
    public static int[][] rotatePoints(double[][] arrListPoints, double angle, int orgX, int orgY){
        //Rotates a list of points by taking an angle and a coordinate to center the rotation on
        int[][] rotatedPoints = new int[2][arrListPoints[0].length];
        for(int i = 0; i < arrListPoints[0].length; i++){
            double oldX = arrListPoints[0][i];
            double oldY = arrListPoints[1][i];
            rotatedPoints[0][i] = orgX +(int) ( oldX * getCos(angle) - oldY*getSin(angle));
            rotatedPoints[1][i] = orgY +(int) ( oldX * getSin(angle) + oldY*getCos(angle));
        }
        return rotatedPoints;
    }
    public void dustAnimation(Graphics g){
        for(int i = 0; i < 5; i++) {
            g.drawOval(dustParticles[i][0], dustParticles[i][1], 1, 1);
            dustParticles[i][0] += 3;
            dustParticles[i][1] += 3;
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDustParticles(int[][] dustParticles) {
        this.dustParticles = dustParticles;
    }
}