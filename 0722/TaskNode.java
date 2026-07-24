
public class TaskNode {
    String code;         // 工作代碼
    String description;  // 工作說明
    boolean isCompleted; // 完成狀態
    TaskNode next;

    public TaskNode(String code, String description) {
        this.code = code;
        this.description = description;
        this.isCompleted = false; // 預設未完成
        this.next = null;
    }
}