
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
            head = null; // if the next value is null (1 item) then make head null
        } else {
            head = head.getNext();
            head.setPrev(null);
        } // turns head into the element it points at
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
        //putting tarnode in the correct place and then removing all of its pointers
        //to delete it. Making nothing point at it.
        if (isFirst(tarNode)) {
            head = tarNode.getNext(); // deletes tmp since it would be the old head
            head.setPrev(null);
        } else if (tarNode.getNext() != null) {
            tarNode.getNext().setPrev(tarNode.getPrev());
        }
        //goes around tmp so nothing points at it
        if (isLast(tarNode)) {
            tail = tarNode.getPrev();
            tail.setNext(null);
        } else if (tarNode.getPrev() != null) {
            tarNode.getPrev().setNext(tarNode.getNext());
        }
        tarNode.setPrev(null);
        tarNode.setNext(null);
        //at the end removes all pointers from tarNode so it's deleted

    }

    public void delete(int tar) {
        DNode tmp = head;
        while (tmp != null) {
            tmp = tmp.getNext();
            if (tmp.getVal() == tar)
                break;
        }
        //searches the list to find the first instance of tar and the corresponding node
        //deletes that node.
        if (tmp == null)
            return; //if not found, then nothing happens
        delete(tmp);
    }

    public void deleteAt(int n) {
        DNode tmp = head;
        for (int i = 0; i < n; i++) {
            if (tmp == null) {
                System.out.println("  Index out of bounds");
                return;
            }
            tmp = tmp.getNext();
        }
        //gets the node at the n value and deletes it
        delete(tmp);
    }

    public void removeDuplicates() {
        DNode tO = head;
        DNode tI;

        while (tO != null) {
            tI = tO;
            while (tI != null) {
                if (tI.getVal() == tO.getVal()) {
                    if (tI.getNext() == null) {
                        delete(tI);
                        tO = head;
                        break;
                    }
                    tI = tI.getNext();
                    delete(tI.getPrev());
                } else {
                    tI = tI.getNext();
                }
            }
            tO = tO.getNext();
        }
    }

    public void reverse(){
        DNode curr = head;
        DNode nxt;
        DNode prv;
        while(curr != null){
            nxt = curr.getNext();
            prv = curr.getPrev();
            curr.setNext(prv);
            curr.setPrev(nxt);
            if(nxt == null)
                head = curr;
            curr = nxt;
        }

    }

    public static DNode getHead() {
        return head; //used to test del method
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
