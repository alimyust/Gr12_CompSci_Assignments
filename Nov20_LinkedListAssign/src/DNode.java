public class DNode {
    private int val;
    private DNode next;
    private DNode prev;

    public DNode(int val,DNode next, DNode prev){
        this.val=val;
        this.next=next;
        this.prev= prev;
    }
    public int getVal(){
        return this.val;
    }
    public void setVal(int val){
        this.val=val;
    }
    public DNode getNext(){
        return this.next;
    }
    public void setNext(DNode next){
        this.next=next;
    }

    public DNode getPrev() {
        return prev;
    }

    public void setPrev(DNode prev) {
        this.prev = prev;
    }

    public String address(){
        return ""+this.hashCode();
    }

    public String toString(){
        String ans=""+this.val;
        ans += (this.prev == null)?":null":":@"+this.prev.address();
        ans += (this.next == null)?":null":":@"+this.next.address();

        return "<"+ans+">";
    }
}
