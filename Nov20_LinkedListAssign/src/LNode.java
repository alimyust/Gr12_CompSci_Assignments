public class LNode {
    private int val;
    private LNode next;

    public LNode(int val){
        this.val = val;
        this.next = null;
    }
    public LNode(int val, LNode next) {
        this.val = val;
        this.next = next;
    }

    public int getVal() {
        return this.val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public LNode getNext() {
        return this.next;
    }

    public void setNext(LNode next) {
        this.next = next;
    }

    public String address() {
        return "" + this.hashCode();
    }

    public String toString() {
        String ans = "" + this.val;
        ans += (this.next == null) ? ":null" : ":@" + this.next.address();
        return "<" + ans + ">";
    }
}
