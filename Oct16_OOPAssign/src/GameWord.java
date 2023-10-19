import java.util.ArrayList;
import java.util.Arrays;

public class GameWord {
    private String contents = "";

    public GameWord(String contents) {
        this.contents = contents;
    }

    public String reverse() {
        String temp = "";
        for (int i = contents.length() - 1; i == 0; i--)
            temp += contents.charAt(i);
        return temp;
    }

    public boolean anagram(String otherWord) {
        return (sortString(otherWord).equals(sortString(this.contents)));
    }
    public boolean anagram(GameWord otherWord) {
        return (sortString(otherWord.contents).equals(sortString(this.contents)));
    }

    private String sortString(String in) {
        char[] ar = in.toLowerCase().toCharArray();
        Arrays.sort(ar);
        return String.valueOf(ar);
    }

    public int pointValue() {
        int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
        int total = 0;
        for (int i = 0; i < this.contents.length(); i++)
            total += values[(int) (this.contents.toLowerCase().charAt(i)) - 97];
        return total;
    }
    public ArrayList<String> permutations(String rem) {
        ArrayList<String> temp= new ArrayList<>();
        return permutations("", rem,temp);
    }


    public ArrayList<String> permutations(String soFar, String rem, ArrayList<String> perms) {
        if (rem.isEmpty())
            perms.add(soFar);
        for (int i = 0; i < rem.length(); i++) {
            String curr = rem.substring(i, i + 1);
            String before = rem.substring(0, i);
            String after = rem.substring(i + 1);
            permutations(soFar + curr, before + after, perms);
        }
        return perms;
    }

    @Override
    public String toString() {
        return  "contents='" + contents + '\'';
    }
}
