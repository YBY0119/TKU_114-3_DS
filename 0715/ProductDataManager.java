import java.util.Scanner;

public class ProductDataManager {

    // 初始預設文字資料
    private static String[] records = {
        "Keyboard,890,12",
        "Mouse,490,20",
        "Monitor,5200,5",
        "USB Cable,250,30",
        "Headset,1290,8"
    };

    // 平行陣列，用於分別存入商品名稱、價格與庫存
    private static String[] names;
    private static int[] prices;
    private static int[] stocks;

    public static void main(String[] args) {
        // 1. & 2. 初始化時使用 split() 解析每筆資料並存入陣列
        parseRecords();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 6) {
            System.out.println("\n=== 商品文字資料管理器 ===");
            System.out.println("1. 顯示解析後的商品表格");
            System.out.println("2. 商品名稱搜尋 (支援完整與部分搜尋)");
            System.out.println("3. 顯示低庫存商品 (庫存 <= 10)");
            System.out.println("4. 顯示庫存總價值");
            System.out.println("5. 輸入一筆新文字資料 (含格式驗證)");
            System.out.println("6. 結束程式");
            System.out.print("請選擇功能 (1-6): ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // 消耗換行符
            } else {
                System.out.println("請輸入有效的數字功能鍵！");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    displayProductTable();
                    break;
                case 2:
                    System.out.print("請輸入要搜尋的商品名稱(完整或部分): ");
                    String searchKey = scanner.nextLine();
                    searchProduct(searchKey);
                    break;
                case 3:
                    displayLowStockProducts(10); // 設庫存 <= 10 為低庫存
                    break;
                case 4:
                    displayTotalValue();
                    break;
                case 5:
                    System.out.print("請輸入新商品文字資料 (格式: 名稱,價格,庫存): ");
                    String newRecord = scanner.nextLine();
                    addNewRecord(newRecord);
                    break;
                case 6:
                    System.out.println("系統已結束。完成後別忘了 commit 與 push 喔！");
                    break;
                default:
                    System.out.println("無此功能選項，請重新輸入。");
            }
        }
        scanner.close();
    }

    /**
     * 功能 1 & 2：解析原始的文字記錄並分配到平行陣列中
     */
    public static void parseRecords() {
        names = new String[records.length];
        prices = new int[records.length];
        stocks = new int[records.length];

        for (int i = 0; i < records.length; i++) {
            // 使用 split(",") 解析每筆資料
            String[] tokens = records[i].split(",");
            names[i] = tokens[0].trim();
            prices[i] = Integer.parseInt(tokens[1].trim());
            stocks[i] = Integer.parseInt(tokens[2].trim());
        }
    }

    /**
     * 功能 3：顯示解析後的商品表格
     */
    public static void displayProductTable() {
        System.out.println("\n-------------------------------------------");
        System.out.printf("%-15s \t %-10s \t %-10s\n", "商品名稱", "價格", "庫存");
        System.out.println("-------------------------------------------");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-15s \t $%-10d \t %-10d\n", names[i], prices[i], stocks[i]);
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * 功能 4：支援完整名稱與部分名稱搜尋 (不區分大小寫)
     */
    public static void searchProduct(String keyword) {
        boolean found = false;
        System.out.println("\n【搜尋結果】:");
        for (int i = 0; i < names.length; i++) {
            // 完整相符，或是部分包含 (皆轉為小寫達成不區分大小寫)
            if (names[i].equalsIgnoreCase(keyword.trim()) || 
                names[i].toLowerCase().contains(keyword.toLowerCase().trim())) {
                System.out.printf("找到商品 -> 名稱: %s, 價格: $%d, 庫存: %d\n", names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到任何符合「" + keyword + "」的商品。");
        }
    }

    /**
     * 功能 5：顯示低庫存商品
     */
    public static void displayLowStockProducts(int threshold) {
        System.out.println("\n【低庫存商品警示 (庫存 <= " + threshold + ")】:");
        boolean hasLowStock = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] <= threshold) {
                System.out.printf("警告!! 商品: %-12s | 目前庫存: %d\n", names[i], stocks[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("非常好！目前所有商品庫存充足。");
        }
    }

    /**
     * 功能 6：顯示庫存總價值
     */
    public static void displayTotalValue() {
        long totalValue = 0;
        for (int i = 0; i < names.length; i++) {
            totalValue += (long) prices[i] * stocks[i];
        }
        System.out.println("\n當前所有商品的庫存總價值為: $" + totalValue);
    }

    /**
     * 功能 7 & 8：允許輸入新文字資料、驗證格式、錯誤攔截且不中止程式
     */
    public static void addNewRecord(String inputStr) {
        try {
            // 驗證格式 1：是否含有逗號
            if (!inputStr.contains(",")) {
                throw new IllegalArgumentException("格式錯誤：輸入字串必須以逗號 ',' 分隔欄位！");
            }

            String[] tokens = inputStr.split(",");
            
            // 驗證格式 2：欄位數量是否正好為 3 個 (名稱, 價格, 庫存)
            if (tokens.length != 3) {
                throw new IllegalArgumentException("格式錯誤：欄位數量不正確，應包含(名稱,價格,庫存)共3個欄位。");
            }

            // 驗證格式 3：嘗試轉換數字，若失敗會拋出 NumberFormatException
            String newName = tokens[0].trim();
            if (newName.isEmpty()) {
                throw new IllegalArgumentException("格式錯誤：商品名稱不能為空字串！");
            }
            int newPrice = Integer.parseInt(tokens[1].trim());
            int newStock = Integer.parseInt(tokens[2].trim());

            if (newPrice < 0 || newStock < 0) {
                throw new IllegalArgumentException("資料錯誤：價格與庫存不能為負數！");
            }

            // 驗證全數通過，動態擴增原始 records 陣列
            String[] newRecords = new String[records.length + 1];
            System.arraycopy(records, 0, newRecords, 0, records.length);
            newRecords[records.length] = inputStr;
            records = newRecords;

            // 重新解析平行陣列
            parseRecords();
            System.out.println("成功！新商品「" + newName + "」已成功加入系統。");

        } catch (NumberFormatException e) {
            // 捕捉數字轉換錯誤
            System.out.println("\n[錯誤攔截] 數字轉換失敗！價格或庫存必須為「整數」數字。原因: " + e.getMessage());
            System.out.println("-> 提示：程式未中止，請重新選擇功能並輸入正確格式。");
        } catch (IllegalArgumentException e) {
            // 捕捉格式或自訂商務邏輯錯誤
            System.out.println("\n[錯誤攔截] " + e.getMessage());
            System.out.println("-> 提示：程式未中止，請重新選擇功能並輸入正確格式。");
        }
    }
}