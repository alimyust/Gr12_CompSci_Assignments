import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
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
    public void sortedInsert(LNode newNode){
        LNode tmp = head;
        while(tmp.getVal() > newNode.getVal() && tmp.getNext() != null) {
            if (tmp.getNext().getVal() <= newNode.getVal())
                break; //if the next value of curr is <= the new node or the list ends, break
            tmp = tmp.getNext(); // cycle through all values in LList
        }
        //tmp is the smallest value greater than newNode (4,3(tmp), 2(to be inserted as new node), 2....)
        newNode.setNext(tmp.getNext());
        //makes NN point at what's after tmp and makes tmp point at NN
        tmp.setNext(newNode);
        if(tmp == head) // if tmp is head (while doesn't run so greater than it all) make NN head
            head = newNode;
    }
    public void removeDuplicates() {
        LNode cur = head;
        Set<Integer> hashSet = new HashSet<>();
        int setLen;
        int oldSetLen = 0;
        hashSet.add(cur.getVal());

        while (cur.getNext().getNext() != null) {
            setLen = hashSet.size();
            hashSet.add(cur.getNext().getNext().getVal());
            // Check if the size changed after adding the next element
            if (oldSetLen == setLen) {
                System.out.println(cur);
                cur.setNext(cur.getNext().getNext());
            }

            cur = cur.getNext();
            oldSetLen = setLen;
        }
    }

    public void sortedRemoveDuplicates(){
        LNode cur=head;
        LNode tmp = null;
        while(cur.getNext()!= null){
            if(cur.getVal() == cur.getNext().getVal() && tmp == null)
                tmp= cur;
            if(tmp != null)
                tmp.setNext(cur.getNext());
            if(cur.getVal() != cur.getNext().getVal())
                tmp = null;
            cur = cur.getNext();
        }
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