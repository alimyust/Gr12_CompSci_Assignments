public class BTree {
    private BNode root;
    public static final int PRE = 0;
    public static final int IN = 1;
    public static final int POST = 2;
    public BTree() {
        root = null;
    }

    public BNode getRoot() {
        return root;
    }

    public void add(int n) {
        BNode tmp = new BNode(n);
        if (root == null)
            root = tmp;
        else
            add(root, tmp);
    }
    public BNode search(int n){
        BNode retNode = search(n, root);
        return (n != root.getVal() && retNode == root)? null:retNode;
    }
    public int sumLeaves(){return sumLeaves(0, root);}
    public int total(){return total(0,root);}
    public int height(){return height(-1, root);}
    public int depth(int n) {
        int dep = depth(n, 0, root);
        return (dep == 0 && root.getVal() != n) ? -1 : dep;
    }

    public void display(){
        System.out.println(this);
    }
    private void add(BNode branch, BNode tmp) {
        if (tmp.getVal() > branch.getVal()) {
            if (branch.getRight() == null)
                branch.setRight(tmp);
            else
                add(branch.getRight(), tmp);
        } else if (tmp.getVal() < branch.getVal()) {
            if (branch.getLeft() == null)
                branch.setLeft(tmp);
            else
                add(branch.getLeft(), tmp);
        }
    }
    private BNode search(int n, BNode branch){
        if(branch == null)
            return root;
        if(n > branch.getVal())
            return search(n, branch.getRight());
        if(n < branch.getVal())
            return search(n, branch.getLeft());
        return branch;
    }
    private int sumLeaves(int sum, BNode branch){
        if(branch.isLeaf())
            return branch.getVal();
        if(branch.getLeft() != null)
            sum += sumLeaves(sum, branch.getLeft());
        if(branch.getRight()  != null)
            sum += sumLeaves(sum, branch.getRight());
        return sum;
    }
    private int total(int sum, BNode branch){
        if(branch == null)
            return 0;
        sum = total(sum, branch.getLeft()) + total(sum, branch.getRight());
        return branch.getVal()+sum;
    }
    private int height(int hgt, BNode branch){
        if(branch == null)
            return hgt;
        hgt =Math.max(height( hgt + 1, branch.getLeft()),
                height(hgt + 1, branch.getRight()));
        return hgt;
    }
    public int depth(int n, int count, BNode branch) {
        if (branch != null) {
            if (n == branch.getVal())
                return count;
            return depth(n, count + 1, branch.getLeft()) + depth(n, count + 1, branch.getRight());
        }
        return 0;
    }
    public boolean isIdentical(BTree otherTree){
        return (this).equals(otherTree);
    }
    public boolean isBalanced(){
        return isBalanced( root, true);
    }
    private boolean isBalanced(BNode branch, boolean bal){
        if(branch == null)
            return bal;
        bal = Math.abs(countNodes(branch.getLeft()) - countNodes(branch.getRight())) <= 1;
        boolean a = isBalanced(branch.getLeft(), bal);
        boolean b = isBalanced(branch.getRight(), bal);
        System.out.println(branch+", " +a + ", " + b);
        return a && b;
    }

    public int countNodes(BNode branch){
        if(branch == null)
            return 0;
        return 1 + countNodes(branch.getLeft()) + countNodes(branch.getRight());
    }
    public void display(int type){
        String ans;
        if(type == PRE)
            ans = preOrder(root);
        else if(type == POST)
            ans = postOrder(root);
        else if(type == IN)
            ans = inOrder(root);
        else {
            System.out.println("Invalid Input");
            return;
        }
        if (!ans.isEmpty())
            ans = ans.substring(0, ans.length() - 2);
        System.out.println("{" + ans + "}");
    }
    private String preOrder(BNode branch) {
        if (branch != null)
            return branch.getVal() + ", " + preOrder(branch.getLeft()) + preOrder(branch.getRight());
        return "";
    }
    private String inOrder(BNode branch) {
        if (branch != null)
            return inOrder(branch.getLeft()) + branch.getVal() + ", " + inOrder(branch.getRight());
        return "";
    }
    private String postOrder(BNode branch) {
        if (branch != null)
            return postOrder(branch.getLeft()) + postOrder(branch.getRight()) + branch.getVal() + ", ";
        return "";
    }
    public String toString() {
        //we will use the inOrder traversal
        String ans = inOrder(root);
        if (!ans.isEmpty())
            ans = ans.substring(0, ans.length() - 2);
        return "{" + ans + "}";
    }
}