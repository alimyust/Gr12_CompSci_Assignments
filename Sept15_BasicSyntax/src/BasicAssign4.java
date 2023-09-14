import java.util.Arrays;
import java.util.Random;

public class BasicAssign4 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] birthdays = new int[366];
        birthdays[0] = -rand.nextInt(1, 365);
        int count = 0;

        int r = 0;
            for (int i = 1; i < 365; i++) {
                birthdays[i] = (int) -Math.ceil(Math.random() * 365);
                Arrays.sort(birthdays);
                if (r >= 10000)
                    break;
//                System.out.println(r);
                for (int j = 1; j <= i; j++) {
                    if (birthdays[j] == birthdays[j - 1]) {
                        count += i -1;
                        r += 1;
                        i = 1;
                        if (j < 3) {
                            System.out.println(Arrays.toString(birthdays));
                        System.out.println(j);
                        }
                            Arrays.fill(birthdays, 0);
                        birthdays[0] = -rand.nextInt(1, 365);
                        break;
                    }
            }
        }
        System.out.println(((double)count)/(double)10000);
    }
}
