public class Main {
    public static int bestTime = 1000;
    public static String code = "";


    public static void main(String[] args) {
        String[] badWords = {"bad", "words"};
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[][] grid =
                {{0, 21, 20, 5, 25, 25, 35, 15},
                        {12, 26, 43, 29, 15, 26, 15, 12},
                        {7, 18, 23, 28, 36, 32, 12, 18},
                        {43, 34, 35, 18, 25, 18, 21, 25},
                        {32, 41, 23, 9, 21, 17, 24, 14},
                        {12, 9, 20, 42, 9, 19, 26, 22},
                        {30, 17, 17, 35, 14, 25, 14, 21},
                        {15, 21, 37, 24, 19, 15, 35, 15}};
        navigate(grid);

//        System.out.println(Arrays.toString(arrayAdd(arr, 5)));
//        System.out.println(Arrays.toString(fold(arr)));
//        System.out.println(licensePlate(badWords));
    }

    static double mean(int[] arrIn) {
        double avg = 0;
        for (int j : arrIn)
            avg += j;
        return avg / arrIn.length;
    }

    static void repeat(String lineIn, int count) {
        for (int i = 0; i < count; i++)
            System.out.print(lineIn);
    }

    static int[] arrayAdd(int[] arrIn, int toAdd) {
        int[] arrOut = new int[arrIn.length + 1];
        System.arraycopy(arrIn, 0, arrOut, 0, arrIn.length);
        arrOut[arrIn.length] = toAdd;
        return arrOut;
    }

    static int[] fold(int[] arrIn) {
        int[] arrOut = new int[arrIn.length / 2];
        for (int i = 0; i < arrIn.length / 2; i++) {
            arrOut[i] = arrIn[i] + arrIn[arrIn.length / 2 + i];
        }
        return arrOut;
    }

    static String licensePlate(String[] badWords) {
        String lineOut = "";
        for (int i = 0; i < 4; i++) {
            char tempC = (char) (Math.random() * 26 + 65);
            lineOut += tempC;
            for (String j : badWords) {
                if (lineOut.equals(j.toUpperCase())) {
                    i = 0;
                    lineOut = "";
                    break;
                }
            }
        }
        lineOut += ' ';
        lineOut += (int) (Math.random() * 999);
        return lineOut;
    }

    static void navigate(int[][] grid) {
        System.out.println(navigate(grid, 0, 0, grid[0][0], ""));

    }

    static int navigate(int[][] grid, int currX, int currY, int timeSum, String seq) {

//            System.out.println(timeSum);
//            System.out.println(seq);
//            if (timeSum < Main.bestTime) {
//                Main.bestTime = timeSum;
//                Main.code = seq;
//            }
        // Reaches the last path
        if (currX != 7 && currY != 7) {
            if (currX == 7) {
                navigate(grid, currX, currY + 1, timeSum + grid[currX][currY + 1], seq + 'D');
            } else if (currY == 7)
                navigate(grid, currX + 1, currY, timeSum + grid[currX + 1][currY], seq + 'R');
            else {
                navigate(grid, currX, currY + 1, timeSum + grid[currX][currY + 1], seq + 'D');
                navigate(grid, currX + 1, currY, timeSum + grid[currX + 1][currY], seq + 'R');
            }
        }
        if (seq.equals("RRRRRRRDDDDDDD"))
            System.out.println(Main.bestTime + "\n" + Main.code);
        return timeSum;

    }
}
//    static void navigate(int [][] grid, int currX, int currY, int timeSum, String seq){
//        if(currX == 7 && currY == 7 ){
//            if (timeSum < Main.bestTime) {
//                Main.bestTime = timeSum;
//                Main.code = seq;
//            }
//            return;
//        }
//
//        if(currX == 7 ) {
//            navigate(grid, currX, currY + 1, timeSum + grid[currX][currY + 1], seq + 'D');
//        }else if(currY == 7)
//            navigate(grid,currX+1,currY,timeSum+grid[currX+1][currY], seq + 'R');
//        else {
//            navigate(grid, currX, currY + 1, timeSum + grid[currX][currY + 1], seq + 'D');
//            navigate(grid,currX+1,currY,timeSum+grid[currX+1][currY], seq + 'R');
//        }
//
//    }
//}