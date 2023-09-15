import java.util.Arrays;
import java.util.Scanner;

public class BasicAssign1 {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String lineIn = stdin.nextLine();
        final int size = lineIn.length();
        char[][] box = new char[size + 1][size + 1];
        //2d list with the correct size

        for (int r = 0; r < size + 1; r++) { // Rows
            for (int c = 0; c < size + 1; c++) { // Columns
                box[r][c] = (r == 0) ? lineIn.charAt(c % size) : // Gives the char at c but when c > size it still gives the index at c (used to get last char on first row)
                        (r == size) ? lineIn.charAt(Math.abs(c - size) % size) : // r would = size (bottom row) so abs(c-size) gets the inverse
                                (c == 0) ? lineIn.charAt(Math.abs(size - r) % size) : // same as rows but with columns
                                        (c == size) ? lineIn.charAt(r % size) : '~';
                // Ternary operator that checks if the row is zero , row is size, column is zero, column is size ( if edge of the square is being called)
                // Adds the appropriate element from lineIn depending on the case. Otherwise, just adds a tilda as
                // a space placeholder for formatting later.
            }
        }
        for (int i = 0; i < size + 1; i++) {
            String temp = Arrays.toString(box[i]).substring(1, size * 3 + 2).replaceFirst(" ", "").replaceAll(",", "").replaceAll("~", "");
            //Creates temporary string from each array in box. Removes all commas, square brackets, etc.
            if (i == 0 || i == size) // If it's the first or the last, removes all spaces, otherwise only remove the first space
                temp = temp.replaceAll(" ", "");
            System.out.println(temp);
        }
    }
}
