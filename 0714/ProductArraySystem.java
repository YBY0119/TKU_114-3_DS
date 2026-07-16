import java.util.Scanner;

public class ProductArraySystem {

    // 用於記錄結束時的「操作摘要」
    private static int totalPurchaseCount = 0; // 總購買次數
    private static int totalPurchaseAmount = 0; // 總消費金額
    private static int totalReplenishCount = 0; // 總補貨次數

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 初始化三個對應陣列
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readChoice(sc);

            switch (choice) {
                case 1:
                    // 1. 顯示全部商品
                    showAllProducts(names, prices, stocks);
                    break;
                case 2:
                    // 2. 依商品編號查詢
                    queryProduct(sc, names, prices, stocks);
                    break;
                case 3:
                    // 3. 購買商品並扣除庫存
                    purchaseProduct(sc, names, prices, stocks);
                    break;
                case 4:
                    // 4. 補充商品庫存
                    replenishStock(sc, names, stocks);
                    break;
                case 5:
                    // 5. 顯示低庫存商品（庫存 < 10）
                    showLowStock(names, prices, stocks);
                    break;
                case 6:
                    // 6. 顯示全部庫存總價值
                    showTotalValue(names, prices, stocks);
                    break;
                case 7:
                    // 7. 結束並顯示操作摘要
                    showSummary();
                    running = false;
                    break;
                default:
                    System.out.println("錯誤：無效的選項，請輸入 1 ~ 7。");
            }
            System.out.println();
        }
        sc.close();
    }

    // ==================== 7 個自訂方法 ====================

    /**
     * 方法 1: 顯示主選單
     */
    public static void displayMenu() {
        System.out.println("====== 商品陣列管理系統 ======");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品並扣除庫存");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (庫存 < 10)");
        System.out.println("6. 顯示全部庫存總價值");
        System.out.println("7. 結束程式");
        System.out.println("==============================");
    }

    /**
     * 方法 2: 讀取並驗證選單輸入
     */
    public static int readChoice(Scanner sc) {
        System.out.print("請選擇功能 (1~7): ");
        while (!sc.hasNextInt()) {
            System.out.print("格式錯誤，請輸入整數 (1~7): ");
            sc.next();
        }
        return sc.nextInt();
    }

    /**
     * 方法 3: 顯示全部商品列表（商品編號從 1 開始）
     */
    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 目前商品清單 ---");
        System.out.printf("%-6s\t%-15s\t%-8s\t%-8s\n", "編號", "商品名稱", "價格", "庫存量");
        System.out.println("----------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("[%d]\t%-15s\t$%d\t%d 個\n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    /**
     * 方法 4: 依商品編號查詢
     */
    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入要查詢的商品編號 (1 ~ " + names.length + "): ");
        int id = readValidId(sc, names.length);
        if (id == -1) return;

        int index = id - 1; // 轉為陣列索引
        System.out.println("\n--- 查詢結果 ---");
        System.out.println("商品名稱: " + names[index]);
        System.out.println("商品單價: $" + prices[index]);
        System.out.println("目前庫存: " + stocks[index] + " 個");
    }

    /**
     * 方法 5: 購買商品並扣除庫存（購買數量必須大於 0 且不能超過庫存）
     */
    public static void purchaseProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入要購買的商品編號 (1 ~ " + names.length + "): ");
        int id = readValidId(sc, names.length);
        if (id == -1) return;

        int index = id - 1;
        System.out.println("您選擇了: " + names[index] + " (目前庫存: " + stocks[index] + " 個, 單價: $" + prices[index] + ")");
        
        if (stocks[index] == 0) {
            System.out.println("抱歉，該商品已無庫存，無法購買！");
            return;
        }

        int quantity = 0;
        while (true) {
            System.out.print("請輸入要購買的數量: ");
            if (sc.hasNextInt()) {
                quantity = sc.nextInt();
                if (quantity > 0 && quantity <= stocks[index]) {
                    break;
                } else if (quantity <= 0) {
                    System.out.println("錯誤：購買數量必須大於 0！");
                } else {
                    System.out.println("錯誤：庫存不足！目前僅剩 " + stocks[index] + " 個。");
                }
            } else {
                System.out.println("錯誤：請輸入有效的整數！");
                sc.next();
            }
        }

        // 扣除庫存並計算金額
        stocks[index] -= quantity;
        int cost = prices[index] * quantity;
        System.out.println("恭喜您成功購買 " + quantity + " 個 " + names[index] + "！總共 $" + cost + " 元。");

        // 記錄到操作摘要
        totalPurchaseCount++;
        totalPurchaseAmount += cost;
    }

    /**
     * 方法 6: 補充商品庫存（補貨數量必須大於 0）
     */
    public static void replenishStock(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入要補貨的商品編號 (1 ~ " + names.length + "): ");
        int id = readValidId(sc, names.length);
        if (id == -1) return;

        int index = id - 1;
        System.out.println("目前 " + names[index] + " 庫存為: " + stocks[index] + " 個");

        int amount = 0;
        while (true) {
            System.out.print("請輸入要補貨的數量 (必須大於 0): ");
            if (sc.hasNextInt()) {
                amount = sc.nextInt();
                if (amount > 0) {
                    break;
                } else {
                    System.out.println("錯誤：補貨數量必須大於 0！");
                }
            } else {
                System.out.println("錯誤：請輸入有效的整數！");
                sc.next();
            }
        }

        // 增加庫存
        stocks[index] += amount;
        System.out.println("已成功將 " + names[index] + " 補貨 " + amount + " 個！目前新庫存為: " + stocks[index] + " 個。");

        // 記錄到操作摘要
        totalReplenishCount++;
    }

    /**
     * 方法 7: 顯示低庫存商品（庫存小於 10 視為低庫存）
     */
    public static void showLowStock(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 低庫存商品警示 (庫存 < 10) ---");
        System.out.printf("%-6s\t%-15s\t%-8s\n", "編號", "商品名稱", "剩餘庫存");
        System.out.println("----------------------------------------------");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.printf("[%d]\t%-15s\t%d 個\n", (i + 1), names[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("（目前所有商品庫存充足！）");
        }
    }

    // ==================== 輔助方法 (Helper Methods) ====================

    /**
     * 計算並顯示全部庫存總價值 (商品單價 * 庫存量 的總和)
     */
    public static void showTotalValue(String[] names, int[] prices, int[] stocks) {
        int totalValue = 0;
        System.out.println("\n--- 各商品庫存價值估算 ---");
        for (int i = 0; i < names.length; i++) {
            int val = prices[i] * stocks[i];
            System.out.printf("%-15s: %d 個 x $%d = 庫存價值 $%d\n", names[i], stocks[i], prices[i], val);
            totalValue += val;
        }
        System.out.println("----------------------------------------------");
        System.out.println("倉庫目前全部商品總價值：$" + totalValue + " 元");
    }

    /**
     * 讀取並驗證商品編號
     */
    private static int readValidId(Scanner sc, int maxId) {
        if (sc.hasNextInt()) {
            int id = sc.nextInt();
            if (id >= 1 && id <= maxId) {
                return id;
            } else {
                System.out.println("錯誤：無此商品編號！");
                return -1;
            }
        } else {
            System.out.println("錯誤：請輸入整數編號！");
            sc.next();
            return -1;
        }
    }

    /**
     * 結束並顯示操作摘要
     */
    private static void showSummary() {
        System.out.println("\n==============================================");
        System.out.println("                系統操作摘要                  ");
        System.out.println("==============================================");
        System.out.println("成功購買交易次數: " + totalPurchaseCount + " 次");
        System.out.println("顧客消費總金額  : $" + totalPurchaseAmount + " 元");
        System.out.println("進行商品補貨次數: " + totalReplenishCount + " 次");
        System.out.println("==============================================");
        System.out.println("感謝您使用本系統，程式已安全退出。");
    }
}