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
        if (branch == null)
            return 0;
        if (branch.isLeaf()) // if a node is a leaf then return that value
            return branch.getVal();
//if left is available recurs left, and if it's a leaf it adds its value
        return sumLeaves(sum, branch.getLeft()) + sumLeaves(sum, branch.getRight());//after all the recursions the sum should have gone to every leaf
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
            return 0; // null values don't contribute to the sum, so return 0
        sum = total(sum, branch.getLeft()) + total(sum, branch.getRight());
        // adds the total value of the left and right branches
        return branch.getVal() + sum;
        // after all the recursions returns the sum + the value
    }

    public int depth(int n) {
        int dep = depth(n, 0, root);
        return (dep == 0 && root.getVal() != n) ? -1 : dep;
    }

    public int depth(int n, int count, BNode branch) {
        if(branch == null)
            return 0; // looks for the n value. if it's not found the method will recur until it hits a leaf
        if (n == branch.getVal()) // and that would make that entire path = 0
            return count; // when the correct branch is found return the count to get there
        return depth(n, count + 1, branch.getLeft()) + depth(n, count + 1, branch.getRight());
    }     // keep returning the sum of the left and right. When found in one branch the other would = 0.

//    public boolean isIdentical(BTree otherTree) {
//        return (this).equals(otherTree);
//    }
    public boolean isIdentical(BTree otherTree) {
        return isIdentical(root, otherTree.getRoot());
    }

    private boolean isIdentical(BNode branchA, BNode branchB) {
        if(branchA == null && branchB== null)
            return true; // if both are null return true (have same depth)
        if(branchA == null || branchB == null)
            return false; // acts as XOR since and case was already covered
        return  branchA.getVal() == branchB.getVal() && //if cur branches are equal and
                isIdentical(branchA.getLeft(), branchB.getLeft()) && // left ones equal and
                isIdentical(branchA.getRight(), branchB.getRight());//right ones equal return true
    }

    public boolean isBalanced() {
        return isBalancedWid(root);
    }

    private boolean isBalancedHgt(BNode branch){
        if(branch == null)
            return true; // using height
        return Math.abs(height(-1, branch.getLeft()) - height(-1,branch.getRight())) <= 1 &&
                isBalancedWid(branch.getLeft()) && isBalancedWid(branch.getRight());
    }
    private boolean isBalancedWid(BNode branch) {
        //using a helper method "countNodes" to count between the left and right to determine the difference
        if (branch == null)
            return true;//if leaf then just send the bal value of that branch
        //if the difference is greater than 1 it becomes false, otherwise true.
        return Math.abs(countNodes(branch.getLeft()) - countNodes(branch.getRight())) <= 1 &&
                isBalancedWid(branch.getLeft()) && isBalancedWid(branch.getRight());
        //if either side is false (not balanced) then the tree isn't either. so only returns true
    } //if every node of the tree is balanced (true && false is still false)

    public int countNodes(BNode branch) {
        if (branch == null)//sums the amount of nodes between a certain node
            return 0; // if it reaches null returns a 0
        //adds 1 to the left node and right nodes (for every recursion it adds 1 to each branch split)
        return 1 + countNodes(branch.getLeft()) + countNodes(branch.getRight());
    }

    public void display() {
        System.out.println(this);
    }

    public void display(int type) {
        String ans;
        //switch statement to determine which order type is needed
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
        if (branch != null) //DLR
            return branch.getVal() + ", " + preOrder(branch.getLeft()) + preOrder(branch.getRight());
        return ""; // different arrangements of Data, Left, Right to make
    }

    private String inOrder(BNode branch) {
        if (branch != null) // LDR
            return inOrder(branch.getLeft()) + branch.getVal() + ", " + inOrder(branch.getRight());
        return "";
    }

    private String postOrder(BNode branch) {
        if (branch != null) // LRD
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