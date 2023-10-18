import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

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

    public ArrayList<String> permutations(String soFar, String rem, ArrayList<String> perms, int i) {
        if(rem.length() == 0) {
            perms.add(soFar);
            if (perms.size() == factorial(soFar.length()))
                return perms;
        }
        if(i < rem.length()){
            String curr = rem.substring(i, i + 1);
            String before = rem.substring(0, i);//letters before current
            String after = rem.substring(i + 1);//letters after current
            return permutations(soFar+curr,before+after, perms, i+1);
        }
        System.out.println(perms);
        return permutations(soFar,rem, perms, i+1);
    }
    private int factorial(int n) {
        if(n == 1) // base case
            return 1;
        return n*factorial((n-1));
    }
    static void anagrams(String soFar,String rem){
        //if no letters remain, we have a solution
        if (rem.length()==0){//base case
            System.out.println(soFar);
        }
        //Recurse ONCE for EACH remaining letter in "rem"
        //adding each to the current string
        //Pass all letters but the added one into the
        //recursive call as remaining letters
        for (int i=0;i<rem.length();i++){
            String curr=rem.substring(i,i+1);
            String before=rem.substring(0,i);//letters before current
            String after=rem.substring(i+1);//letters after current
            //System.out.println(curr+" "+before+" "+after);
            anagrams(soFar+curr,before+after);
        }
    }
}


// anagram
//    String small = smallerString(otherWord);
//    String large = (Objects.equals(small, this.contents))? otherWord: this.contents;
//    int count = 0;
//        for(int i =0; i <small.length(); i++) {
//        for (int j = 0; j < large.length(); j++) {
//            if (small.charAt(i) == large.charAt(j)) {
//                count++;
//                break;
//            }
//        }
//    }
//        return (count == small.length());

