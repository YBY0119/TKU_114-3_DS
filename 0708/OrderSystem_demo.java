import java.util.Scanner;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int totalAmount = 0; 
        int totalQuantity = 0; 
        
        int option = -1;
        
        while (option != 0) {
            System.out.println("=== Order Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            
            System.out.print("請輸入選項: ");
            option = sc.nextInt();
            
            if (option == 1 || option == 2 || option == 3) {
            
                System.out.print("請輸入數量: ");
                int quantity = sc.nextInt();
                
              
                if (quantity <= 0) {
                    System.out.println("數量必須大於 0");
                    continue; 
                }
                
                int price = 0;
                
               
                switch (option) {
                    case 1:
                        price = 30;
                        break;
                    case 2:
                        price = 35;
                        break;
                    case 3:
                        price = 50;
                        break;
                }
                
                // 計算本次小計
                int subtotal = price * quantity;
                System.out.println("Subtotal: " + subtotal);
                System.out.println(); // 空行對齊範例
                
                // 累加總數量與總金額
                totalQuantity += quantity;
                totalAmount += subtotal;
                
            } else if (option == 0) {
                // 選擇 0 時結束
                System.out.println(); // 換行對齊範例
            } else {
                System.out.println("Invalid option, please try again.");
                System.out.println();
            }
        }
        
        // 輸出最後總數量與總金額（收據格式）
        System.out.println("=== Receipt ===");
        System.out.println("Total items: " + totalQuantity);
        System.out.println("Total amount: " + totalAmount);
        
        sc.close();
    }
}
