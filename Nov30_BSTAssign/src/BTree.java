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
    public BNode search(int n){
        BNode retNode = search(n, root);
        return (n != root.getVal() && retNode == root)? null:retNode;
//        return (root.getVal() != n) ? -1 : dep;
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
    private boolean isLeaf(BNode tmp){
        return(tmp.getLeft() == null && tmp.getRight() == null);
    }
    public int sumLeaves(){
        return sumLeaves(0, root);
    }
    private int sumLeaves(int sum, BNode branch){
        if(isLeaf(branch))
            return branch.getVal();
        if(branch.getLeft() != null)
            sum += sumLeaves(sum, branch.getLeft());
        if(branch.getRight()  != null)
            sum += sumLeaves(sum, branch.getRight());
        return sum;
    }
    public void display(){
        System.out.println(this);
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
    public int depth(int n) {
        int dep = depth(n, 0, root);
        return (dep == 0 && root.getVal() != n) ? -1 : dep;
    }
    public int depth(int n, int count, BNode branch) {
        if (branch != null) {
            if (n == branch.getVal())
                return count;
            return depth(n, count + 1, branch.getLeft()) + depth(n, count + 1, branch.getRight());
        }
        return 0;
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

