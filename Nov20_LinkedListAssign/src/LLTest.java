import java.util.LinkedList;

public class LLTest {
    public static void main(String[] args) {
        LList myList=new LList();
        for (int i = 0; i < 1; i++)
            myList.push(i);
        System.out.println(myList);
        myList.reverse();
//        myList.removeDuplicates();
        myList.sortedInsert(new LNode(1));
        System.out.println(myList);

//        DLList dList = new DLList();
//        for (int i = 1; i < 2; i++)
//            dList.push(i);
//        System.out.println(dList);
//        dList.pop();
//        System.out.println(dList);
//        dList.enqueue(1);
//        System.out.println(dList);
////        dList.enqueue(2);
//        System.out.println(dList);
//        System.out.println("End");

    }
}