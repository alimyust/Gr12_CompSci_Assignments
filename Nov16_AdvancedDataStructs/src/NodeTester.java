public class NodeTester {
    public static void main(String[] args) {
        Node a = new Node(2);
        Node b = new Node(4);
        Node c = new Node(6);
        Node d = new Node(7);
        Node head = a;

        a.next = b;
        b.next = c;
        c.next = d;

        System.out.println(head);
        System.out.println("Node count: " +nodeCount(head));
    }

    private static int nodeCount(Node head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return  count;
    }

    private static int nodeSum(Node head) {
        int sum = 0;
        while (head != null) {
            sum+=head.val;
            head = head.next;
        }
        return  sum;
    }
}
