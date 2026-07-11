import java.util.Scanner;

public class OrderSystemRefactor_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int totalAmount = 0; 
        int totalQuantity = 0; 
        int option = -1;
        
        // 2. 使用建立重複選單
        while (option != 0) {
            // 4. 使用方法顯示選單
            printMenu();
            
            System.out.print("請輸入選項: ");
            option = sc.nextInt();
            
            if (option == 1 || option == 2 || option == 3) {
                // 5. 使用方法讀取合法數量
                int quantity = readValidQuantity(sc);
                
                // 3. 使用方法判斷商品價格
                int price = getPrice(option);
                
                // 6. 使用方法計算小計
                int subtotal = calculateSubtotal(price, quantity);
                System.out.println("Subtotal: " + subtotal);
                System.out.println(); 
                
                // 累加
                totalQuantity += quantity;
                totalAmount += subtotal;
                
            } else if (option == 0) {
                System.out.println(); 
            } else {
                System.out.println("Invalid option, please try again.");
                System.out.println();
            }
        }
        
        // 7. 使用方法印出收據
        printReceipt(totalQuantity, totalAmount);
        
        sc.close();
    }

    // ==================== 自訂方法區塊 ====================

    // 4. 顯示選單方法
    public static void printMenu() {
        System.out.println("=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
    }

    // 3. 根據選項回傳價格方法 (使用 switch)
    public static int getPrice(int option) {
        int price = 0;
        switch (option) {
            case 1: price = 30; break;
            case 2: price = 35; break;
            case 3: price = 50; break;
        }
        return price; 
    }

    // 5. 讀取合法數量方法 (包含輸入驗證，確認大於 0)
    public static int readValidQuantity(Scanner sc) {
        int quantity = 0;
        while (true) {
            System.out.print("請輸入數量: ");
            quantity = sc.nextInt();
            if (quantity > 0) {
                break; 
            }
            System.out.println("數量必須大於 0！");
        }
        return quantity; 
    }

    // 6. 計算小計方法
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    // 7. 印出收據方法
    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }
}
