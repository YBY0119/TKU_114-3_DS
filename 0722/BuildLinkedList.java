

class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class BuildLinkedList {

    // 由 head 走訪並印出串列
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("串列為空 (Empty List)");
            return;
        }
        Node current = head;
        System.out.print("鏈結串列內容: ");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    // 計算節點數量
    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // 計算所有節點數值的總和
    public static int sumNodes(Node head) {
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum += current.data;
            current = current.next;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1：正常建立 10, 20, 30, 40 串列 ===");
        // 1. 建立 10、20、30、40 四個 Node
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);

        // 2. 使用 next 正確連接
        Node head = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        // 3. 由 head 走訪輸出
        printList(head);

        // 4. 計算節點數與總和
        System.out.println("節點總數: " + countNodes(head));
        System.out.println("節點總和: " + sumNodes(head));

        System.out.println("\n=== 測試 2：邊界條件（空串列） ===");
        Node emptyHead = null;
        printList(emptyHead);
        System.out.println("節點總數: " + countNodes(emptyHead));
        System.out.println("節點總和: " + sumNodes(emptyHead));
    }
}