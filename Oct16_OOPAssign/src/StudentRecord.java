import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class StudentRecord {
    private ArrayList<Double> marks = new ArrayList<Double>();
    private String name;

    public StudentRecord(ArrayList<Double> marks, String name) {
        this.marks = marks;
        Collections.sort(this.marks);
        this.name = name;
    }

    public double average() {
        double avg = 0;
        for (Double j : this.marks)
            avg += j;
        return avg/(double)(this.marks.size());
    }
    public double median(){
//        int size = marks.size() - 1;
        if(((this.marks.size())% 2) == 0)
            return (this.marks.get(this.marks.size()/2 - 1) + this.marks.get(this.marks.size()/2))/2;
        return (this.marks.get(this.marks.size()/2));
    }

    public ArrayList<Double> mode(){

        int[] tempArr = new int[this.marks.size()];
        ArrayList<Double> modes = new ArrayList<Double>();

        for (int i = 0; i < marks.size(); i++)
            for (int j = i + 1; j < marks.size(); j++)
                if (Objects.equals(this.marks.get(i), this.marks.get(j))) {
                    tempArr[marks.indexOf(marks.get(i))]++;
                    break;
                }
//        System.out.println(Arrays.toString(tempArr));
        for (int i = 0; i < tempArr.length; i++)
            if (tempArr[i] == arrMax(tempArr) && tempArr[i] > 0)
                modes.add(this.marks.get(i));
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
        this.marks.add(mark);
    }
    public boolean hasImproved (){
        return (average() < this.marks.get(marks.size()-1));
    }

    @Override
    public String toString() {
        return "StudentRecord: " +
                "marks=" + marks +
                ", name='" + name;
    }
}

