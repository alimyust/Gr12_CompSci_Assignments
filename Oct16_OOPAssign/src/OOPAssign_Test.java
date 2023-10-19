import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OOPAssign_Test {
    public static void main(String[] args) {
        ArrayList<Double> s1_marks= new ArrayList<Double>(List.of(1.0));
        StudentRecord s1 = new StudentRecord(s1_marks, "s1");
        s1.addMark(2);
        s1.addMark(3);
////        System.out.println(s1.hasImproved());
        System.out.println(s1.average() + ", " + s1.median() + ", " + s1.mode());


        GameWord w1 = new GameWord("abc");
        System.out.println(w1.anagram("pots"));
        System.out.println(w1.pointValue());
        ArrayList<String> temp= new ArrayList<>();
        System.out.println(w1.permutations("", "abcde", temp));
    }
}
