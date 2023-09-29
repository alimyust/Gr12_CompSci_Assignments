import java.util.Scanner;

public class HuzzuTroll {
    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        String lineIn = stdin.nextLine();
        String [] words = lineIn.split(" ");
        for(String j: words)
          System.out.print(j.charAt(0));
    }
}
