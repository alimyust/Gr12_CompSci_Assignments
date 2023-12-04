import java.util.Objects;

public class BNode {
    private int val;
    private BNode left, right;

    public BNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public BNode getLeft() {
        return left;
    }

    public void setLeft(BNode left) {
        this.left = left;
    }

    public BNode getRight() {
        return right;
    }
    public String address(){
        return ""+this.hashCode();
    }
    public void setRight(BNode right) {
        this.right = right;
    }
    public boolean hasLeft(){
        return (left != null);
    }
    public boolean hasRight(){
        return (right != null);
    }
    public boolean isLeaf(){
        return(left == null && right == null);
    }
    @Override
    public String toString() {
        String ans=""+this.val;
        ans += (this.left == null)?":null":":@"+this.left.address();
        ans += (this.right == null)?":null":":@"+this.right.address();
        return "<"+ans+">";
    }
}