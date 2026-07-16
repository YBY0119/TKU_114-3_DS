import java.util.Scanner;

public class SalesMatrix {

    // 定義常數，代表 3 項商品與 4 天銷售量
    private static final int PRODUCTS = 3;
    private static final int DAYS = 4;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 宣告 3 列 4 欄的二維陣列
        int[][] sales = new int[PRODUCTS][DAYS];

        System.out.println("=== 銷售矩陣報表系統 ===");

        // 1. 使用方法輸入每一格銷售量（含防呆驗證）
        inputSales(sc, sales);

        // 2. 以表格形式顯示完整資料與各項統計
        displayReport(sales);

        sc.close();
    }

    /**
     * 讓使用者逐筆輸入銷售量，並驗證數值不得小於 0
     */
    public static void inputSales(Scanner sc, int[][] sales) {
        System.out.println("\n請輸入每項商品在各天別的銷售量（數值不得小於 0）：");
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                while (true) {
                    System.out.print("商品 " + (i + 1) + " - 第 " + (j + 1) + " 天銷售量: ");
                    if (sc.hasNextInt()) {
                        int val = sc.nextInt();
                        if (val >= 0) {
                            sales[i][j] = val;
                            break; // 輸入正確，跳出內層迴圈
                        } else {
                            System.out.println("錯誤：銷售量不得小於 0，請重新輸入。");
                        }
                    } else {
                        System.out.println("錯誤：請輸入有效的整數！");
                        sc.next(); // 消耗無效輸入
                    }
                }
            }
            System.out.println(); // 每項商品輸入完後換行，利於閱讀
        }
    }

    /**
     * 以表格形式顯示完整資料，並印出各項統計結果（商品總量、每日總量、銷售最高商品）
     */
    public static void displayReport(int[][] sales) {
        System.out.println("==================== 銷售報表 ====================");
        
        // 表頭
        System.out.printf("%-8s\t%-6s\t%-6s\t%-6s\t%-6s\t%-8s\n", 
                          "商品/天數", "Day 1", "Day 2", "Day 3", "Day 4", "商品總銷售量");
        System.out.println("--------------------------------------------------");

        // 儲存每項商品總銷售量的陣列
        int[] productTotals = new int[PRODUCTS];
        // 儲存每日全部商品銷售總量的陣列
        int[] dayTotals = new int[DAYS];

        // 3. 計算每項商品的銷售總量 & 走訪二維陣列 (確保列、欄不顛倒)
        for (int i = 0; i < sales.length; i++) {
            int prodSum = 0;
            System.out.printf("商品 %-4d\t", (i + 1));
            for (int j = 0; j < sales[i].length; j++) {
                System.out.printf("%-6d\t", sales[i][j]);
                prodSum += sales[i][j];
                dayTotals[j] += sales[i][j]; // 累加每日總量
            }
            productTotals[i] = prodSum;
            System.out.printf("[ %-6d ]\n", prodSum); // 印出該列商品總量
        }

        System.out.println("--------------------------------------------------");
        
        // 4. 印出每日全部商品的銷售總量
        System.out.printf("%-8s\t", "每日總和");
        for (int j = 0; j < DAYS; j++) {
            System.out.printf("%-6d\t", dayTotals[j]);
        }
        System.out.println("\n==================================================");

        // 5. 找出總銷售量最高的商品並顯示
        findBestProduct(productTotals);
    }

    /**
     * 找出總銷售量最高的商品並印出結果
     */
    public static void findBestProduct(int[] productTotals) {
        int maxIndex = 0;
        int maxSales = productTotals[0];

        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > maxSales) {
                maxSales = productTotals[i];
                maxIndex = i;
            }
        }

        System.out.println("\n[ 統計分析結果 ]");
        System.out.println("-> 總銷售量最高的商品為：商品 " + (maxIndex + 1) + " (累積銷售量: " + maxSales + ")");
    }
}
