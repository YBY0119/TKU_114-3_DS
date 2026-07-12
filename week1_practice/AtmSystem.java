import java.util.Scanner;

public class AtmSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始設定
        int balance = 1000;
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;

        while (true) {
            // 1. 可重複操作選單
            printMenu();

            System.out.print("請輸入選項: ");
            if (!sc.hasNextInt()) {
                System.out.println("輸入錯誤，請輸入 0 到 4 的數字。");
                sc.next(); // 清除錯誤輸入
                continue;
            }
            int option = sc.nextInt();

            // 離開系統
            if (option == 0) {
                break;
            }

            switch (option) {
                case 1:
                    // 查詢餘額
                    printBalance(balance);
                    break;

                case 2:
                    // 存款金額必須大於 0（呼叫驗證防呆方法）
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額: ");
                    // 更新餘額與統計
                    balance = deposit(balance, depositAmount);
                    depositCount++;
                    totalDeposit += depositAmount;
                    // 依範例印出當前餘額
                    printBalance(balance);
                    break;

                case 3:
                    // 提款金額必須大於 0（呼叫驗證防呆方法）
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");
                    
                    // 檢查提款金額是否超過目前餘額
                    if (canWithdraw(balance, withdrawAmount)) {
                        balance = withdraw(balance, withdrawAmount);
                        withdrawCount++;
                        totalWithdraw += withdrawAmount;
                        printBalance(balance);
                    } else {
                        System.out.println("錯誤：提款金額不能超過目前餘額。");
                    }
                    break;

                case 4:
                    // 顯示目前操作統計摘要
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;

                default:
                    System.out.println("無此選項，請重新輸入。");
                    break;
            }
        }

        // 11. 離開時輸出完整摘要
        printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);

        sc.close();
    }

    /**
     * 1. 印出 ATM 功能主選單
     */
    public static void printMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    /**
     * 2. 讀取並驗證大於 0 的金額（存提款金額防呆機制）
     */
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                amount = sc.nextInt();
                if (amount > 0) {
                    break; // 金額大於 0，合法跳出
                }
            } else {
                sc.next(); // 清除錯誤輸入
            }
            System.out.println("金額不合法，必須大於 0，請重新輸入。");
        }
        return amount;
    }

    /**
     * 3. 執行存款操作，回傳更新後的餘額
     */
    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    /**
     * 4. 執行提款操作，回傳更新後的餘額
     */
    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    /**
     * 5. 檢查提款金額是否小於或等於當前餘額
     */
    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }

    /**
     * 6. 印出當前帳戶餘額
     */
    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }

    /**
     * 7. 印出完整操作統計摘要報表
     */
    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println("\n=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
    }
}