import java.util.Arrays;
import java.util.Random;

public class BasicAssign4 {
    public static void main(String[] args) {
        Random rand = new Random();
        int[] birthdays = new int[366];
        birthdays[0] = rand.nextInt(1, 365);
        int count = 0;
        for (int j = 0; j < 10000; j++) {
            for (int i = 0; i < 365; i++) {
                birthdays[i] = (int) Math.ceil(Math.random()*365);
                Arrays.sort(birthdays);
                System.out.println(Arrays.toString(birthdays));
                if (birthdays[365-i] == birthdays[365-i-1] && birthdays[365-i] != 0) {
                    count += i;
                    break;
                }
                Arrays.fill(birthdays, 0);
                birthdays[0] = rand.nextInt(1, 365);
            }
        }
        System.out.println(count / 10000);
    }
}
