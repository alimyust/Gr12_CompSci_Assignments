import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRecordTester {
    public static void main(String[] args) {
        ArrayList<Double> s1_marks = new ArrayList<Double>(List.of(100.0,92.0,91.0,97.0));
        StudentRecord s1 = new StudentRecord(s1_marks, "Sadat");
        s1.addMark(97);
        System.out.println(s1.hasImproved());
        s1.addMark(92);
        System.out.println(s1.hasImproved());
        System.out.println(s1);
    }
}
