
//List node
public class LNode {
    private int val;
    private LNode next;

    public LNode(int val, LNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public LNode getNext() {
        return next;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public void setNext(LNode next) {
        this.next = next;
    }
    public String address(){
        return "" + this.hashCode();
    }

    @Override
    public String toString() {
        String ans = "" + this.val;
        if(this.next == null)
            ans += ":"+ null;
        else
            ans += " :@" + this.next.address();
        return "<" + ans + ">";
    }
}
