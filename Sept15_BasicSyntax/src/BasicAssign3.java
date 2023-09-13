import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Scanner;

public class BasicAssign3 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        double tempX = stdin.nextDouble(), tempY = stdin.nextDouble();
        Point2D interPoint = new Point2D.Double(tempX, tempY);
        tempX = stdin.nextDouble();
        tempY = stdin.nextDouble();
        Point2D endPoint = new Point2D.Double(tempX, tempY);
        tempX = stdin.nextDouble();
        tempY = stdin.nextDouble();
        Point2D startPoint = new Point2D.Double(tempX, tempY);

//        System.out.println(Line2D.linesIntersect())

    }
}
