// 檔案名稱：NumberHistoryList.java

class IntNode {
    int data;
    IntNode next;

    public IntNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class NumberHistoryList {
    private IntNode head;

    public NumberHistoryList() {
        this.head = null;
    }

    // 1. 前端新增 (Add First)
    public void addFirst(int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = head;
        head = newNode;
        System.out.println("【前端新增】: " + value);
    }

    // 1. 尾端新增 (Add Last)
    public void addLast(int value) {
        IntNode newNode = new IntNode(value);
        if (head == null) {
            head = newNode;
        } else {
            IntNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("【尾端新增】: " + value);
    }

    // 1. 搜尋 (Search)
    public boolean search(int value) {
        IntNode current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // 1. 刪除 (Remove)
    public boolean remove(int value) {
        if (head == null) {
            System.out.println("【刪除失敗】: 串列為空，找不到 " + value);
            return false;
        }

        if (head.data == value) {
            head = head.next;
            System.out.println("【刪除成功】: " + value);
            return true;
        }

        IntNode current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }

        if (current.next != null) {
            current.next = current.next.next;
            System.out.println("【刪除成功】: " + value);
            return true;
        }

        System.out.println("【刪除失敗】: 串列中無此數值 " + value);
        return false;
    }

    // 1. 輸出串列
    public void printList() {
        if (head == null) {
            System.out.println("當前串列: [空串列]");
            return;
        }
        System.out.print("當前串列: ");
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : ""));
            current = current.next;
        }
        System.out.println();
    }

    // 2 & 3. 統計資訊（size, 總和, 最大值, 最小值）
    public void printStatistics() {
        if (head == null) {
            System.out.println("【統計結果】串列為空，Size: 0, 總和: 0, 最大值: N/A, 最小值: N/A");
            return;
        }

        int size = 0;
        int sum = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        IntNode current = head;
        while (current != null) {
            size++;
            sum += current.data;
            if (current.data > max) max = current.data;
            if (current.data < min) min = current.data;
            current = current.next;
        }

        System.out.printf("【統計結果】Size: %d, 總和: %d, 最大值: %d, 最小值: %d%n", size, sum, max, min);
    }

    // 4. 至少測試 8 次操作
    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("=== 操作 1: 測試空串列統計 ===");
        list.printStatistics();

        System.out.println("\n=== 操作 2: 前端新增 20 ===");
        list.addFirst(20);

        System.out.println("\n=== 操作 3: 前端新增 10 ===");
        list.addFirst(10);

        System.out.println("\n=== 操作 4: 尾端新增 30 ===");
        list.addLast(30);

        System.out.println("\n=== 操作 5: 尾端新增 40 ===");
        list.addLast(40);
        list.printList();
        list.printStatistics();

        System.out.println("\n=== 操作 6: 搜尋 30 與 99 ===");
        System.out.println("是否存在 30? " + list.search(30));
        System.out.println("是否存在 99? " + list.search(99));

        System.out.println("\n=== 操作 7: 刪除頭節點 (10) ===");
        list.remove(10);
        list.printList();

        System.out.println("\n=== 操作 8: 刪除尾節點 (40) ===");
        list.remove(40);
        list.printList();

        System.out.println("\n=== 操作 9: 刪除不存在的節點 (99) ===");
        list.remove(99);
        list.printList();
        list.printStatistics();
    }
}