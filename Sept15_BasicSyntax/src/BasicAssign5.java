import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;

public class BasicAssign5 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter potential product code:");
        String lineIn = stdin.nextLine();
        String lineInP1 = lineIn.substring(0, lineIn.indexOf(' '));
        int[] digs = new int[3];
        String lineInP2 = lineIn.substring(lineIn.indexOf(' ') + 1);
        int digCount = 0;
        for(int i = 0; i < lineInP1.length(); i++){
            System.out.println((int) (double) (i / 2));
//            digCount = (Character.isDigit(lineInP1.charAt(i)))? digCount + 1: digCount;
            char tempChar = lineInP1.charAt(i);
            if (Character.isDigit(tempChar)){
                digCount+=1;
                if(digCount > 6) {
                    System.out.println("Not a valid product code");
                    return;
                }
                if(digCount % 2 != 0) {
                    digs[(int) (double) (i / 2)] = tempChar-'0';
                }else{
                    digs[(int) (double) (i / 2)] = digs[(int) (double) (i / 2)]*10 + tempChar-'0';

                }
            }
        }
        if(digCount < 6) {
            System.out.println("Not a valid product code");
            return;
        }
        if(lineInP2.equals())
        System.out.println(Arrays.toString(digs));


    }

}
