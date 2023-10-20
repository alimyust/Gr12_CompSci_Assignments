import java.util.ArrayList;
import java.util.List;

public class GameWordTester {
    public static void main(String[] args) {
        GameWord w1 = new GameWord("banana");
        GameWord w2 = new GameWord("nBAAna");
        System.out.println(w1.reverse());
        System.out.println(w1.anagram("badsacs"));
        System.out.println(w1.anagram(w2));

        System.out.println(w1.pointValue());
        System.out.println(w1.permutations());

    }
}
