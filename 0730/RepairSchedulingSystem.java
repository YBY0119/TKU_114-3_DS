import java.util.*;

public class RepairSchedulingSystem {
    private List<RepairTask> allTasks = new ArrayList<>();
    private Queue<RepairTask> waitingQueue = new LinkedList<>();
    private Stack<RepairTask> completedStack = new Stack<>();
    private int orderCounter = 1;

    public void addWork(String id, String deviceName, int priority) {
        RepairTask task = new RepairTask(id, deviceName, priority, orderCounter++);
        allTasks.add(task);
        waitingQueue.offer(task);
        System.out.println("✅ 成功新增維修工作: " + task);
    }

    public void sortWaitingQueueByPriority() {
        List<RepairTask> list = new ArrayList<>(waitingQueue);
        if (list.isEmpty()) return;
        RepairAlgorithms.mergeSort(list, 0, list.size() - 1);
        waitingQueue.clear();
        waitingQueue.addAll(list);
        System.out.println("✅ 等待 Queue 已依優先等級重新排程。");
    }

    public RepairTask completeTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("⚠️ 無等待中的維修工作！");
            return null;
        }
        RepairTask finished = waitingQueue.poll();
        completedStack.push(finished);
        System.out.println("🛠️ 已完成工作: " + finished);
        return finished;
    }

    public boolean undoLastCompletion() {
        if (completedStack.isEmpty()) {
            System.out.println("❌ 復原失敗：已完成紀錄為空！");
            return false;
        }
        RepairTask restored = completedStack.pop();
        waitingQueue.offer(restored);
        System.out.println("🔄 已復原工作至等待隊列: " + restored);
        return true;
    }

    public void displayStats() {
        System.out.println("\n----------------- 排程統計報告 -----------------");
        System.out.println("總工作數 (ArrayList): " + allTasks.size());
        System.out.println("等待中工作數 (Queue): " + waitingQueue.size());
        System.out.println("已完成工作數 (Stack): " + completedStack.size());
        System.out.println("------------------------------------------------\n");
    }

    public static void main(String[] args) {
        RepairSchedulingSystem sys = new RepairSchedulingSystem();

        sys.addWork("T001", "Server A", 3);
        sys.addWork("T002", "Router B", 5);
        sys.addWork("T003", "Switch C", 5); // 相同等級，驗證穩定排序
        sys.addWork("T004", "Server A", 1);

        sys.displayStats();

        System.out.println("=== 依優先級重新排序等待隊列 ===");
        sys.sortWaitingQueueByPriority();

        System.out.println("\n=== 執行完成工作與 Stack 復原 ===");
        sys.completeTask();
        sys.completeTask();

        sys.displayStats();

        sys.undoLastCompletion();

        sys.displayStats();
    }
}