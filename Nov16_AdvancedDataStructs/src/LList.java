import java.util.LinkedList;

public class LList {
    private LNode head;
    private LNode tail;


    public LList(){
        head=null;
        tail=null;
    }

    public void add(int n){
        LNode tmp=new LNode(n,head,tail);
        head=tmp;

    }
    public void push(int n){
//        LNode tmp=new LNode(n,tail,head);
//        tail = tmp;
//        tmp.setNext(head);
//        tmp.setPrev(null);
//        if(head != null)
//            head.setPrev(tmp);
//        else
//            tail = head;
//        head = tmp;

        LNode f = head;
        LNode newNode = new LNode(n,null , f);
        head = newNode;
        if (f == null)
            tail = newNode;
        else
            f.setPrev(newNode);
    }
    public void enqueue(int n) {
        LNode tmpTail = tail;
        LNode tmp=new LNode(n,tmpTail,head);
        tail = tmp;
        if(tmpTail == null)
            head = tmp;
        else
            tmpTail.setNext(tmp);
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