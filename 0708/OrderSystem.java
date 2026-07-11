import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
       
        int totalAmount = 0; // 總金額
        int totalQuantity = 0; // 總數量
        
        int option = -1;
        
        // 當使用者沒有輸入 0 的時候，就一直重複點餐流程
        while (option != 0) {
            // 1. 顯示功能選單
            System.out.println("\n=== 商品選單 ===");
            System.out.println("1. 紅茶\t$30");
            System.out.println("2. 綠茶\t$35");
            System.out.println("3. 咖啡\t$50");
            System.out.println("0. 結帳");
            System.out.print("請輸入商品選項: ");
            
            // 2. 使用者輸入商品選項
            option = sc.nextInt();
            
            // 如果選擇 1, 2, 3，執行點餐與數量輸入
            if (option == 1 || option == 2 || option == 3) {
                System.out.print("請輸入數量: ");
                int quantity = sc.nextInt();
                
                // 4. 檢查數量必須大於 0
                if (quantity <= 0) {
                    System.out.println("數量必須大於 0,請重新點餐！");
                    continue; // 跳過本次後面流程，直接回到迴圈開頭重新選單
                }
                
                int price = 0;  // 用來記錄單價
                String itemName = ""; // 用來記錄商品名稱
                
                // 使用 switch 判斷單價與名稱
                switch (option) {
                    case 1:
                        itemName = "紅茶";
                        price = 30;
                        break;
                    case 2:
                        itemName = "綠茶";
                        price = 35;
                        break;
                    case 3:
                        itemName = "咖啡";
                        price = 50;
                        break;
                }
                
                // 5. 計算本次小計
                int subtotal = price * quantity;
                System.out.println("已點: " + itemName + " " + quantity + " 杯，小計: $" + subtotal);
                
                // 6. 累加總數量與總金額
                totalQuantity += quantity;
                totalAmount += subtotal;
                
            } else if (option == 0) {
                // 7. 選擇 0 時，跳出提示準備結帳
                System.out.println("正在為您結帳...");
            } else {
                // 輸入錯誤數字的情況
                System.out.println("無效的選項，請重新輸入！");
            }
        }
        
        // 7. 離開迴圈後，輸出最終的總數量與總金額
        System.out.println("\n========= 結帳明細 =========");
        System.out.println("總購買數量: " + totalQuantity + " 杯");
        System.out.println("總消費金額: $" + totalAmount + " 元");
        
        sc.close();
    }
}
