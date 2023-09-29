import java.util.Arrays;
import java.util.Random;

public class Main {
    public static int bestTime = 1000;
    public static String code = "";
    //Global variables for Q6


    public static void main(String[] args) {
        //Values for methods
        String[] badWords = {"abcd", "abfd", "sbcd", "ahcd", "aycd", "ayed", "fdag", "angf", "ascd", "avcp", "AAAA"};
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


        //Q1
        System.out.println(mean(arr));
        //Q2
        repeat("<>", 5);
        System.out.println();
        //Q3
        System.out.println(Arrays.toString(arrayAdd(arr, 5)));
        //Q4
        System.out.println(Arrays.toString(fold(arr)));
        //Q5
        System.out.println(licensePlate(badWords));
        //Q6
        navigate(grid);
    }

    static double mean(int[] arrIn) {
        double avg = 0;
        for (int j : arrIn)
            avg += j; // adds all values to avg and divides by the ammount
        return avg / arrIn.length;
    }

    static void repeat(String lineIn, int count) {
        for (int i = 0; i < count; i++)
            System.out.print(lineIn); // Prints lineIn count times
    }

    static int[] arrayAdd(int[] arrIn, int toAdd) {
        int[] arrOut = new int[arrIn.length + 1]; // Arr with length + 1
        System.arraycopy(arrIn, 0, arrOut, 0, arrIn.length);
        //Array copy method to get arrOut as new Arr
        arrOut[arrIn.length] = toAdd;
        // Adds the new element
        return arrOut;
    }

    static int[] fold(int[] arrIn) {
        int[] arrOut = new int[arrIn.length / 2];
        // arrOut with length of half of arrIn (integer division)
        for (int i = 0; i < arrIn.length / 2; i++)  // for all i values of arrOut (arrIn/2)
            arrOut[i] = arrIn[i] + arrIn[arrIn.length / 2 + i]; // adds the ith element and teh len / 2 + ith element
        // This basically splits the array in 2 and adds both pieces together
        return arrOut;
    }
    static String licenseHelper(){
        String lineOut = "";
        for(int i = 0; i < 4; i++){
            char tempC = (char) (Math.random() * 26 + 65);
            lineOut += tempC;//Generates a random letter and adds it to the temp String
        }
        if((int)(Math.random()*4) %2 == 0) {
            lineOut = "AAAA"; // Bad Word simulator (AAAA every other try, but it never actually prints AAAA)
        }
        //Returns a string
        return  lineOut;
    }
    static String licensePlate(String[] badWords) {
        String lineOut;
        int count; // initialize
        do{
            count = 0; // Count acts as bool
            lineOut = licenseHelper(); //Gets a random value
            for (String j : badWords) { // For all bad words
                if (lineOut.equals(j.toUpperCase()))
                    count += 1; // if there is a bad word add 1
            }// loop continues since 1 > 0 if it's wrong, otherwise it goes through
        }while(count > 0);

        lineOut += " " + (int) (Math.random() * 999); // A random number between 000 and 999
        return lineOut;
    }

    static void navigate(int[][] grid) { // Overloaded method to make the call in Main simpler
        navigate(grid, 0, 0, grid[0][0], "");
//        System.out.println(dpNav(grid, 7, 7));
    }

    static void navigate(int[][] grid, int currX, int currY, int timeSum, String seq) {
        if (currX == 7 && currY == 7) { // Base case of index reaching the last value in the grid
            // Reaches the last path
            if (timeSum < Main.bestTime) {
                // if the calculated time at the bottom of the branch is less than then past best time
                Main.bestTime = timeSum;
                Main.code = seq;
                //Copies the time and seq
            }
            if (seq.equals("RRRRRRRDDDDDDD")) // The last sequence that will be checked (to make the method print
                //inside itself and not return something. It will always be the last sequence)
                System.out.println(Main.bestTime + "\n" + Main.code);
            return;
            // Stops the recursion
        }
        if (currY < 7) // if currY is less than 7, add to currY (if it's greater it can't go there anymore
            navigate(grid, currX, currY + 1, timeSum + grid[currX][currY + 1], seq + 'D');
        if (currX < 7)// Sane for currX
            navigate(grid, currX + 1, currY, timeSum + grid[currX + 1][currY], seq + 'R');
            //Recurse with an updated current location, timeSum and Seq
    }
}

//            if (currX == 7) {
//        navigate(grid, currX, currY + 1, timeSum + grid[currX][currY + 1], seq + 'D');
//    } else if (currY == 7)
//    navigate(grid, currX + 1, currY, timeSum + grid[currX + 1][currY], seq + 'R');
//        else {
//        navigate(grid, currX, currY + 1, timeSum + grid[currX][currY + 1], seq + 'D');
//        navigate(grid, currX + 1, currY, timeSum + grid[currX + 1][currY], seq + 'R');
//    }
/*
    static int [][]dpNav(int[][] grid, int[][][] sumGrid, int currX, int currY, int seq) {
        System.out.println(currX + "," + currY);
        if(currX ==0 && currY == 0)
            sumGrid[currX][currY] = new int[]{grid[currX][currY], seq};
        else if (currX <= 0)
            sumGrid[currX][currY] = new int[]{grid[0][currY] + dpNav(grid, sumGrid, 0, currY - 1, seq)};
        else if (currY <= 0)
            sumGrid[currX][currY] = new int[]{grid[currX][0] + dpNav(grid, sumGrid, currX - 1, 0, seq)};
        else
            sumGrid[currX][currY] = new int[]{grid[currX][currY] + Math.min(dpNav(grid, sumGrid, currX - 1, currY, seq), dpNav(grid, sumGrid, currX, currY - 1, seq))};
        return sumGrid[currX][currY];
    }
//        sumGrid[currY][currX] = grid[currY][currX] + Math.min(sumGrid[currY][currX-1], sumGrid[currY-1][currX]);


    static int dpNav(int[][] grid, int currX, int currY) {
//        System.out.println(currX + "," + currY);
        if (currX == 0 && currY == 0)
            return grid[0][0];
        if (currX <= 0)
            return grid[0][currY] + dpNav(grid, 0, currY - 1);
        if (currY <= 0)
            return grid[currX][0] + dpNav(grid, currX - 1, 0);
        else
            return grid[currX][currY] + Math.min(dpNav(grid, currX - 1, currY), dpNav(grid, currX, currY - 1));
    }
*/