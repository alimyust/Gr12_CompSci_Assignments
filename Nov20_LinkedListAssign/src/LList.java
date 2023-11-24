import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class LList {
    private LNode head;


    public LList() {
        head = null;
    }

    public void add(int n) {
        LNode tmp = new LNode(n, head);
        head = tmp;
    }

    public void push(int n) {
        LNode tmp = new LNode(n, head);
        head = tmp;
    }
    public int pop() {
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
        if(head == null) {
            System.out.println("Empty List");
            return;
        }
        LNode cur = head;
        Set<Integer> hashSet = new HashSet<>();
        int setLen;
        int oldSetLen = 0;
        hashSet.add(cur.getVal());

        while (cur.getNext().getNext() != null) {
            setLen = hashSet.size();
            hashSet.add(cur.getNext().getNext().getVal());
            // Check if the size changed after adding the next element
            if (oldSetLen == setLen)
                cur.setNext(cur.getNext().getNext());
            oldSetLen = setLen;
            cur = cur.getNext();
        }
        hashSet.add(cur.getNext().getVal());
        if (hashSet.size() == oldSetLen)
            cur.setNext(null);
//        System.out.println(cur);
    }

    public void reverse() {
        if(head == null) {
            System.out.println("Empty List");
            return;
        }
        LNode cur = head;
        ArrayList<LNode> nodeStack = new ArrayList<>();
        while (cur != null) {
            nodeStack.add(cur);
            cur = cur.getNext();
        }
        int ns = nodeStack.size() - 1;
        nodeStack.get(ns).setNext(nodeStack.get(ns - 1));
        head = nodeStack.get(ns);
        for (int i = ns -1; i > 0; i--)
            nodeStack.get(i).setNext(nodeStack.get(i-1));
        nodeStack.get(0).setNext(null);
    }

    public LList cloneList(){
        LNode cur = head;
        LList outList = new LList();
        while(cur != null){
            LNode tmp = new LNode(cur.getVal());
            outList.push(tmp.getVal());
            cur =cur.getNext();
        }
        outList.reverse();
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