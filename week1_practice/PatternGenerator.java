import java.util.Scanner;

public class PatternGenerator {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            // 1. 可重複顯示選單
            printMenu();
            System.out.print("請輸入選項: ");
            
            if (!sc.hasNextInt()) {
                System.out.println("輸入錯誤！請輸入 0 到 4 的整數。");
                sc.next(); // 清除錯誤輸入
                continue;
            }
            int option = sc.nextInt();

            // 選擇 0 離開系統
            if (option == 0) {
                System.out.println("系統已結束，謝謝使用！");
                break;
            }

            // 2. 使用 switch 判斷選項
            switch (option) {
                case 1:
                    // 輸出完整九九乘法表
                    printMultiplicationTable();
                    break;

                case 2:
                    // 正三角形：輸入高度並驗證
                    int height = readPositiveInt(sc, "請輸入正三角形高度: ");
                    printTriangle(height);
                    break;

                case 3:
                    // 倒三角形：輸入高度並驗證
                    int reverseHeight = readPositiveInt(sc, "請輸入倒三角形高度: ");
                    printReverseTriangle(reverseHeight);
                    break;

                case 4:
                    // 數字方格：輸入列數與欄數並驗證
                    int rows = readPositiveInt(sc, "請輸入方格列數 (rows): ");
                    int cols = readPositiveInt(sc, "請輸入方格欄數 (cols): ");
                    printNumberGrid(rows, cols);
                    break;

                default:
                    System.out.println("無此選項，請重新輸入。");
                    break;
            }
        }

        sc.close();
    }

    /**
     * 1. 印出圖形產生器主選單
     */
    public static void printMenu() {
        System.out.println("\n=== 圖形與表格產生器選單 ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
    }

    /**
     * 2. 讀取並驗證大於 0 的整數（高度、列數、欄數防呆）
     */
    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0) {
                    break; // 數值大於 0，合法跳出
                }
            } else {
                sc.next(); // 清除錯誤輸入
            }
            System.out.println("輸入不合法，數值必須大於 0，請重新輸入。");
        }
        return value;
    }

    /**
     * 3. 輸出完整的九九乘法表 (1x1 到 9x9)
     */
    public static void printMultiplicationTable() {
        System.out.println("\n--- 九九乘法表 ---");
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                // 使用 \t 確保排版對齊
                System.out.print(j + "x" + i + "=" + (i * j) + "\t");
            }
            System.out.println(); // 換行
        }
    }

    /**
     * 4. 輸出正三角形星號
     */
    public static void printTriangle(int height) {
        System.out.println("\n--- 正三角形 (高度: " + height + ") ---");
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 5. 輸出倒三角形星號
     */
    public static void printReverseTriangle(int height) {
        System.out.println("\n--- 倒三角形 (高度: " + height + ") ---");
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /**
     * 6. 輸出數字方格（每列輸出 1 到 cols，總共 rows 列）
     */
    public static void printNumberGrid(int rows, int cols) {
        System.out.println("\n--- 數字方格 (" + rows + " 列 " + cols + " 欄) ---");
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}