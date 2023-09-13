import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String lineIn = stdin.nextLine();
        final int size = lineIn.length();
        char[][] box = new char[size + 1][size + 1];

        for (int r = 0; r < size + 1; r++) { // Rows
            for (int c = 0; c < size + 1; c++) { // Columns
                box[r][c] = (r == 0) ? lineIn.charAt(c % size) :
                        (r == size) ? lineIn.charAt(Math.abs(c - size) % size) :
                                (c == 0) ? lineIn.charAt(Math.abs(size - r) % size) :
                                        (c == size) ? lineIn.charAt(r % size) : '~';
            }
        }
        for (int i = 0; i < size + 1; i++) {
            String temp = Arrays.toString(box[i]).substring(1, size * 3 + 2).replaceFirst(" ", "").replaceAll(",", "").replaceAll("~", "");
            if (i == 0 || i == size)
                temp = temp.replaceAll(" ", "");
            System.out.println(temp);
        }
    }
}


//                if (r == 0) {
//                    box[r][c] = lineIn.charAt(c % size);
//                } else if (r == size) {
//                    box[r][c] = lineIn.charAt(Math.abs(c - size) % size);
//                } else {
//                    if (c == 0) {
//                        box[r][c] = lineIn.charAt(Math.abs(size-r) % size);
//                    }
//                    else if(c == size){
//                        box[r][c] = lineIn.charAt(r % size);
//                    }
//                    else{
//                        box[r][c] = '~';
//                    }
//                }
//            }



//for (int i = 0; i < lineIn.length() + 1; i++) {
//            for (int j = 0; j < lineIn.length() + 1; j++) {
//                if (i == 0)
//                    lineOut += lineIn.charAt(j % lineIn.length());
//                else if (i == lineIn.length())
//                    lineOut += lineIn.charAt(Math.abs(j - lineIn.length()) % lineIn.length());
//                else {
//                    if (j == 0) {
//                        lineOut += lineIn.charAt(Math.abs(j - lineIn.length()) % lineIn.length());
//                    }else if (j == lineIn.length()){
//                        lineOut += lineIn.charAt(j % lineIn.length());
//                    }else{
//                        lineOut+= " ";
//                    }
//                }
//                System.out.println("");
//            }
//        }
//        System.out.println(lineOut);
