import com.sun.jdi.request.MethodEntryRequest;

import java.awt.*;
import java.util.ArrayList;

public class Meteoroid extends SpaceObject{
    private static final int[] WID = {150,75,25};

    private final int BIG = 0,MED = 1, SMALL = 2;
    private final int size;

    public Meteoroid(int x, int y, int size) {
        super(x,y,WID[size]);
        this.size = size;
//        System.out.println(angle);
    }

    public void drawMeteoroid(Graphics g) {
//        System.out.println(this.x + " , " + this.y);
        g.setColor(Color.white);
        g.drawRect(this.x, this.y, WID[size], WID[size]);

    }
}
