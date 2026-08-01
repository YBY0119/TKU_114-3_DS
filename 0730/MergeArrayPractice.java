import java.util.Arrays;

public class MergeArrayPractice {

    /**
     * 合併兩個已排序陣列，使用三個索引，且自動去重與處理空陣列
     */
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        if (arr1 == null) arr1 = new int[0];
        if (arr2 == null) arr2 = new int[0];

        int i = 0, j = 0, k = 0; // 三個索引
        int[] temp = new int[arr1.length + arr2.length];

        while (i < arr1.length && j < arr2.length) {
            int val;
            if (arr1[i] < arr2[j]) {
                val = arr1[i++];
            } else if (arr1[i] > arr2[j]) {
                val = arr2[j++];
            } else { // 兩者相同，取其一並雙雙遞增索引（去重）
                val = arr1[i];
                i++;
                j++;
            }

            // 避免在新陣列中加入重複元素
            if (k == 0 || temp[k - 1] != val) {
                temp[k++] = val;
            }
        }

        // 處理 arr1 剩餘元素
        while (i < arr1.length) {
            int val = arr1[i++];
            if (k == 0 || temp[k - 1] != val) {
                temp[k++] = val;
            }
        }

        // 處理 arr2 剩餘元素
        while (j < arr2.length) {
            int val = arr2[j++];
            if (k == 0 || temp[k - 1] != val) {
                temp[k++] = val;
            }
        }

        // 裁切成正確長度
        return Arrays.copyOf(temp, k);
    }

    public static void main(String[] args) {
        System.out.println("=== 測試 1: 正常測試 (含負數、重複值、不同長度) ===");
        int[] a1 = {-5, 1, 3, 5, 8, 8, 12};
        int[] a2 = {-3, 3, 7, 12, 15};
        int[] result1 = mergeSortedArrays(a1, a2);
        System.out.println("Arr1: " + Arrays.toString(a1));
        System.out.println("Arr2: " + Arrays.toString(a2));
        System.out.println("合併結果: " + Arrays.toString(result1));

        System.out.println("\n=== 測試 2: 其中一個陣列為空 ===");
        int[] a3 = {};
        int[] a4 = {-2, 0, 4};
        int[] result2 = mergeSortedArrays(a3, a4);
        System.out.println("Arr3: " + Arrays.toString(a3));
        System.out.println("Arr4: " + Arrays.toString(a4));
        System.out.println("合併結果: " + Arrays.toString(result2));
    }
}