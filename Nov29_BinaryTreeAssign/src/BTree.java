public class BTree {
    private BNode root;

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
                root.setRight(tmp);
            else
                add(branch.getRight(), tmp);
        } else if(tmp.getVal() < branch.getVal()){
            if (branch.getLeft() == null)
                root.setLeft(tmp);
            else
                add(branch.getLeft(), tmp);
        }
    }

    public String inOrder(BNode branch){
        if(branch != null)
            return inOrder(branch.getLeft()) + ", "+ branch.getVal()+ ", "+branch.getRight();
        return "";
    }

    @Override
    public String toString() {
        String ans = inOrder(root);//Using InOrder traversal
        return "{" + ans + "}";
    }
}
