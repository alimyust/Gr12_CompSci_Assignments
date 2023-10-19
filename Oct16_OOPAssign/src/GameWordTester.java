import java.util.ArrayList;
import java.util.List;

public class GameWordTester {
    public static void main(String[] args) {
        GameWord w1 = new GameWord("abc");
        GameWord w2 = new GameWord("CaB");
        System.out.println(w1.anagram("bacs"));
        System.out.println(w1.pointValue());
        System.out.println(w1.permutations("12345"));
        ArrayList<Double> s1_marks = new ArrayList<Double>(List.of(1.0,3.0,2.0));
        StudentRecord s1 = new StudentRecord(s1_marks, "s1");
    }
}
