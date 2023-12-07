public class BTreeTester {
    public static void main(String[] args) {
        BTree BT = new BTree();
        BTree BT2 = new BTree();
        BT.addList(new int[]{3, 1, 2, 0, 5, 6});
        BT2.addList(new int[]{3, 1, 2, 5, 6});
        BT.display();
        System.out.println("Depth of 0:      " + BT.depth(0));
        System.out.println("Invalid depth:   " + BT.depth(-24));
        System.out.print("PreOrder:        ");BT.display(0);
        System.out.print("InOrder:         ");BT.display(1);
        System.out.print("PostOrder:       ");BT.display(2);
        System.out.println("Search for 2:    " + BT.search(2));
        System.out.println("Sum Leaves:      " + BT.sumLeaves());
        System.out.println("Total:           " + BT.total());
        System.out.println("Height:          " + BT.height());
        System.out.println("IsIdentical:     " + BT.isIdentical(BT2));
       //^doesn't work
        System.out.println("IsBalanced:      " + BT.isBalanced());
        BT.add(BT2);
        System.out.println("Add Other BST: ");BT.display();


    }
}