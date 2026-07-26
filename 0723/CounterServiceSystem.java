import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;
import java.util.List;

public class CounterServiceSystem {
    // 內部類別保存號碼與姓名
    static class Customer {
        int number;
        String name;

        Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public String toString() {
            return "號碼: " + number + " (" + name + ")";
        }
    }

    private Queue<Customer> waitingQueue = new LinkedList<>();
    private List<Customer> processedHistory = new ArrayList<>();
    private int ticketCounter = 1;

    // 取號
    public void takeNumber(String name) {
        Customer customer = new Customer(ticketCounter++, name);
        waitingQueue.add(customer);
        System.out.println("取號成功 -> " + customer);
    }

    // 叫號
    public void callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[提示] 目前沒有人在等待叫號。");
            return;
        }
        Customer current = waitingQueue.poll();
        processedHistory.add(current);
        System.out.println("請 " + current + " 至櫃台辦理！");
    }

    // 查看下一位與等待人數
    public void showStatus() {
        System.out.println("--- 叫號狀態 ---");
        System.out.println("目前等待人數: " + waitingQueue.size());
        if (!waitingQueue.isEmpty()) {
            System.out.println("下一位顧客: " + waitingQueue.peek());
        } else {
            System.out.println("下一位顧客: 無");
        }
    }

    // 顯示所有處理紀錄
    public void showHistory() {
        System.out.println("--- 已處理紀錄歷史 ---");
        if (processedHistory.isEmpty()) {
            System.out.println("(無歷史紀錄)");
        } else {
            for (Customer c : processedHistory) {
                System.out.println("已完成: " + c);
            }
        }
    }

    public static void main(String[] args) {
        CounterServiceSystem system = new CounterServiceSystem();

        system.callNext(); // 空 Queue 叫號，測試安全機制

        system.takeNumber("Alice");
        system.takeNumber("Bob");
        system.takeNumber("Charlie");

        system.showStatus();

        system.callNext();
        system.callNext();

        system.showStatus();
        system.showHistory();
    }
}