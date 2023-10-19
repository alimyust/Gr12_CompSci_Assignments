import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class StudentRecord {
    //Initializing
    private ArrayList<Double> marks = new ArrayList<Double>();
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
        Collections.sort(marksSorted); // A seoerate
        if(((marksSorted.size())% 2) == 0)
            return (double)((marksSorted.get(marksSorted.size()/2-1) + marksSorted.get(marksSorted.size()/2))/2.0);
        return marksSorted.get(marksSorted.size()/2 +1);
    }

    public ArrayList<Double> mode(){
        ArrayList<Double> marksSorted = new ArrayList<>(marks);
        Collections.sort(marksSorted);
        int[] tempArr = new int[marksSorted.size()];
        ArrayList<Double> modes = new ArrayList<Double>();

        for (int i = 0; i < marks.size(); i++)
            for (int j = i + 1; j < marks.size(); j++)
                if (Objects.equals(marksSorted.get(i), marksSorted.get(j))) {
                    tempArr[marks.indexOf(marks.get(i))]++;
                    break;
                }
//        System.out.println(Arrays.toString(tempArr));

        if(arrMax(tempArr) == arrMin(tempArr))
            return modes;
        for (int i = 0; i < tempArr.length; i++)
            if (tempArr[i] == arrMax(tempArr) && tempArr[i] > 0)
                modes.add(marksSorted.get(i));
        return modes;
    }
    private int arrMax(int[] arr){
        int temp = 0;
        for(int j : arr)
            if( temp < j)
                temp = j;
        return temp;
    }
    private int arrMin(int[] arr){
        int temp = arrMax(arr);
        for(int j : arr)
            if( temp > j)
                temp = j;
        return temp;
    }

    public void addMark(double mark){
        if(mark >= 0 && mark <= 100)
            this.marks.add(mark);
    }
    public boolean hasImproved (){
        return (average() < this.marks.get(this.marks.size()-1));
    }
    @Override
    public String toString() {
        return "StudentRecord: " +
                "marks=" + marks +
                ", name='" + name;
    }
}

