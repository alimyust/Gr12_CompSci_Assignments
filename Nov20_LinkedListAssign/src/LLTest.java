import java.util.LinkedList;

public class LLTest {
    public static void main(String[] args) {
        LList myList=new LList();
//        System.out.println(myList);
//        myList.push(92);
//        System.out.println(myList);
//        myList.push(5);
//        System.out.println(myList);
//        myList.push(47);
//        System.out.println(myList + "\n");

        DLList dList = new DLList();
        for (int i = 1; i < 4; i++)
            dList.push(i);
//        System.out.println(dList);
        dList.delete(0);
        dList.delete(0);

        dList.delete(0);

        System.out.println(dList);

    }
}