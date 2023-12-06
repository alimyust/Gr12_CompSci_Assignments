public class BTreeTester {
    public static void main(String[] args) {
        BTree BT = new BTree();
        BTree BT2 = new BTree();
        BT.addList(new int[]{5,0,10});
        BT2.addList(new int[]{4,-10,9});
        BT.display();
        BT2.display();
//        BT.add(2);
//        BT.add(1);
        BT.add(BT2);
        BT.display();
//        System.out.println(BT.isBalanced());

    }
}