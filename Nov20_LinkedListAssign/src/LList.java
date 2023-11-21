public class LList {
    private LNode head;


    public LList(){
        head=null;
    }

    public void add(int n){
        LNode tmp=new LNode(n,head);
        head=tmp;

    }
    public void push(int n){
        LNode tmp=new LNode(n,head);
        head = tmp;
    }
    public int pop(){
        int popValue= head.getVal();
        head.setVal(head.getNext().getVal());
        head.setNext(head.getNext().getNext());
        return popValue;
    }

    public String toString(){
        LNode tmp=head;
        String ans="";
        while (tmp!=null){
            ans+=tmp+", ";
            tmp=tmp.getNext();
        }
        if (head!=null) {//the list is not empty
            ans = ans.substring(0, ans.length() - 2);//remove the ", "
        }
        return "["+ans+"]";
    }
}