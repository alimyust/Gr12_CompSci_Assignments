import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRecordTester {
    public static void main(String[] args) {
        ArrayList<Double> s1Marks= new ArrayList<Double>(Arrays.asList(1.0,9.0,3.0));
        StudentRecord s1 = new StudentRecord(s1Marks, "s1");
        s1.addMark(2);
        s1.addMark(2);
        s1.addMark(9);

        System.out.println(s1.hasImproved());
        System.out.println(s1.average() + ", " + s1.median() + ", " + s1.mode());
        System.out.println(s1);
    }
}
