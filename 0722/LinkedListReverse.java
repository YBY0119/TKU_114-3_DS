

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListReverse {

    // 走訪列印
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("串列內容: [空串列]");
            return;
        }
        Node current = head;
        System.out.print("串列內容: ");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    // 1. reverse 方法：原地（In-place）反轉串列，不建立第二條串列
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        Node nextTemp = null;

        while (current != null) {
            nextTemp = current.next; // 暫存下一個節點，防止指標遺失
            current.next = prev;     // 將當前節點指向前一個節點（反轉）
            prev = current;          // prev 向前移動
            current = nextTemp;      // current 向前移動
        }

        // 當 current 為 null 時，prev 即為新串列的 head
        return prev;
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1：多節點串列反轉 ===");
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.print("反轉前: ");
        printList(head);

        head = reverse(head);

        System.out.print("反轉後: ");
        printList(head);

        System.out.println("\n=== 測試 2：單一節點串列反轉 ===");
        Node singleHead = new Node(100);

        System.out.print("反轉前: ");
        printList(singleHead);

        singleHead = reverse(singleHead);

        System.out.print("反轉後: ");
        printList(singleHead);

        System.out.println("\n=== 測試 3：空串列反轉 ===");
        Node emptyHead = null;

        System.out.print("反轉前: ");
        printList(emptyHead);

        emptyHead = reverse(emptyHead);

        System.out.print("反轉後: ");
        printList(emptyHead);
    }
}