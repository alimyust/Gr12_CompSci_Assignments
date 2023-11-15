
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Meteoroid extends SpaceObject{
    private static final int[] WID = {25,55,90};
    private final int size;
    private double rotCount;
    private final double rotSpeed;
    private final double[][][] PTS;
    private final double[][][] POT_PTS = {//Potential points (different meteor shapes)
            {{-5, -45, -31, -28, 7, 11, 33, 42, 29, 15, 10}, {-47, 2, 24, 39, 46, 25, 30, -7, -34, -8, -42}},
            {{-24, 30, 41, 14, 18, -14, -32, -45, -19}, {-31, -30, -1, 34, 8, 15, 23, -8, -10}},
            {{-4, 37, 34, -2, 27, -12, -41, -44, -21, -27}, {-46, -14, 17, 18, 37, 40, 24, -7, -8, -36}},
            {{-22, -35, -36, -25, -25, -12, -10, 0, 1, 13, 12, -6, -8, 9, 13, 10, 2, -19, -21, -24}, {-19, -19, 10, 10, 32, 33, 17, 18, 28, 28, -22, -20, -34, -36, -23, -36, -44, -43, -20, 11}}
    };
    private final Music[] bangSounds ={new Music(),new Music(),new Music()};

    public Meteoroid(int x, int y, int size, double angle) {
        super(x,y,WID[size], angle);
        this.size = size;
        this.rotSpeed = Math.random()+5;
        this.rotCount = 1;
        this.bangSounds[2].setFile("sound/bangLarge.wav");
        this.bangSounds[1].setFile("sound/bangMedium.wav");
        this.bangSounds[0].setFile("sound/bangSmall.wav");
        double[][] BIG_PTS = POT_PTS[(int) (Math.random()* POT_PTS.length)];
        double[][] MED_PTS = new double[2][BIG_PTS[0].length];
        double[][] SML_PTS = new double[2][BIG_PTS[0].length];
        for(int i=0 ; i <BIG_PTS[0].length; i++){
            MED_PTS[0][i] =BIG_PTS[0][i]/2;
            MED_PTS[1][i] =BIG_PTS[1][i]/2;
            SML_PTS[0][i] =BIG_PTS[0][i]/4;
            SML_PTS[1][i] =BIG_PTS[1][i]/4;
        }
        this.PTS = new double[][][]{SML_PTS, MED_PTS, BIG_PTS};
    }

//        System.out.println(angle);

    public void drawMeteoroid(Graphics g) {
        rotCount = (rotCount%360 ==0)?1:rotCount+rotSpeed;
        g.setColor(Color.white);
        int[][] rotatedPts = rotatePoints(PTS[size],rotCount,this.getX(),this.getY());
        g.drawPolygon(rotatedPts[0],rotatedPts[1], rotatedPts[0].length);

    }
    public Rectangle getRect(){
        return new Rectangle(this.getX()-wid/2,this.getY()-wid/2,wid,wid);
    }

    public int getSize() {
        return size;
    }

    public Music getMusic(){return bangSounds[size];}
}
