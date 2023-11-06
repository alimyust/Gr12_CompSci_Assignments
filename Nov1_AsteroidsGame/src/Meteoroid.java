import com.sun.jdi.request.MethodEntryRequest;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Meteoroid extends SpaceObject{
    private static final int[] WID = {25,55,110};
    private final int BIG = 2,MED = 1, SMALL = 0;
    private final int size;
    private double rotCount;
    private double rotSpeed;
//    private final double[][] BIG_PTS = {{40, 103, 173, 137, 159, 100, 74, 86, 47},
//            {35, -12, 22, 66, 108, 84, 95, 46, 57}};
    private final double[][] BIG_PTS = {{-24, 30, 41, 14, 18, -14, -32, -45, -19}, {-31, -30, -1, 34, 8, 15, 23, -8, -10}};
//    private final double[][] BIG_PTS = {{-50, -50, 50, 50},{-50, 50, -50, 50}};
    private final double[][] SML_PTS = new double[2][BIG_PTS[0].length];

    private final double[][] MED_PTS = new double[2][BIG_PTS[0].length];
    private final double[][][] PTS;


    public Meteoroid(int x, int y, int size, double angle) {
        super(x,y,WID[size], angle);
        this.size = size;
        this.rotSpeed = Math.random()+5;
        this.rotCount = 1;
        for(int i=0 ; i <BIG_PTS[0].length; i++){
            this.MED_PTS[0][i] =BIG_PTS[0][i]/2;
            this.MED_PTS[1][i] =BIG_PTS[1][i]/2;
            this.SML_PTS[0][i] =BIG_PTS[0][i]/4;
            this.SML_PTS[1][i] =BIG_PTS[1][i]/4;
        }
        this.PTS = new double[][][]{SML_PTS, MED_PTS, BIG_PTS};
    }

//        System.out.println(angle);

    public void drawMeteoroid(Graphics g) {
        rotCount = (rotCount%360 ==0)?1:rotCount+rotSpeed;
        g.setColor(Color.white);
//System.out.println(rotCount);
        int[][] rotatedPts = rotatePoints(PTS[size],rotCount,
                this.getX(),this.getY());
        g.drawPolygon(rotatedPts[0],rotatedPts[1], BIG_PTS[0].length);
//        g.drawOval(this.getX()-WID[size]/2,this.getY()-WID[size]/2, WID[size], WID[size]);
//        g.drawOval(this.getX()-2,this.getY()-2, 4,4);

//        g.drawRect(this.x,this.y,WID[size], WID[size]);

    }

    public int getSize() {
        return size;
    }

}
