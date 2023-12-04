public class BTreeTester {
    public static void main(String[] args) {
        BTree BT = new BTree();
        BTree BT2 = new BTree();
        BT.add(9);
        BT.add(15);
        BT.add(5);
        BT.add(3);
        BT.add(6);
        BT.add(4);
//        BT.add(2);
//        BT.add(1);
        BT.display();
        System.out.println(BT.isBalanced());

    }
}