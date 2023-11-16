import java.util.LinkedList;

//Linked list
public class LList {
private LNode head;

    public LList() {
    head = null;
    }
    public void add(int n){
        LNode tmp = new LNode(n, head);
        head = tmp;
    }

    @Override
    public String toString() {
        LNode tmp = head;
        String strOut = "";
        while(tmp != null) {
            strOut += tmp + ", ";
            tmp = tmp.getNext();
        }
            return "[" +strOut.substring(0,strOut.length()-2) + "]";
    }
LinkedList a = new LinkedList();
}
