import java.util.ArrayList;
import java.util.Arrays;

public class GameWord {
    private final String contents;

    public GameWord(String contents) {
        this.contents = contents;
    }
    //Constructor
    public String reverse() {
        String temp = ""; // length - > 0
        for (int i = contents.length()- 1; i >= 0; i--)
            temp += contents.charAt(i); // adds every char backwards from contents
        return temp;
    }

    public boolean anagram(String otherWord) { // takes string as an argument
        return (sortString(otherWord).equals(sortString(this.contents)));
        //returns true if both are equal after they're sorted
    }
    public boolean anagram(GameWord otherWord) {// takes gameword as an argument
        return (sortString(otherWord.contents).equals(sortString(this.contents)));
        // uses . content to access the string value
    }

    private String sortString(String in) { // sorts a string and makes it all lowercase
        char[] arr = in.toLowerCase().toCharArray();
        Arrays.sort(arr);
        return String.valueOf(arr);
    }

    public int pointValue() { // index is on the order of the alphabet (a - 0, b - 1, ...  z - 25)
        int[] values = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
        int total = 0;
        for (int i = 0; i < this.contents.length(); i++) // gets the int value of each letter and subtracts
            total += values[ (this.contents.toLowerCase().charAt(i)) - 97]; // 97 (ASCII for a) to get the index value
        // sums all the values present in contents
        return total;
    }
    public ArrayList<String> permutations() {
        ArrayList<String> temp= new ArrayList<String>();
        return permutations("", this.contents,temp);
    }//helper function to get the right input


    private ArrayList<String> permutations(String soFar, String rem, ArrayList<String> perms) {
        if (rem.isEmpty()) // if remainder is empty soFar is a valid anagram
            perms.add(soFar); // adds it to the arrList
        for (int i = 0; i < rem.length(); i++) { // loop that parses through the string letter by letter
            String curr = rem.substring(i, i + 1);
            String before = rem.substring(0, i);
            String after = rem.substring(i + 1);
            permutations(soFar + curr, before + after, perms);
        } // branches until every permutation for how the letters can be arranged reach the base case
        return perms;
        //returns perms with 1 more permutation until every one is reached and the method ends
    }

    @Override // prints contents
    public String toString() {
        return  "\nContents: " + contents + '\'';
    }
}
