import java.awt.geom.Line2D;
import java.util.Scanner;

public class BasicAssign3 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        // Get input
        System.out.println("Enter point: (x then y)");
        double px = stdin.nextDouble(), py = stdin.nextDouble();
        System.out.println("Enter start line and end line point:(x,y) (x,y)");
        double lx1 = stdin.nextDouble(), ly1 = stdin.nextDouble(), lx2 = stdin.nextDouble(), ly2 = stdin.nextDouble();
        // Prints output of ptLineDist() method. If 0(intersects) else 1/-1 (does not intersect)
        System.out.println(Line2D.ptLineDist(lx1, ly1, lx2, ly2, px, py)==0 ? "Intersects" : "Does not intersect");

    }
}
