import java.awt.*;

public class SpaceObject {
    private int x;
    private int y;
    private int vx;
    private int vy;
    private int angle;
    protected int wid;

    public SpaceObject(){
    }
    public SpaceObject(int x, int y,double angle) {
        this(x,y,3,angle);
        this.vx = 10;
        this.vy = 10;
    }

    public SpaceObject(int x, int y, int wid, double angle) {
        this.x = x;
        this.y = y;
        this.wid = wid;
        this.angle = (int) angle;
        this.vx = (Math.random() > 0.5)?(int) (Math.random()*3+2):(int) -(Math.random()*3+2);
        this.vy = (Math.random() > 0.5)?(int) (Math.random()*3+2):(int) -(Math.random()*3+2);
//        System.out.println(angle + " , "+ vx + " , "+vy);
    }
    public void moveSpaceObject(){
        this.x-= (int) (this.vx*Player.getCos(angle));
        this.y-= (int) (this.vy*Player.getSin(angle));
        spaceObjectBoundary();
    }

    public void spaceObjectBoundary() {
        //Allows object to get out of frame before going to the other side
        if (this.x > AsteroidsPanel.getWIDTH())
            this.x = -wid;
        if (this.x < -wid)
            this.x = AsteroidsPanel.getWIDTH();
        if (this.y > AsteroidsPanel.getHEIGHT())
            this.y = -wid;
        if (this.y < -wid)
            this.y = AsteroidsPanel.getHEIGHT();
    }
    public Rectangle getRect(){
        return new Rectangle(this.x,this.y,this.wid,this.wid);
    }
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}