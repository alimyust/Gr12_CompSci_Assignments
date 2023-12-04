public class BTreeTester {
    public static void main(String[] args) {
        BTree BT = new BTree();
        BTree BT2 = new BTree();
        BT.add(4);
        BT.add(3);
        BT.add(2);
        BT.add(19);
        BT.add(18);
        BT.add(25);
//        BT.add(25);
//        BT.add(42);
//        BT.add(2);
//        BT.add(1);
        BT.display();
        System.out.println(BT.isBalanced());

    }
}