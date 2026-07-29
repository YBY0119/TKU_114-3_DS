import java.util.Arrays;

public class SortingDebugReport {

    // ==========================================
    // 錯誤 1：內層範圍錯誤 (Inner Loop Bound Error)
    // ==========================================
    // 錯誤原因：內層迴圈邊界條件漏掉了 -1，導致 j + 1 超出陣列索引界限 (ArrayIndexOutOfBoundsException)。
    public static void bubbleSortBug1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i; j++) { // BUG: 應為 j < n - 1 - i
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 修正版本 1
    public static void bubbleSortFixed1(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // ==========================================
    // 錯誤 2：未保存 Key 值 (Missing Key Backup)
    // ==========================================
    // 錯誤原因：Insertion Sort 在移動元素時直接覆蓋了 arr[i]，未預先將 arr[i] 備份至 key 變數，導致原始數值遺失。
    public static void insertionSortBug2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            // BUG: 缺少 int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > arr[i]) { // BUG: 直接拿被覆蓋的 arr[i] 比較
                arr[j + 1] = arr[j];
                j--;
            }
            // arr[j + 1] = key; 覆蓋無法還原
        }
    }

    // 修正版本 2
    public static void insertionSortFixed2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // 正確保存 Key
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // ==========================================
    // 錯誤 3：比較方向錯誤 (Wrong Comparison Direction)
    // ==========================================
    // 錯誤原因：本意要進行升冪排序，但比較運算子誤寫成 `<`，導致結果變成降冪排序。
    public static void selectionSortBug3(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[minIdx]) { // BUG: 欲升冪卻寫成 > (變成找最大值)
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    // 修正版本 3
    public static void selectionSortFixed3(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) { // 正確：找最小值
                    minIdx = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;
        }
    }

    // ==========================================
    // 主程式：測試與比較結果
    // ==========================================
    public static void main(String[] args) {
        System.out.println("=== 排序程式除錯報告測試 ===\n");

        // 測試 1：內層範圍錯誤
        System.out.println("--- 測試 1: 內層範圍錯誤 ---");
        int[] test1 = {5, 3, 8, 1, 2};
        System.out.println("原始資料: " + Arrays.toString(test1));
        try {
            bubbleSortBug1(test1);
        } catch (Exception e) {
            System.out.println("修正前執行結果: 發生例外狀況 -> " + e);
        }
        int[] test1Fixed = {5, 3, 8, 1, 2};
        bubbleSortFixed1(test1Fixed);
        System.out.println("修正後執行結果: " + Arrays.toString(test1Fixed) + "\n");

        // 測試 2：未保存 Key 值
        System.out.println("--- 測試 2: 未保存 Key 值 ---");
        int[] test2 = {7, 2, 4, 1, 5};
        System.out.println("原始資料: " + Arrays.toString(test2));
        int[] test2Bug = Arrays.copyOf(test2, test2.length);
        insertionSortBug2(test2Bug);
        System.out.println("修正前執行結果 (數值毀損): " + Arrays.toString(test2Bug));
        int[] test2Fixed = Arrays.copyOf(test2, test2.length);
        insertionSortFixed2(test2Fixed);
        System.out.println("修正後執行結果 (正確排序): " + Arrays.toString(test2Fixed) + "\n");

        // 測試 3：比較方向錯誤
        System.out.println("--- 測試 3: 比較方向錯誤 ---");
        int[] test3 = {12, 4, 7, 2, 9};
        System.out.println("原始資料: " + Arrays.toString(test3));
        int[] test3Bug = Arrays.copyOf(test3, test3.length);
        selectionSortBug3(test3Bug);
        System.out.println("修正前執行結果 (誤變降冪): " + Arrays.toString(test3Bug));
        int[] test3Fixed = Arrays.copyOf(test3, test3.length);
        selectionSortFixed3(test3Fixed);
        System.out.println("修正後執行結果 (正確升冪): " + Arrays.toString(test3Fixed));
    }
}