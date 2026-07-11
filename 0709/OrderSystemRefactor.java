import java.util.Scanner;

public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int totalAmount = 0; 
        int totalQuantity = 0; 
        int option = -1;
        
        while (option != 0) {
            // 1. 呼叫顯示選單方法
            printMenu();
            
            System.out.print("請輸入選項: ");
            option = sc.nextInt();
            
            if (option == 1 || option == 2 || option == 3) {
                // 3. 呼叫讀取合法數量方法
                int quantity = readValidQuantity(sc);
                
                // 2. 呼叫根據選項回傳價格方法
                int price = getPrice(option);
                
                // 4. 呼叫計算小計方法
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
        
        // 5. 呼叫印出收據方法
        printReceipt(totalQuantity, totalAmount);
        
        sc.close();
    }

    // ==================== 以下為指定的 5 個自訂方法 ====================

    // 方法 1：顯示選單
    public static void printMenu() {
        System.out.println("=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
    }

    // 方法 2：根據選項回傳價格 (有回傳值 int)
    public static int getPrice(int option) {
        int price = 0;
        switch (option) {
            case 1: price = 30; break;
            case 2: price = 35; break;
            case 3: price = 50; break;
        }
        return price; // 回傳價格給呼叫端
    }

    // 方法 3：讀取合法數量 (必須大於 0)
    public static int readValidQuantity(Scanner sc) {
        int quantity = 0;
        while (true) {
            System.out.print("請輸入數量: ");
            quantity = sc.nextInt();
            if (quantity > 0) {
                break; // 數量合法，跳出防錯迴圈
            }
            System.out.println("數量必須大於 0，請重新輸入！");
        }
        return quantity; // 回傳合法的數量
    }

    // 方法 4：計算小計 (傳入單價與數量，回傳乘積)
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    // 方法 5：印出收據
    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }
}
