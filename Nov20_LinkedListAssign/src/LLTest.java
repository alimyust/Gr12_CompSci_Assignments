import java.util.LinkedList;

public class LLTest {
    public static void main(String[] args) {
        LList myList=new LList();
        for (int i = 1; i < 7; i++)
            myList.push(i);
        myList.sortedInsert(new LNode(-3));
        myList.add(-3);
        System.out.println(myList);
        myList.removeDuplicates();
        System.out.println(myList);

//        System.out.println(myList + "\n");

//        DLList dList = new DLList();
//        for (int i = 1; i < 4; i++)
//            dList.push(i);
//        System.out.println(dList);
//        dList.delete(1);
//        System.out.println(dList);

    }
}