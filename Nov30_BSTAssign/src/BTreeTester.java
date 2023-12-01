public class BTreeTester {
    public static void main(String[] args) {
        BTree BT = new BTree();
        BT.add(12);
        BT.add(2);
        BT.add(13);
//        BT.add(3);
//        BT.add(18);
//        BT.add(9);
        System.out.println();
        BT.display();
        System.out.println(BT.sumLeaves());

    }
}