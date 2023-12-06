public class BTreeTester {
    public static void main(String[] args) {
        BTree BT = new BTree();
        BTree BT2 = new BTree();
        BT.addList(new int[]{2,1,3});
        System.out.println(BT.height());
        System.out.println(BT.search(3));
        BT.display();
//        System.out.println(BT.isBalanced());

    }
}