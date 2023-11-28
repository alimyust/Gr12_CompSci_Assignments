import java.util.LinkedList;

public class LLTest {
    public static void main(String[] args) {
        LList myList=new LList();
        for (int i = 1; i < 6; i++)
            myList.push(i);
        System.out.println("Base List\t\t\t:" +myList);
        myList.sortedInsert(new LNode(3));
        System.out.println("Sorted Insert\t\t:" + myList);
        myList.removeDuplicates();
        System.out.println("Removed Duplicates  :" + myList);
        myList.reverse();
        System.out.println("Reversed List   \t:" + myList);
        LList clonedLL = myList.cloneList();
        System.out.println("Cloned List \t\t:" + clonedLL);
        int popVal =myList.pop();
        System.out.println("Popped List\t\t\t:" + myList + "  Popped: "+popVal);
        myList.push(55);
        System.out.println("Pushed List\t\t\t:" + myList + "\n");

        DLList dList = new DLList();
        for (int i = 1; i < 5; i++)
            dList.add(i);
        System.out.println("Base List\t\t\t:"+ dList);
        int dPop = dList.pop();
        System.out.println("Popped List\t\t\t:"+ dList+ "  D_Popped: "+dPop);
        dList.delete(DLList.getHead().getNext());
        System.out.println("Deleted head+1\t\t:"+dList);
        dList.deleteAt(1);
        System.out.println("Deleted Index 1\t\t:"+dList);
        int dqVal = dList.dequeue();
        System.out.println("Dequeued List \t\t:"+ dList + "  DQ_Val: " + dqVal);
        System.out.println("End");
    }
}
