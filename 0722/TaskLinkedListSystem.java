public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("--- 1. 新增工作項目 ---");
        taskList.addNormalTask("T01", "撰寫週報");
        taskList.addNormalTask("T02", "整理程式碼庫");
        
        // 插入緊急工作
        taskList.addEmergencyTask("E01", "修復伺服器 Crash 漏洞");

        taskList.printPendingTasks();
        taskList.printSummary();

        System.out.println("\n--- 2. 標記完成工作 ---");
        taskList.completeTask("E01"); // 完成緊急工作
        
        taskList.printPendingTasks();
        taskList.printSummary();

        System.out.println("\n--- 3. 刪除工作項目 ---");
        taskList.deleteTask("T01"); // 刪除 T01

        taskList.printPendingTasks();
        taskList.printSummary();

        System.out.println("\n--- 4. 測試異常操作 ---");
        taskList.completeTask("T99"); // 找不到工作
        taskList.deleteTask("T99");     // 找不到工作
    }
}