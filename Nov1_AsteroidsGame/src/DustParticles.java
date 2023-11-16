import java.awt.*;
//dust particles that shoot out when something is destroyed

public class DustParticles extends SpaceObject{
    private final int otherX;
    private final int otherY;
    private int timer;
    private final boolean type;
    public DustParticles(int x,int y, double angle, boolean type) {
        //inherits from spaceObj and has a random velocity
        super(x,y,2,angle);
        timer = 60;
        this.type = type;
        // V used for where the other part of the line would be drawn
        this.otherX = (int)((Math.random()>0.5)? (Math.random()*20+15):-(Math.random()*20+15));
        this.otherY = (int)((Math.random()>0.5)? (Math.random()*20+15):-(Math.random()*20+15));
    }
    public void drawDust(Graphics g){
        timer--;
        if (type) //if line
            g.drawLine(x, y, x + otherX, y + otherY);
        else
            g.drawOval(x, y, wid, wid);
    }

    public boolean getTime() {
        return (timer < 0);
    }//used to check if it should be deleted or not
}
