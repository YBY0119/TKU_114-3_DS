import java.util.*;

public class EventRegistrationSystem {
    private int capacity;
    private List<Registration> allRegistrations = new ArrayList<>();
    private Queue<Registration> waitingQueue = new LinkedList<>();
    private Stack<Registration> cancelStack = new Stack<>();
    private Set<String> registeredIds = new HashSet<>();

    public EventRegistrationSystem(int capacity) {
        this.capacity = capacity;
    }

    public boolean register(String id, String name) {
        if (registeredIds.contains(id)) {
            System.out.println("❌ 報名失敗：重複的報名編號 [" + id + "]");
            return false;
        }

        Registration reg = new Registration(id, name);
        registeredIds.add(id);

        if (allRegistrations.size() < capacity) {
            allRegistrations.add(reg);
            System.out.println("✅ 正式報名成功: " + reg);
        } else {
            waitingQueue.offer(reg);
            System.out.println("⏳ 名額已滿，放入候補 Queue: " + reg);
        }
        return true;
    }

    public boolean cancel(String id) {
        Registration found = null;
        for (Registration r : allRegistrations) {
            if (r.getId().equals(id)) {
                found = r;
                break;
            }
        }

        if (found == null) {
            System.out.println("❌ 取消失敗：找不到報名編號 [" + id + "]");
            return false;
        }

        allRegistrations.remove(found);
        registeredIds.remove(id);
        cancelStack.push(found);
        System.out.println("🗑️ 已取消報名並存入復原 Stack: " + found);

        // 若候補 Queue 不為空，遞補至正式名單
        if (!waitingQueue.isEmpty()) {
            Registration candidate = waitingQueue.poll();
            allRegistrations.add(candidate);
            System.out.println("🎉 候補遞補成功: " + candidate);
        }
        return true;
    }

    public boolean undoCancel() {
        if (cancelStack.isEmpty()) {
            System.out.println("❌ 復原失敗：取消紀錄 Stack 為空！");
            return false;
        }
        Registration restored = cancelStack.pop();
        return register(restored.getId(), restored.getName());
    }

    public static void main(String[] args) {
        EventRegistrationSystem sys = new EventRegistrationSystem(2); // 上限 2 人

        System.out.println("=== 1. 報名測試 ===");
        sys.register("R002", "Alice");
        sys.register("R001", "Bob");
        sys.register("R003", "Charlie"); // 額滿，進入 Queue
        sys.register("R001", "Duplicate"); // 重複編號測試

        System.out.println("\n=== 2. 取消與候補遞補測試 ===");
        sys.cancel("R002"); // 取消 Alice，Charlie 遞補

        System.out.println("\n=== 3. 測試復原取消 (Undo) ===");
        sys.undoCancel();

        System.out.println("\n=== 4. 測試取消不存在資料 ===");
        sys.cancel("R999");
    }
}