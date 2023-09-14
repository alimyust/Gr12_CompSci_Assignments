import java.util.Scanner;

public class BasicAssign2 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String lineIn = stdin.nextLine().toUpperCase();
        String lineOut = "";
        int countB = 1, countW = 1;
        while (lineIn.replaceAll("B", "").replaceAll("W", "").length() != 0) {
            System.out.println("Invalid Input: Try Again");
            lineIn = stdin.nextLine().toUpperCase();
        }
        for (int i = 1; i < lineIn.length(); i++) {
            if (lineIn.charAt(i) == 'B') {
                if (lineIn.charAt(i - 1) == 'W')
                    lineOut += countW + "W";
                countW = 0;
                countB += 1;
            }
            if (lineIn.charAt(i) == 'W') {
                if (lineIn.charAt(i - 1) == 'B')
                    lineOut += countB + "B";
                countB = 0;
                countW += 1;
            }
            if (i == lineIn.length()-1) {
                if (countW > countB) {
                    lineOut += countW + "W";
                }
                if (countB > countW) {
                    lineOut += countB + "B";
                }
            }
        }
        if(lineOut.length() == 0){
            if(lineIn.charAt(0) == 'W')
                lineOut += 1 + "W";
            if(lineIn.charAt(0) == 'B')
                lineOut += 1 + "B";
        }
        System.out.println(lineOut);
    }
}
