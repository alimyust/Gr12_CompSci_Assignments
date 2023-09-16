import java.util.Scanner;

public class BasicAssign5 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        System.out.println("Enter potential product code:");
        // Inputs
        String lineIn = stdin.nextLine();
        String lineInP1 = lineIn.substring(0, lineIn.indexOf(' '));
        String lineInP2 = lineIn.substring(lineIn.indexOf(' ') + 1);
        // ^ Breaks the input into 2 strings separated from the space
        // v If there are any lowercase letters, it terminates the program immediately (no need to check further)
        if(!lineInP1.equals(lineInP1.toUpperCase())) {
            System.out.println("Not a valid product code");
            return; // terminates code if not all caps
        }

        int[] digs = new int[6];
        int digCount = 0;

        for (int i = 0; i < lineInP1.length(); i++) {
            if (Character.isDigit(lineInP1.charAt(i))) {
                digCount++; // If there is a digit add to a counter
                if (digCount > 6) {
                    System.out.println("Not a valid product code");
                    return; // terminates code if there are more than 6 digits
                }
                //Adds each digit to an array for storage as an int from char
                digs[digCount - 1] = lineInP1.charAt(i) - '0';
                // digCount -1 to counteract the digCount ++ at the start
            }
        }
        if (digCount != 6) {
            System.out.println("!= 6");
            return; // terminates code if there aren't 6 digits (less than 6 digits since more is covered for)
        }
        int prod = (digs[0] * 10 + digs[1]) * (digs[2] * 10 + digs[3]) * (digs[4] * 10 + digs[5]);
        // hard coded product calculation (only ever 6 values)
        if ((prod != Integer.parseInt(lineInP2)))
            System.out.println("Not a valid product code");
        else // print output
            System.out.println("A valid product code");

    }
}
