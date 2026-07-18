import java.util.Scanner;

public class ProductManagementSystem {
    // 限制陣列長度為 10
    private static Product[] products = new Product[10];
    private static int productCount = 0; // 記錄目前陣列中的實際商品數量
    private static Scanner scanner = new Scanner(System.in);

    // 統計操作摘要用變數
    private static int totalSalesCount = 0;
    private static int totalRestockCount = 0;

    public static void main(String[] args) {
        // 1. 初始加入 5 項商品，保留 5 個空間供新增
        initProducts();

        // 2. 主程式選單迴圈
        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("請選擇操作項目 (1-9): ");
            int choice = getIntInput();

            switch (choice) {
                case 1: displayAllProducts(); break;
                case 2: searchProductByName(); break;
                case 3: addNewProduct(); break;
                case 4: sellProduct(); break;
                case 5: restockProduct(); break;
                case 6: updateProductPrice(); break;
                case 7: displayLowStockProducts(); break;
                case 8: displayTotalInventoryValue(); break;
                case 9: 
                    showSummary(); 
                    running = false; 
                    break;
                default:
                    System.out.println("[系統] 無效的選項，請重新輸入！");
            }
            System.out.println();
        }
    }

    // ==========================================
    // 主程式輔助方法 (共 10 個，滿足「至少 8 個」的要求)
    // ==========================================

    // 輔助方法 1: 初始化 5 項商品
    private static void initProducts() {
        products[0] = new Product("Apple iPhone 15", 29900, 15);
        products[1] = new Product("Sony Headphones", 9900, 5); // 測試低庫存 (<10)
        products[2] = new Product("Logitech Mouse", 2490, 25);
        products[3] = new Product("ASUS ROG Laptop", 45000, 3); // 測試低庫存 (<10)
        products[4] = new Product("Nintendo Switch", 9500, 12);
        productCount = 5;
        System.out.println("[系統] 系統初始化成功，已載入 5 項預設商品。");
    }

    // 輔助方法 2: 顯示選單介面
    private static void showMenu() {
        System.out.println("====== 物件導向商品管理系統 ======");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
        System.out.println("==================================");
    }

    // 輔助方法 3: 功能 1 - 顯示全部商品
    private static void displayAllProducts() {
        System.out.println("\n--- 目前所有商品清單 ---");
        if (productCount == 0) {
            System.out.println("目前無任何商品。");
            return;
        }
        for (int i = 0; i < productCount; i++) {
            System.out.println((i + 1) + ". " + products[i].toString());
        }
    }

    // 輔助方法 4: 功能 2 - 依完整名稱搜尋 (忽略大小寫與前後空白)
    private static void searchProductByName() {
        System.out.print("\n請輸入欲搜尋的完整商品名稱: ");
        String searchName = scanner.nextLine();
        
        int index = findProductIndex(searchName);
        if (index != -1) {
            System.out.println("[搜尋結果] 找到商品 -> " + products[index].toString());
        } else {
            System.out.println("[錯誤] 找不到該名稱的商品。");
        }
    }

    // 輔助方法 5: 功能 3 - 新增商品 (內含不可重複、陣列已滿檢查)
    private static void addNewProduct() {
        System.out.println("\n--- 新增商品功能 ---");
        // 檢查陣列是否已滿
        if (productCount >= products.length) {
            System.out.println("[錯誤] 陣列已滿，無法新增商品！");
            return;
        }

        System.out.print("請輸入商品名稱: ");
        String name = scanner.nextLine();

        // 檢查重複名稱 (忽略大小寫與前後空白)
        if (findProductIndex(name) != -1) {
            System.out.println("[錯誤] 不可新增重複名稱的商品！");
            return;
        }

        System.out.print("請輸入商品價格: ");
        int price = getIntInput();
        System.out.print("請輸入初始庫存: ");
        int stock = getIntInput();

        // 利用 Product 類別本身的驗證邏輯來維護資料規則
        products[productCount] = new Product(name, price, stock);
        System.out.println("[系統] 商品新增成功: " + products[productCount].toString());
        productCount++;
    }

    // 輔助方法 6: 功能 4 - 出售商品
    private static void sellProduct() {
        System.out.print("\n請輸入欲出售的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(name);

        if (index == -1) {
            System.out.println("[錯誤] 找不到該商品。");
            return;
        }

        System.out.print("請輸入出售數量: ");
        int qty = getIntInput();

        // 呼叫物件內建的 sell 方法
        if (products[index].sell(qty)) {
            System.out.println("[成功] 出售成功！目前商品狀態: " + products[index].toString());
            totalSalesCount += qty;
        } else {
            System.out.println("[錯誤] 出售失敗！請確認庫存量是否充足或數量是否大於 0。");
        }
    }

    // 輔助方法 7: 功能 5 - 補充庫存
    private static void restockProduct() {
        System.out.print("\n請輸入欲進貨的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(name);

        if (index == -1) {
            System.out.println("[錯誤] 找不到該商品。");
            return;
        }

        System.out.print("請輸入補充數量: ");
        int qty = getIntInput();

        // 呼叫物件內建的 restock 方法
        if (products[index].restock(qty)) {
            System.out.println("[成功] 補貨成功！目前商品狀態: " + products[index].toString());
            totalRestockCount += qty;
        } else {
            System.out.println("[錯誤] 補貨失敗！請輸入大於 0 的有效數量。");
        }
    }

    // 輔助方法 8: 功能 6 - 修改商品價格
    private static void updateProductPrice() {
        System.out.print("\n請輸入欲修改價格的商品名稱: ");
        String name = scanner.nextLine();
        int index = findProductIndex(name);

        if (index == -1) {
            System.out.println("[錯誤] 找不到該商品。");
            return;
        }

        System.out.print("請輸入新價格: ");
        int newPrice = getIntInput();

        // 呼叫物件內建的 setPrice 方法
        if (products[index].setPrice(newPrice)) {
            System.out.println("[成功] 價格修改成功！目前商品狀態: " + products[index].toString());
        } else {
            System.out.println("[錯誤] 修改失敗！價格必須大於 0。");
        }
    }

    // 輔助方法 9: 功能 7 & 功能 8 & 功能 9 的核心查找邏輯
    // 依據名稱尋找商品在陣列中的索引，符合「搜尋忽略大小寫與前後空白」規則
    private static int findProductIndex(String name) {
        if (name == null) return -1;
        String target = name.trim().toLowerCase();
        for (int i = 0; i < productCount; i++) {
            if (products[i].getName().trim().toLowerCase().equals(target)) {
                return i;
            }
        }
        return -1;
    }

    // 輔助方法 10: 功能 7 - 顯示低庫存商品
    private static void displayLowStockProducts() {
        System.out.println("\n--- 低庫存商品警告清單 (< 10) ---");
        boolean hasLowStock = false;
        for (int i = 0; i < productCount; i++) {
            // 呼交物件內建的 isLowStock 方法
            if (products[i].isLowStock()) {
                System.out.println("⚠️ " + products[i].toString());
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前所有商品庫存水位安全。");
        }
    }

    // 輔助方法 11: 功能 8 - 顯示全部庫存總價值
    private static void displayTotalInventoryValue() {
        long totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            totalValue += products[i].getInventoryValue();
        }
        System.out.println("\n[庫存統計] 目前倉庫內所有商品的總價值為: $" + totalValue + " 元");
    }

    // 輔助方法 12: 功能 9 - 結束並顯示操作摘要
    private static void showSummary() {
        System.out.println("\n==================================");
        System.out.println("系統已順利結束。以下為本次運行摘要：");
        System.out.println("運作結束時總商品數: " + productCount + " / 10");
        System.out.println("本次執行累計售出商品總件數: " + totalSalesCount + " 件");
        System.out.println("本次執行累計補充進貨總件數: " + totalRestockCount + " 件");
        System.out.println("==================================");
    }

    // 輔助方法 13: 處理安全的數字輸入讀取
    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("[警告] 請輸入有效的整數數字: ");
            scanner.next();
        }
        int val = scanner.nextInt();
        scanner.nextLine(); // 消耗換行符
        return val;
    }
}
