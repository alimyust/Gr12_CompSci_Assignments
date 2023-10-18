import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class GameWord {
private String contents = "";

    public GameWord(String contents) {
        this.contents = contents;
    }
    public String reverse(){
        String temp = "";
        for(int i = contents.length() - 1; i == 0; i--)
            temp+=contents.charAt(i);
        return temp;
    }

    public boolean anagram(String otherWord){
        String small = smallerString(otherWord);
        String large = (Objects.equals(small, this.contents))? otherWord: this.contents;
        int count = 0;
        for(int i =0; i <small.length(); i++) {
            for (int j = 0; j < large.length(); j++) {
                if (small.charAt(i) == large.charAt(j)) {
                    count++;
                    break;
                }
            }
        }
        return (count == small.length());
    }
    private String smallerString(String in){
        if ( in.length() <  this.contents.length())
            return in;
        return this.contents;
    }


}

