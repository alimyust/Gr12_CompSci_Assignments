import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LList {
    private LNode head;


    public LList() {
        head = null;
    }

    public void add(int n) {
        head = new LNode(n, head);
    }

    public void push(int n) {
        head = new LNode(n, head);
        //creates a new node with the value that becomes the new head and points at the old head
    }
    public int pop() {
        if(head == null) {
            System.out.println("Empty List");
            return -1;
        }
        int popValue = head.getVal();
        head.setVal(head.getNext().getVal());
        head.setNext(head.getNext().getNext());
        return popValue;
    }
    public void sortedInsert(LNode newNode) {
        LNode tmp = head;
        while (tmp.getVal() > newNode.getVal() && tmp.getNext() != null) {
            if (tmp.getNext().getVal() <= newNode.getVal())
                break; //if the next value of curr is <= the new node or the list ends, break
            tmp = tmp.getNext(); // cycle through all values in LList
        }
        //tmp is the smallest value greater than newNode (4,3(tmp), 2(to be inserted as new node), 2....)
        newNode.setNext(tmp.getNext());
        //makes NN point at what's after tmp and makes tmp point at NN
        tmp.setNext(newNode);
        if (tmp == head) // if tmp is head (while doesn't run so greater than it all) make NN head
            head = newNode;
    }

    public void removeDuplicates() {
        if(head == null || head.getNext() == null){
            System.out.println("Not enough elements for any to occur more then once");
            return; // returns if there are 0 or 1 elements in the list
        }
        LNode cur = head;
        Set<Integer> hashSet = new HashSet<>();
        //Sets store unique elements so if you append all the elements, it won't include and duplicates
        int setLen; // variables to keep track of the sets length ( amount of distinct elements)
        int oldSetLen = 0;
        hashSet.add(cur.getVal());
        while (cur.getNext().getNext() != null) {
            setLen = hashSet.size();
            hashSet.add(cur.getNext().getNext().getVal());
            // Check if the size changed after adding the next element
            if (oldSetLen == setLen) // if it didn't the added element was a repeat, so it didn't go into the set
                cur.setNext(cur.getNext().getNext()); // sets the cur value to skip over the (attempted) added value
            oldSetLen = setLen; // resets and the keeps cycling through the list
            cur = cur.getNext();
        }
        hashSet.add(cur.getNext().getVal());
        //case to cover the last element
        if (hashSet.size() == oldSetLen)
            cur.setNext(null);
    }

    public void reverse() {
        if(head == null || head.getNext() == null) {
            System.out.println("Empty List");
            return; // returns if there are 0 or 1 elements in the list
        }
        LNode cur = head;
        ArrayList<LNode> nodeStack = new ArrayList<>();
        //list to hold all the nodes
        while (cur != null) {
            nodeStack.add(cur);
            cur = cur.getNext();
        } // adds all nodes to the list
        int ns = nodeStack.size() - 1;
        nodeStack.get(ns).setNext(nodeStack.get(ns - 1)); // sets next to the element before the tail
        head = nodeStack.get(ns); // sets the new head with the old tail
        for (int i = ns -1; i > 0; i--)//repeats this process for every element
            nodeStack.get(i).setNext(nodeStack.get(i-1));
        nodeStack.get(0).setNext(null); // sets the first element to null (old head becomes new tail)
    }

    public LList cloneList(){
        LNode cur = head;
        LList outList = new LList();
        while(cur != null){
            LNode tmp = new LNode(cur.getVal());
            //creates a new node with a unique address and the same value
            outList.push(tmp.getVal());
            //pushes it to the new clone LList
            cur =cur.getNext();
        }
        outList.reverse();
        //since all values are pushed in the list needs to be reversed to be in the right order
        return outList;
    }
    public String toString() {
        LNode tmp = head;
        String ans = "";
        while (tmp != null) {
            ans += tmp + ", ";
            tmp = tmp.getNext();
        }
        if (head != null) {//the list is not empty
            ans = ans.substring(0, ans.length() - 2);//remove the ", "
        }
        return "[" + ans + "]";
    }
}