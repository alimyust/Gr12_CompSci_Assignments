import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class StudentRecord {
    //Initializing
    private final ArrayList<Double> marks;
    private final String name;


    public StudentRecord(ArrayList<Double> marks, String name) {
        this.marks = marks;
        this.name = name;
        //Constructor
    }

    public double average() {
        double avg = 0;
        for (Double j : this.marks)
            avg += j; // Adds all values in this.marks
        return avg/(double)(this.marks.size()); // divides by its size (cast as double to avoid int division from
        //this.marks.size
    }
    public double median(){
        ArrayList<Double> marksSorted = new ArrayList<>(marks);
        Collections.sort(marksSorted);
        //Creates a separate sorted arraylist
        if(((marksSorted.size())% 2) == 0) // if length is even ((n/2)th + (n/2+1)th)/2
            return (marksSorted.get(marksSorted.size()/2-1) + marksSorted.get(marksSorted.size()/2))/2.0;
        return marksSorted.get(marksSorted.size()/2);
        // if length is odd (n/2)th
    }

    public ArrayList<Double> mode(){
        int[] freqArr = new int[this.marks.size()]; // frequency array
        ArrayList<Double> modes = new ArrayList<Double>();
        // return arraylist (arraylist since could have 0,1,2... modes)
        for (int i = 0; i < marks.size(); i++) //counts the entirety of marks
            for (int j = i ; j < marks.size(); j++) // counts from i to the end (removes cases)
                if (Objects.equals(this.marks.get(i), this.marks.get(j))) { // if there is a match
                    freqArr[this.marks.indexOf(this.marks.get(i))]++; //  adds 1 to the frequency array of the first
                    break; // index i is found at. (so if 3 1's are found, all would be added to the same index)
                    //Breaks to continue.
                }
//        System.out.println(Arrays.toString(freqArr));
        if(arrMax(freqArr) == arrMin(freqArr))
            return modes; // if the lowest and highest are the same (arr of same element) return empty arrList
        for (int i = 0; i < freqArr.length; i++)
            if (freqArr[i] == arrMax(freqArr) && freqArr[i] > 1)//(> 0 to prevent single freq numbers from appearing
                modes.add(this.marks.get(i)); // if any value in freqArr is equal to the max value(appears the most
        //then the value from marksSorted would be added)
        return modes;
    }
    private int arrMax(int[] arr){
        int temp = 0; // Helper func to get the max value of arr
        for(int j : arr)
            if( temp < j)
                temp = j;
        return temp;
    }
    private int arrMin(int[] arr){
        int temp = arrMax(arr); // Helper func to get the max value of arr
        for(int j : arr) // not equal to zero to avoid default zeroes of the arr
            if( temp > j && j != 0) // ^(if it repeats once the minimum would be 1)
                temp = j;
        return temp;
    }

    public void addMark(double mark){
        if(mark >= 0 && mark <= 100)
            this.marks.add(mark);
    }//if the value is in the range, it's added
    public boolean hasImproved (){//if the average is less then the last value return true (has improved)
        return (average() < this.marks.get(this.marks.size()-1));//otherwise returns false
    }//Assumes a correct input (not just 1 value in marks)
    @Override
    public String toString() {
        ArrayList<Double> marksSorted = new ArrayList<>(this.marks);
        Collections.sort(marksSorted);
        return  "\nName: " + name +
                "\nAvg: " + average() +
                "\nMedian: " + median() +
                "\nMode: " + mode() +
                "\nMarks: " + marks +
                "\nSortedMarks: " + marksSorted;
    } // returns info about students
}

