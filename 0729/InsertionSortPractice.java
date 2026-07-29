import java.util.Arrays;

public class InsertionSortPractice {

    public static void insertionSortWithTracking(int[] arr, String label) {
        System.out.println("========================================");
        System.out.println("測試資料組 [" + label + "]: " + Arrays.toString(arr));
        System.out.println("----------------------------------------");

        int comparisons = 0;
        int shifts = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i]; // 當前要插入的 key
            int j = i - 1;

            System.out.println("第 " + i + " 輪 (key = " + key + "):");

            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j]; // 元素右移
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key; // 插入位置 (j + 1)
            System.out.println("  - key 插入於位置 (index): " + (j + 1));
            System.out.println("  - 當前陣列內容: " + Arrays.toString(arr));
        }

        System.out.println("----------------------------------------");
        System.out.println("總比較次數: " + comparisons);
        System.out.println("元素右移次數: " + shifts);
        System.out.println("========================================\n");
    }

    public static void main(String[] args) {
        // 1. 指定測試資料
        int[] arr1 = {30, 10, 20, 50, 40, 5};
        insertionSortWithTracking(arr1, "原始題目資料");

        // 2. 已排序資料
        int[] arr2 = {5, 10, 20, 30, 40, 50};
        insertionSortWithTracking(arr2, "已排序資料");

        // 3. 反向排序資料
        int[] arr3 = {50, 40, 30, 20, 10, 5};
        insertionSortWithTracking(arr3, "反向排序資料");

        // 4. 分析與解答說明
        System.out.println("【觀察與說明】");
        System.out.println("移動次數最多的是：「反向排序資料」。");
        System.out.println("原因：在反向排序的情況下（最差情況 Worst-Case），每一輪的 key 都比左邊已排序區的所有元素還小，");
        System.out.println("因此左邊的每一個元素都必須向右移動一格，導致移動次數達到最大值 N*(N-1)/2 次。");
    }
}