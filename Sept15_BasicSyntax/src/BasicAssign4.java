import java.util.Arrays;
import java.util.Random;

public class BasicAssign4 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] birthdays = new int[366];
        birthdays[0] = -rand.nextInt(1, 365);
        //gets 1 birthday as a start so comparison between last birthday is possible
        int count = 0;
        int r = 0;
        // counter vars
            for (int i = 1; i < 365; i++) {
                birthdays[i] = (int) -Math.ceil(Math.random() * 365);
                // inverted random numbers to help with printing when sorted (so you just don't see zeros)
                // technically not necessary but useful for debugging
                Arrays.sort(birthdays);
                //sorted so same elements are next to one another
                if (r >= 10000)
                    break; // breaks after 10000 times
                for (int j = 1; j <= i; j++) {
                    // Checks if jth element and (j-1)th elememt are the same
                    if (birthdays[j] == birthdays[j - 1]) {
                        count += i;
                        r += 1;
                        i = 1;
                        Arrays.fill(birthdays, 0);
                        birthdays[0] = -rand.nextInt(1, 365);
                        break;
                    }
            }
        }
        System.out.println(((double)count)/(double)10000);
    }
}
