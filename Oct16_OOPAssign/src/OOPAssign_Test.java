import java.util.ArrayList;
import java.util.Arrays;

public class OOPAssign_Test {
    public static void main(String[] args) {
        ArrayList<Double> s1_marks= new ArrayList<Double>(Arrays.asList(1.0,2.0,2.0));
        StudentRecord s1 = new StudentRecord(s1_marks, "s1");
        System.out.println(s1.average() + ", " + s1.median() + ", " + s1.mode());
    }
}
