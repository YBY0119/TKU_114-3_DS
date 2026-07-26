import java.util.*;

public class ClinicQueueSystem {
    private Queue<Patient> queue = new LinkedList<>();
    private Set<Integer> usedIds = new HashSet<>();
    private Map<String, Integer> deptWaitingCount = new HashMap<>();
    private int totalServedCount = 0;

    // 掛號
    public boolean register(int id, String name, String department) {
        // 號碼不可重複
        if (usedIds.contains(id)) {
            System.out.println("[錯誤] 號碼 " + id + " 已存在，無法重複掛號！");
            return false;
        }

        Patient patient = new Patient(id, name, department);
        queue.add(patient);
        usedIds.add(id);

        deptWaitingCount.put(department, deptWaitingCount.getOrDefault(department, 0) + 1);
        System.out.println("掛號成功 -> " + patient);
        return true;
    }

    // 叫號
    public void callNext() {
        if (queue.isEmpty()) {
            System.out.println("[提示] 目前等待清單為空。");
            return;
        }
        Patient p = queue.poll();
        totalServedCount++;

        // 更新科別等待人數
        String dept = p.getDepartment();
        deptWaitingCount.put(dept, deptWaitingCount.get(dept) - 1);

        System.out.println("請診察: " + p);
    }

    // 查看下一位
    public void peekNext() {
        if (queue.isEmpty()) {
            System.out.println("下一位病患: 無");
        } else {
            System.out.println("下一位病患: " + queue.peek());
        }
    }

    // 顯示等待清單與統計資訊
    public void showStats() {
        System.out.println("\n--- 診所當前狀態統計 ---");
        System.out.println("等待清單明細: " + queue);
        System.out.println("各科別等待人數:");
        for (Map.Entry<String, Integer> entry : deptWaitingCount.entrySet()) {
            System.out.println(" - " + entry.getKey() + ": " + entry.getValue() + " 人");
        }
        System.out.println("總服務人數: " + totalServedCount + " 人\n");
    }

    public static void main(String[] args) {
        ClinicQueueSystem system = new ClinicQueueSystem();

        system.register(101, "張三", "內科");
        system.register(102, "李四", "外科");
        system.register(101, "王五", "內科"); // 測試重複號碼阻擋

        system.register(103, "趙六", "內科");

        system.showStats();
        system.peekNext();

        system.callNext();
        system.callNext();

        system.showStats();
    }
}