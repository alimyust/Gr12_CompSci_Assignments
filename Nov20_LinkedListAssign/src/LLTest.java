import java.util.LinkedList;

public class LLTest {
    public static void main(String[] args) {
        LList myList=new LList();
        myList.reverse();
        System.out.println(myList);
        for (int i = 0; i < 7; i++)
            myList.push(i);
//        myList.sortedInsert(new LNode(-3));
//        myList.add(1);
        System.out.println(myList);
//        myList.removeDuplicates();
//        System.out.println(myList);
//        myList.reverse();
        System.out.println(myList);
        System.out.println("End");

        LList cloneList = myList.cloneList();
        cloneList.push(2);
//        cloneList.reverse();
        System.out.println(cloneList);
//        System.out.println(myList + "\n");

//        DLList dList = new DLList();
//        for (int i = 1; i < 4; i++)
//            dList.push(i);
//        System.out.println(dList);
//        dList.delete(1);
//        System.out.println(dList);

    }
}