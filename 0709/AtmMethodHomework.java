import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 規則 1：初始餘額設定為 1000
        int balance = 1000;
        int option = -1;
        
        // 規則 5：選單可重複操作直到輸入 0
        while (option != 0) {
            // 呼叫方法 1：顯示選單
            printMenu();
            System.out.print("請輸入選項: ");
            option = sc.nextInt();
            
            switch (option) {
                case 1:
                    // 呼叫方法 5：印出目前餘額
                    printBalance(balance);
                    break;
                    
                case 2:
                    // 呼叫方法 2：讀取大於 0 的存款金額
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額: ");
                    // 呼叫方法 3：執行存款並更新餘額
                    balance = deposit(balance, depositAmount);
                    break;
                    
                case 3:
                    // 呼叫方法 2：讀取大於 0 的提款金額
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");
                    // 呼叫方法 4：執行提款並更新餘額
                    balance = withdraw(balance, withdrawAmount);
                    break;
                    
                case 0:
                    System.out.println("感謝您的使用，謝謝光臨！");
                    break;
                    
                default:
                    System.out.println("無效選項，請重新輸入！");
                    System.out.println();
                    break;
            }
        }
        sc.close();
    }

    // ==================== 以下為規定的 5 個自訂方法 ====================

    // 方法 1：顯示選單
    public static void printMenu() {
        System.out.println("=== ATM Menu ===");
        System.out.println("1: 查詢餘額");
        System.out.println("2: 存款");
        System.out.println("3: 提款");
        System.out.println("0: 離開");
    }

    // 方法 2：讀取合法的正數金額（規則 2 & 3：金額必須大於 0）
    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = 0;
        while (true) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount > 0) {
                break; // 金額大於 0，跳出防錯迴圈
            }
            System.out.println("錯誤：金額必須大於 0 元，請重新輸入！");
        }
        return amount; // 回傳合法的金額
    }

    // 方法 3：存款邏輯，回傳更新後的餘額
    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("存款成功！已存入 $" + amount + " 元，目前餘額: $" + balance + " 元");
        System.out.println();
        return balance; // 必須將更新後的餘額 return 護送回主程式
    }

    // 方法 4：提款邏輯，包含餘額驗證，回傳更新後的餘額
    public static int withdraw(int balance, int amount) {
        // 規則 4：提款金額不能大於目前餘額
        if (amount > balance) {
            System.out.println("錯誤：餘額不足！您目前餘額僅有 $" + balance + " 元");
        } else {
            balance -= amount;
            System.out.println("提款成功！已領取 $" + amount + " 元，目前餘額: $" + balance + " 元");
        }
        System.out.println();
        return balance; // 回傳餘額（若餘額不足則不變）
    }

    // 方法 5：印出餘額
    public static void printBalance(int balance) {
        System.out.println("目前帳戶餘額為: $" + balance + " 元");
        System.out.println();
    }
}
