import java.util.Arrays;

public class SelectionSortPractice {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            System.out.println("陣列為空或只有單一元素，無需排序。");
            return;
        }

        int comparisons = 0;
        int swaps = 0;

        System.out.println("初始陣列: " + Arrays.toString(arr));
        System.out.println("----------------------------------------");

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            System.out.println("第 " + (i + 1) + " 輪 (start index = " + i + "):");

            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 進行交換
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

            System.out.println("  - 找到的最小值索引: " + minIndex + " (數值: " + arr[i] + ")");
            System.out.println("  - 當前陣列內容: " + Arrays.toString(arr));
        }

        System.out.println("----------------------------------------");
        System.out.println("排序完成!");
        System.out.println("總比較次數: " + comparisons);
        System.out.println("實際交換次數: " + swaps);
    }

    public static void main(String[] args) {
        // 1. 主要測試
        System.out.println("=== 1. 主要測試 {42, 18, 35, 7, 29, 14} ===");
        int[] arr1 = {42, 18, 35, 7, 29, 14};
        selectionSort(arr1);

        // 2. 測試空陣列
        System.out.println("\n=== 2. 測試空陣列 ===");
        int[] emptyArr = {};
        selectionSort(emptyArr);

        // 3. 測試單一元素陣列
        System.out.println("\n=== 3. 測試單一元素陣列 ===");
        int[] singleArr = {99};
        selectionSort(singleArr);
    }
}