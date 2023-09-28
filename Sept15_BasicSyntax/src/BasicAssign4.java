import java.util.Arrays;
import java.util.Random;

public class BasicAssign4 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] birthdays = new int[366];
        birthdays[0] = -rand.nextInt(1, 365);
        //gets 1 birthday as a start so comparison between last birthday is possible
        int total = 0;
        int r = 0;
        // counter vars
            for (int i = 1; i < 366; i++) { // loops 365 times for the potential of
                //everyone have a unique birthday, so the 366th is the first guaranteed repeat
                // ( even thought it is practically statistically impossible but just in case.
                birthdays[i] = (int) -Math.ceil(Math.random() * 365);
                // inverted random numbers to help with printing when sorted (so you just don't see zeros)
                // technically not necessary but useful for debugging
                Arrays.sort(birthdays);
                //sorted so same elements are next to one another
                if (r >= 10000)
                    break; // breaks after 10000 times
                for (int j = 1; j <= i; j++) {
                    // Checks if jth element and (j-1)th element are the same
                    if (birthdays[j] == birthdays[j - 1]) {
                        total += i; // i is the amount of people before there was a repeat, and it adds it to the total
                        r += 1; // Counter for how many times the birthday experiment happens (up to 10,000)
                        i = 1; // Resets back to 1 and resets array
                        Arrays.fill(birthdays, 0);
                        birthdays[0] = -rand.nextInt(1, 365);
                        break;
                    }
            }
        }// Prints the average
        System.out.println(((double)total)/(double)10000);
    }
}
