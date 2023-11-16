// The parent class for all the different game pieces. Many objects share similar behaviour regarding
//movement and boundaries, so this class is a template for all of them
public class SpaceObject {
    protected int x;
    protected int y;
    protected double vx;
    protected double vy;
    private final int angle;
    protected int wid;
    public SpaceObject(int x, int y,double angle,int vx, int vy, int wid) {
        this.x = x;
        this.y = y;
        this.angle = (int)angle;
        this.vx = vx;
        this.vy = vy;
        this.wid = wid;
    }

    public SpaceObject(int x, int y, int wid, double angle) {
        //Constructor for objects where velocity is random
        this.x = x;
        this.y = y;
        this.wid = wid;
        this.angle = (int) angle;
        this.vx = (Math.random() > 0.5)? (Math.random()*3+2):- (Math.random()*3+2);
        this.vy = this.vx; // creates a random velocity and both vx and vy are the same to make sure that there aren't
        //Huge disparities between vx and vy. If there are, it could lead to the object behaving as if it only moves
        //horizontally or vertically, and it would get stuck off-screen.
        //Still use 2 velocities because other objects utilize both
    }
    public void moveSpaceObject(){
        //adds the x and y component of the circle with the velocity as the radius per frame
        this.x-= (int) (this.vx*Player.getCos(angle));
        this.y-= (int) (this.vy*Player.getSin(angle));
        spaceObjectBoundary();
    }

    public void spaceObjectBoundary() {
        //Allows objects to get out of frame before going to the other side
        if (this.x - wid> AsteroidsPanel.getWIDTH())
            this.x = -wid;
        if (this.x+wid < -wid)
            this.x = AsteroidsPanel.getWIDTH()+wid;
        if (this.y- wid > AsteroidsPanel.getHEIGHT())
            this.y = -wid;
        if (this.y +wid< -wid)
            this.y = AsteroidsPanel.getHEIGHT()+wid;
    }
    public static double getCos(double angle){
        return Math.cos(Math.toRadians(angle));
    }
    //functions to streamline code and prevent constant conversion to radians in main methods
    public static double getSin(double angle){
        return Math.sin(Math.toRadians(angle));
    }
    public static int[][] rotatePoints(double[][] arrListPoints, double angle, int orgX, int orgY){
        //Rotates a list of points by taking an angle and a coordinate to center the rotation on
        //Allows optional rotation (angle = 0 -> no rotation) or just centering on a point
        int[][] rotatedPoints = new int[2][arrListPoints[0].length];
        for(int i = 0; i < arrListPoints[0].length; i++){
            double oldX = arrListPoints[0][i];
            double oldY = arrListPoints[1][i];
            rotatedPoints[0][i] = orgX +(int) ( oldX * getCos(angle) - oldY*getSin(angle));
            rotatedPoints[1][i] = orgY +(int) ( oldX * getSin(angle) + oldY*getCos(angle));
            // ^ formula derived from the 2d rotation matrix
        }
        return rotatedPoints;
    }

    public int getX() {
        return x;
    }
    //getters
    public int getY() {
        return y;
    }

}