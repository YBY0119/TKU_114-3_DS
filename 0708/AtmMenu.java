import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 規則 1：初始餘額設定為 1000
        int balance = 1000;
        
        // 初始化選項變數，使其能順利進入 while 迴圈
        int option = -1;
        
        // 規則 6：使用 while 讓選單可以重複操作
        while (option != 0) {
            System.out.println("=== ATM Menu ===");
            System.out.println("1: 查詢餘額");
            System.out.println("2: 存款");
            System.out.println("3: 提款");
            System.out.println("0: 離開");
            System.out.print("請輸入選項: ");
            
            option = sc.nextInt();
            
            // 規則 7：使用 switch 判斷選項
            switch (option) {
                case 1:
                    // 功能 1：查詢餘額
                    System.out.println("目前帳戶餘額為: $" + balance + " 元");
                    System.out.println();
                    break;
                    
                case 2:
                    // 功能 2：存款
                    System.out.print("請輸入存款金額: ");
                    int deposit = sc.nextInt();
                    
                    // 規則 2：存款金額必須大於 0 (作業要求的輸入驗證之一)
                    if (deposit <= 0) {
                        System.out.println("錯誤：存款金額必須大於 0 元！");
                    } else {
                        balance += deposit; // 累加至餘額
                        // 規則 5：每次操作後顯示必要結果
                        System.out.println("存款成功！已存入 $" + deposit + " 元，目前餘額: $" + balance + " 元");
                    }
                    System.out.println();
                    break;
                    
                case 3:
                    // 功能 3：提款
                    System.out.print("請輸入提款金額: ");
                    int withdraw = sc.nextInt();
                    
                    // 規則 3：提款金額必須大於 0 (作業要求的輸入驗證之二)
                    if (withdraw <= 0) {
                        System.out.println("錯誤：提款金額必須大於 0 元！");
                    } 
                    // 規則 4：提款金額不能大於目前餘額
                    else if (withdraw > balance) {
                        System.out.println("錯誤：餘額不足！您目前餘額僅有 $" + balance + " 元");
                    } 
                    else {
                        balance -= withdraw; // 從餘額扣除
                        // 規則 5：每次操作後顯示必要結果
                        System.out.println("提款成功！已領取 $" + withdraw + " 元，目前餘額: $" + balance + " 元");
                    }
                    System.out.println();
                    break;
                    
                case 0:
                    // 功能 0：離開
                    System.out.println("感謝您的使用");
                    break;
                    
                default:
                    System.out.println("無效選項，請重新輸入");
                    System.out.println();
                    break;
            }
        }
        
        sc.close();
    }
}

