import java.util.Scanner;

public class DrinkOrderSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 各商品銷售杯數統計器
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;

        int totalItems = 0;
        int totalAmount = 0;

        while (true) {
            // 1. 重複顯示商品選單
            printMenu();

            System.out.print("請輸入選項: ");
            if (!sc.hasNextInt()) {
                System.out.println("輸入錯誤，請輸入 0 到 4 的數字。");
                sc.next();
                continue;
            }
            int option = sc.nextInt();

            // 2. 選擇 0 時結帳並跳出迴圈
            if (option == 0) {
                break;
            }

            // 檢查選項是否合法 (1 ~ 4)
            if (option < 1 || option > 4) {
                System.out.println("無此商品選項，請重新輸入。");
                continue;
            }

            // 3. 選擇 1 到 4，要求輸入數量（呼叫防呆方法）
            int quantity = readValidQuantity(sc);

            // 4. 計算本次小計
            int price = getPrice(option);
            int subtotal = calculateSubtotal(price, quantity);

            // 顯示單次點單結果
            String itemName = getItemName(option);
            System.out.println(itemName + " x " + quantity);
            System.out.println("Subtotal: " + subtotal);

            // 5. 累加各項統計數據
            totalItems += quantity;
            totalAmount += subtotal;

            // 統計每一種商品各賣出幾杯
            switch (option) {
                case 1: blackTeaCount += quantity; break;
                case 2: greenTeaCount += quantity; break;
                case 3: milkTeaCount += quantity; break;
                case 4: coffeeCount += quantity; break;
            }
        }

        // 6. 輸出完整收據（包含折抵計算）
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount);

        sc.close();
    }

    /**
     * 1. 印出商品菜單選單
     */
    public static void printMenu() {
        System.out.println("\n=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
    }

    /**
     * 2. 依選項取得商品單價
     */
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 45;
            case 4: return 50;
            default: return 0;
        }
    }

    /**
     * 3. 依選項取得商品英文名稱
     */
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Milk tea";
            case 4: return "Coffee";
            default: return "";
        }
    }

    /**
     * 4. 讀取並驗證購買數量（數量必須大於 0）
     */
    public static int readValidQuantity(Scanner sc) {
        int quantity;
        while (true) {
            System.out.print("請輸入數量: ");
            if (sc.hasNextInt()) {
                quantity = sc.nextInt();
                if (quantity > 0) {
                    break; // 數量大於 0，合法跳出
                }
            } else {
                sc.next(); // 清除錯誤輸入
            }
            System.out.println("數量不合法，必須大於 0，請重新輸入。");
        }
        return quantity;
    }

    /**
     * 5. 計算單項商品點餐小計
     */
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    /**
     * 6. 計算折扣後總金額（滿 300 元打 9 折，否則維持原價）
     */
    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            // 打 9 折，使用 Math.round() 進行四捨五入取整數
            return (int) Math.round(totalAmount * 0.9);
        }
        return totalAmount;
    }

    /**
     * 7. 依據執行範例格式，輸出最終完整收據
     */
    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, int totalItems, int totalAmount) {
        // 計算最終應付金額
        int finalAmount = calculateDiscountedTotal(totalAmount);
        
        // 判斷是否有打折 (滿 300 元為 Yes，未滿為 No)
        String isDiscount = (totalAmount >= 300) ? "Yes" : "No";

        System.out.println("\n=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);
        System.out.println("Discount: " + isDiscount);
        System.out.println("Final amount: " + finalAmount);
    }
}
