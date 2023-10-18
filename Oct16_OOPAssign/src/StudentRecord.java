import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class StudentRecord {
    private ArrayList<Double> marksUnsorted = new ArrayList<Double>();
    private ArrayList<Double> marksSorted = new ArrayList<Double>();
    private final String name;


    public StudentRecord(ArrayList<Double> marks, String name) {
        this.marksUnsorted = marks;
        this.marksSorted = marks;
        Collections.sort(this.marksSorted);
        this.name = name;
    }

    public double average() {
        double avg = 0;
        for (Double j : this.marksSorted)
            avg += j;
        return avg/(double)(this.marksSorted.size());
    }
    public double median(){
//        int size = marks.size() - 1;
        if(((this.marksSorted.size())% 2) == 0)
            return (this.marksSorted.get(this.marksSorted.size()/2 - 1) + this.marksSorted.get(this.marksSorted.size()/2))/2;
        return (this.marksSorted.get(this.marksSorted.size()/2));
    }

    public ArrayList<Double> mode(){

        int[] tempArr = new int[this.marksSorted.size()];
        ArrayList<Double> modes = new ArrayList<Double>();

        for (int i = 0; i < marksSorted.size(); i++)
            for (int j = i + 1; j < marksSorted.size(); j++)
                if (Objects.equals(this.marksSorted.get(i), this.marksSorted.get(j))) {
                    tempArr[marksSorted.indexOf(marksSorted.get(i))]++;
                    break;
                }
//        System.out.println(Arrays.toString(tempArr));
        for (int i = 0; i < tempArr.length; i++)
            if (tempArr[i] == arrMax(tempArr) && tempArr[i] > 0)
                modes.add(this.marksSorted.get(i));
        return modes;
    }
    private int arrMax(int[] arr){
        int temp = 0;
        for(int j : arr)
            if( temp < j)
                temp = j;
        return temp;
    }

    public void addMark(double mark){
        this.marksUnsorted.add(mark);
        this.marksSorted.add(mark);
        Collections.sort(marksSorted);
    }
    public boolean hasImproved (){
        return (average() < this.marksUnsorted.get(this.marksUnsorted.size()-1));
    }

    @Override
    public String toString() {
        return "StudentRecord: " +
                "marks=" + marksUnsorted +
                ", name='" + name;
    }
}

