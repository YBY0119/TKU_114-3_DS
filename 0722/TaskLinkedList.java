public class TaskLinkedList {
    private TaskNode head;

    public TaskLinkedList() {
        this.head = null;
    }

    // 2. 緊急工作加入前端 (Add First)
    public void addEmergencyTask(String code, String description) {
        TaskNode newNode = new TaskNode(code, description);
        newNode.next = head;
        head = newNode;
        System.out.println("【新增緊急工作】: [" + code + "] " + description);
    }

    // 2. 一般工作加入尾端 (Add Last)
    public void addNormalTask(String code, String description) {
        TaskNode newNode = new TaskNode(code, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("【新增一般工作】: [" + code + "] " + description);
    }

    // 3. 完成工作
    public boolean completeTask(String code) {
        TaskNode current = head;
        while (current != null) {
            if (current.code.equalsIgnoreCase(code)) {
                current.isCompleted = true;
                System.out.println("【完成工作】: [" + code + "] " + current.description);
                return true;
            }
            current = current.next;
        }
        System.out.println("【操作失敗】找不到工作代碼: " + code);
        return false;
    }

    // 3. 刪除工作
    public boolean deleteTask(String code) {
        if (head == null) {
            System.out.println("【刪除失敗】工作清單為空。");
            return false;
        }

        if (head.code.equalsIgnoreCase(code)) {
            System.out.println("【刪除工作】: [" + head.code + "] " + head.description);
            head = head.next;
            return true;
        }

        TaskNode current = head;
        while (current.next != null && !current.next.code.equalsIgnoreCase(code)) {
            current = current.next;
        }

        if (current.next != null) {
            System.out.println("【刪除工作】: [" + current.next.code + "] " + current.next.description);
            current.next = current.next.next;
            return true;
        }

        System.out.println("【刪除失敗】找不到工作代碼: " + code);
        return false;
    }

    // 3. 列出未完成工作
    public void printPendingTasks() {
        System.out.println("\n=== 未完成工作清單 ===");
        TaskNode current = head;
        boolean hasPending = false;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println("- [" + current.code + "] " + current.description);
                hasPending = true;
            }
            current = current.next;
        }
        if (!hasPending) {
            System.out.println("(無未完成工作)");
        }
    }

    // 4. 輸出工作總數與未完成數量
    public void printSummary() {
        int total = 0;
        int pending = 0;
        TaskNode current = head;
        while (current != null) {
            total++;
            if (!current.isCompleted) {
                pending++;
            }
            current = current.next;
        }
        System.out.println("\n【工作統計】總數: " + total + " | 未完成數量: " + pending);
    }
}