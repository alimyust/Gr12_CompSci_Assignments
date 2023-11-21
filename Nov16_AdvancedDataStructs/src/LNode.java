public class LNode {
    private int val;
    private LNode next;
    private LNode prev;

    public LNode(int val,LNode prev, LNode next){
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
    public LNode getNext(){
        return this.next;
    }
    public void setNext(LNode next){
        this.next=next;
    }

    public LNode getPrev() {
        return prev;
    }

    public void setPrev(LNode prev) {
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