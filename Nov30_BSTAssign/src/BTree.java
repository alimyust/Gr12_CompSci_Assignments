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

    public void addList(int[] valList) {
        //method to make debugging easier
        for (int j : valList)
            this.add(j);
    }

    public void add(int n) {
        BNode tmp = new BNode(n);
        if (root == null) // if empty tree root = node to be added
            root = tmp;
        else
            add(root, tmp);
    }

    public void add(BTree otherTree) {
        //overloaded add for other tree
        addNodes(otherTree.getRoot());
    }

    private void addNodes(BNode branch) {
        //iterates through the other tree
        if (branch == null)
            return;//if null then stop recursion and go back
        add(branch.getVal()); // calls add method for each value
        addNodes(branch.getLeft());//covers all left and right branches
        addNodes(branch.getRight());
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

    public BNode search(int n) {
        return search(n, root); // calls search that has the recursion
    }

    private BNode search(int n, BNode branch) {
        if (branch == null) // if the branch gets to null by following the most optimal path
            return null; //then the desired node doesn't exist so return null
        if (n > branch.getVal()) // if the value is greater recurs right
            return search(n, branch.getRight());
        if (n < branch.getVal()) // if the value is left recurs left
            return search(n, branch.getLeft());
        return branch;//if n == branch.val then return that node all the way
    }

    public int sumLeaves() {
        return sumLeaves(0, root);
    }//overloaded with another method that recurs

    private int sumLeaves(int sum, BNode branch) {
        if (branch.isLeaf()) // if a node is a leaf then return that value
            return branch.getVal();
        if (branch.getLeft() != null)//if left is avalible recurs left, and if it's a leaf it adds its value
            sum += sumLeaves(sum, branch.getLeft());
        if (branch.getRight() != null)
            sum += sumLeaves(sum, branch.getRight());
        return sum;//after all the recursions the sum should have gone to every leaf
    }

    public int height() {
        return height(-1, root);
    }

    private int height(int hgt, BNode branch) {
        if (branch == null)
            return hgt; //when leaf is reached return the height
        hgt = Math.max(height(hgt + 1, branch.getLeft()),
                height(hgt + 1, branch.getRight()));
        //height is equal to the maximum value between the left height and the right height
        return hgt;
    }

    public int total() {
        return total(0, root);
    }

    private int total(int sum, BNode branch) {
        if (branch == null)
            return 0;
        sum = total(sum, branch.getLeft()) + total(sum, branch.getRight());
        return branch.getVal() + sum;
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

    public boolean isIdentical(BTree otherTree) {
        return (this).equals(otherTree);
    }

    public boolean isBalanced() {
        return isBalanced(root, true);
    }

    private boolean isBalanced(BNode branch, boolean bal) {
        if (branch == null)
            return bal;
        bal = Math.abs(countNodes(branch.getLeft()) - countNodes(branch.getRight())) <= 1;
        boolean a = isBalanced(branch.getLeft(), bal);
        boolean b = isBalanced(branch.getRight(), bal);
        System.out.println(branch + ", " + a + ", " + b);
        return a && b;
    }

    public int countNodes(BNode branch) {
        if (branch == null)
            return 0;
        return 1 + countNodes(branch.getLeft()) + countNodes(branch.getRight());
    }

    public void display() {
        System.out.println(this);
    }

    public void display(int type) {
        String ans;
        switch (type) {
            case PRE -> ans = preOrder(root);
            case POST -> ans = postOrder(root);
            case IN -> ans = inOrder(root);
            default -> {
                System.out.println("Invalid Input");
                return;
            }
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