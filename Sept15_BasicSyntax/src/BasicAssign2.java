import java.util.Scanner;

public class BasicAssign2 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String lineIn = stdin.nextLine().toUpperCase();
        String lineOut = "";
        int countB = 1, countW = 1;
        while (!lineIn.replaceAll("B", "").replaceAll("W", "").isEmpty()) {
            // Loops unless the line without B and W is empty ( only B and W )
            System.out.println("Invalid Input: Try Again");
            lineIn = stdin.nextLine().toUpperCase();
        }

        for (int i = 1; i < lineIn.length(); i++) {
            if (lineIn.charAt(i) == 'B') { // If char is b and last char is w (wb, wwb, wwwb ...)
                if (lineIn.charAt(i - 1) == 'W')
                    lineOut += countW + "W"; // add amount of previous w's and 'W' to output
                countW = 0; // Reset W counter and add 1 th B counter
                countB += 1;
            }
            if (lineIn.charAt(i) == 'W') {
                if (lineIn.charAt(i - 1) == 'B')
                    lineOut += countB + "B";
                countB = 0; // Same logic but for B instead
                countW += 1;
            }
            if (i == lineIn.length()-1) {
                if (countW > countB) { // Edge case for the last element
                    lineOut += countW + "W";
                } // if 1 chars count is greater than the other, then that was the last char
                if (countB > countW) {
                    lineOut += countB + "B";
                }
            }
        }
        if(lineOut.length() == 0){ // Edge case if only 1 character (loop doesn't run)
            if(lineIn.charAt(0) == 'W')
                lineOut += 1 + "W";
            if(lineIn.charAt(0) == 'B')
                lineOut += 1 + "B";
        }
        System.out.println(lineOut);
    }
}


/*
* import java.util.Scanner;

public class BasicAssign2 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String lineIn = stdin.nextLine().toUpperCase();
        String lineOut = "";
        int countB = 1, countW = 1;
        while (!lineIn.replaceAll("B", "").replaceAll("W", "").isEmpty()) {
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
*/
