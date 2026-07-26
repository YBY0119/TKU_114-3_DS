import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class DeliveryProcessingSystem {
    private Queue<DeliveryTask> pendingQueue = new LinkedList<>(); // 待配送工作 Queue
    private Stack<DeliveryTask> completedStack = new Stack<>();     // 完成紀錄 Stack
    private List<String> logs = new ArrayList<>();                 // 所有處理紀錄 log

    private void addLog(String message) {
        logs.add(message);
        System.out.println(message);
    }

    // 1. 新增工作
    public void addTask(DeliveryTask task) {
        pendingQueue.add(task);
        addLog("[新增工作] " + task);
    }

    // 2. 完成下一筆
    public void completeNext() {
        if (pendingQueue.isEmpty()) {
            addLog("[警告] 待配送隊列為空，無工作可完成。");
            return;
        }
        DeliveryTask task = pendingQueue.poll();
        completedStack.push(task);
        addLog("[完成工作] " + task);
    }

    // 3. 查看下一筆
    public void peekNext() {
        if (pendingQueue.isEmpty()) {
            System.out.println("下一筆待配送: 無");
        } else {
            System.out.println("下一筆待配送: " + pendingQueue.peek());
        }
    }

    // 4. 復原最近完成 -> 回到等待 Queue 尾端
    public void undoLastCompleted() {
        if (completedStack.isEmpty()) {
            addLog("[警告] 無已完成工作可供復原。");
            return;
        }
        DeliveryTask task = completedStack.pop();
        pendingQueue.add(task); // 放回 Queue 尾端
        addLog("[復原工作] " + task + " 已回存至待配送隊列尾端");
    }

    // 5. 輸出等待數、完成數與所有處理紀錄
    public void printSummary() {
        System.out.println("\n=== 系統狀態與歷程報告 ===");
        System.out.println("等待配送數: " + pendingQueue.size());
        System.out.println("已完成數: " + completedStack.size());
        System.out.println("--- 所有處理紀錄 ---");
        for (String log : logs) {
            System.out.println(log);
        }
        System.out.println("========================\n");
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        system.addTask(new DeliveryTask("T001", "台北"));
        system.addTask(new DeliveryTask("T002", "台中"));
        system.addTask(new DeliveryTask("T003", "高雄"));

        system.peekNext();

        system.completeNext();
        system.completeNext();

        system.undoLastCompleted(); // 復原 T002，其將被放回 Queue 尾端

        system.printSummary();
    }
}