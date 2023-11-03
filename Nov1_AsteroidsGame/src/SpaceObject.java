import com.sun.source.tree.DirectiveTree;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SpaceObject {
        protected int x;
        protected int y;
        private int vx;
        private int vy;
        private int angle;
        protected int wid;

    public SpaceObject() {
    }
    public SpaceObject(int x, int y,double angle) {
        this(x,y,3);
        this.angle = (int)(angle);
        this.vx = 2;
        this.vy = 2;
    }

    public SpaceObject(int x, int y, int wid) {
            this.x = x;
            this.y = y;
            this.wid = wid;
            this.angle = (int)((Math.random() *70+10) *(int)(Math.random()*3+1));
            this.vx = (int) (Math.random()*3+1);
            this.vy = (int) (Math.random() * 3 + 1);
        }
        public void moveSpaceObject(){
            this.x+= this.vx*Player.getCos(angle);
            this.y+= this.vy*Player.getSin(angle);
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

        public Rectangle spaceObjRect(){
            return new Rectangle();
        }
    }
