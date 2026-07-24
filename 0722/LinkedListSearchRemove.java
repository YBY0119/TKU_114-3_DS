class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class LinkedListSearchRemove {

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

    // 1. contains 方法：搜尋值是否存在
    public static boolean contains(Node head, int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 1. removeValue 方法：刪除指定的 value，並回傳更新後的 head
    public static Node removeValue(Node head, int value) {
        // 處理空串列
        if (head == null) {
            System.out.println("【刪除失败】串列為空，無法執行刪除操作。");
            return null;
        }

        // 情況 A：要刪除的是頭節點 (head)
        if (head.data == value) {
            System.out.println("成功刪除頭節點: " + value);
            return head.next;
        }

        // 情況 B：刪除中間或尾部節點
        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }

        // 找不到資料
        if (current.next == null) {
            System.out.println("【刪除失敗】找不到值為 " + value + " 的節點。");
            return head;
        }

        // 找到資料，跨過該節點進行刪除
        System.out.println("成功刪除節點: " + value);
        current.next = current.next.next;
        return head;
    }

    public static void main(String[] args) {
        // 建立初始串列：10 -> 20 -> 30 -> 40 -> 50
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        head.next.next.next.next = new Node(50);

        System.out.println("--- 初始狀態 ---");
        printList(head);

        // 測試 contains
        System.out.println("\n--- 測試搜尋 (contains) ---");
        System.out.println("包含 30 ? " + contains(head, 30));
        System.out.println("包含 99 ? " + contains(head, 99));

        System.out.println("\n--- 測試刪除操作 ---");

        // 測試刪除 head (10)
        System.out.println("\n[1] 測試刪除 head (10):");
        head = removeValue(head, 10);
        printList(head);

        // 測試刪除 中間 (30)
        System.out.println("\n[2] 測試刪除 中間節點 (30):");
        head = removeValue(head, 30);
        printList(head);

        // 測試刪除 尾巴 (50)
        System.out.println("\n[3] 測試刪除 末尾節點 (50):");
        head = removeValue(head, 50);
        printList(head);

        // 測試 找不到資料 (100)
        System.out.println("\n[4] 測試刪除 不存在的節點 (100):");
        head = removeValue(head, 100);
        printList(head);

        // 測試 空串列 刪除
        System.out.println("\n[5] 測試對 空串列 進行刪除:");
        Node emptyHead = null;
        emptyHead = removeValue(emptyHead, 10);
        printList(emptyHead);
    }
}