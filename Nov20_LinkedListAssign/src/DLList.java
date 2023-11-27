
public class DLList {
    private static DNode head;

    private DNode tail;

    public DLList() {
        head = tail = null;
    }

    public void add(int n) {
        DNode tmp = new DNode(n, head, null);
        //creates a null pointing at head with nothing behind it
        if (head != null) // if not empty then the previous of head is
            head.setPrev(tmp);
        if (tail == null)
            tail = tmp; // if empty then tail is also head which is tmp
        head = tmp;
    }

    public void push(int n) {
        add(n);//same as add
    }

    public void enqueue(int n) {
        DNode tmp = new DNode(n, null, tail);
        // creates a new node that points at nothing forward and at the tail backward
        if (tail != null) // if there is a tail make the tail point at tmp
            tail.setNext(tmp);
        if (head == null)
            head = tmp;//if empty then the head = tail = tmp
        tail = tmp;
    }

    public int dequeue() {
        //same logic as pop. Records the head value and then sets the next head to the next next node, skipping
        //only and removing it from the DLL.
        return pop();
    }

    public int pop() {
        if (head == null) {
            System.out.println("Empty List");
            return -1;
        }
        int popValue = head.getVal();
        if (head.getNext() == null) {
            head = null;
        } else {
            head.setVal(head.getNext().getVal());
            head.setNext(head.getNext().getNext());
        }
        return popValue;
    }

    private boolean isFirst(DNode testNode) {
        return (testNode.getPrev() == null && testNode.getNext() != null);
    }
    //helper methods to check if a node is head / tail;
    private boolean isLast(DNode testNode) {
        return (testNode.getPrev() != null && testNode.getNext() == null);
    }

    public void delete(DNode tarNode) { //Target node
        if (tarNode == null) {
            System.out.println("Invalid Input");
            return;
        }
        if (isFirst(tarNode))
            head = tarNode;
        if (tarNode.getNext() != null)
            tarNode.getNext().setPrev(tarNode.getPrev());

        if (isLast(tarNode))
            tail = tarNode;
        if (tarNode.getPrev() != null)
            tarNode.getPrev().setNext(tarNode.getNext());

        tarNode.setNext(null);
        tarNode.setPrev(null);
    }

    public void delete(int tar) {
        DNode tmp = head;
        while (tmp != null) {
            tmp = tmp.getNext();
            if (tmp.getVal() == tar)
                break;
        }
        if (tmp == null)
            return;
        delete(tmp);
    }

    public void deleteAt(int n) {
        DNode tmp = head;
        for (int i = 0; i < n; i++)
            tmp = tmp.getNext();
        if (tmp == null)
            return;

        if (isFirst(tmp))
            head = tmp.getNext();
        else if (tmp.getNext() != null)
            tmp.getNext().setPrev(tmp.getPrev());

        if (isLast(tmp))
            tail = tmp.getPrev();
        else if (tmp.getPrev() != null)
            tmp.getPrev().setNext(tmp.getNext());

        tmp.setPrev(null);
        tmp.setNext(null);
    }

    public static DNode getHead() {
        return head;
    }

    public String toString() {
        DNode tmp = head;
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
