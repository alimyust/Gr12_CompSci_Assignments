public class DLList {
    private DNode head;
    private DNode tail;


    public DLList() {
        head = null;
        tail = null;
    }

    public void add(int n) {
        DNode tmp = new DNode(n, head, tail);
        head = tmp;

    }

    public void push(int n) {
        DNode tmp = new DNode(n, head, tail);
        tmp.setNext(head);
        tmp.setPrev(null);
        if (head != null)
            head.setPrev(tmp);
        if (tail == null)
            tail = head;
        head = tmp;
    }

    public void enqueue(int n) {
        DNode tmp = new DNode(n, head, tail);
        tmp.setNext(null);
        tmp.setPrev(tail);
        if (tail != null)
            tail.setNext(tmp);
        tail = tmp;
    }

    public int dequeue() {
        int popValue = head.getVal();
        head.setVal(head.getNext().getVal());
        head.setNext(head.getNext().getNext());
        return popValue;
    }

    public int pop() {
        int popValue = head.getVal();
        head.setVal(head.getNext().getVal());
        head.setNext(head.getNext().getNext());
        return popValue;
    }
    private boolean isFirst()
    public void delete(DNode tarNode){
        if (tarNode.getPrev() == null && tarNode.getNext() != null) {

        }

    public void delete(int n) {
        DNode tmp = head;
        for (int i = 0; i < n; i++)
            tmp = tmp.getNext();
        if (tmp.getPrev() == null && tmp.getNext() != null) {
            tmp.getNext().setPrev(tmp.getPrev());
            head = tmp.getNext();
        }
        else if(tmp.getNext() == null && tmp.getPrev() != null) {
            tmp.getPrev().setNext(tmp.getNext());
            tail = tmp.getPrev();
        }
        else if (tmp.getPrev() != null && tmp.getNext() != null) {
            tmp.getPrev().setNext(tmp.getNext());
            tmp.getNext().setPrev(tmp.getPrev());
        }
//
//        System.out.println(tmp);
//        System.out.println(tmp.getNext());

        tmp.setPrev(null);
        tmp.setNext(null);
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
