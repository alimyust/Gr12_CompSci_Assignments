import java.util.ArrayList;
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
        return avg/this.marks.size();
    }
    public double median(){
//        int size = marks.size() - 1;
        if(((this.marks.size())% 2) == 0)
            return (this.marks.get(this.marks.size()/2 - 1) + this.marks.get(this.marks.size()/2))/2.0;
//        return (this.marks.get((int) Math.ceil(this.marks.size()/2.0)) - 1);
        return -1;
    }

    public double mode(){
        int modeIndex = 0;
        int[] tempArr = new int[this.marks.size()];
        for(int i = 0; i < marks.size() - 1; i++)
            if(Objects.equals(this.marks.get(i), this.marks.get(i + 1)))
                tempArr[marks.indexOf(marks.get(i))] ++;
        for(int i = 0 ; i < tempArr.length-1; i++)
            if(tempArr[i] > tempArr[i+ 1])
                modeIndex = i;
        return modeIndex;
    }
}

