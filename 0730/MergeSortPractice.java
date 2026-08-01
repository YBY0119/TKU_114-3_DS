import java.util.Arrays;

public class MergeSortPractice {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        // 顯示拆分範圍
        System.out.printf("拆分範圍: [%d ... %d] -> 左 [%d ... %d], 右 [%d ... %d]\n",
                left, right, left, mid, mid + 1, right);

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);

        // 顯示合併後區間內容
        System.out.printf("合併完成 [%d ... %d]: %s\n",
                left, right, getSubArrayString(arr, left, right));
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    private static String getSubArrayString(int[] arr, int start, int end) {
        int[] sub = Arrays.copyOfRange(arr, start, end + 1);
        return Arrays.toString(sub);
    }

    public static void testAndRun(String title, int[] data) {
        System.out.println("========================================");
        System.out.println("測試項目: " + title);
        System.out.println("原始陣列: " + Arrays.toString(data));
        if (data != null && data.length > 0) {
            mergeSort(data, 0, data.length - 1);
        } else {
            System.out.println("空陣列，無需排序。");
        }
        System.out.println("排序結果: " + Arrays.toString(data));
    }

    public static void main(String[] args) {
        // 題目指定測試
        testAndRun("題目指定資料", new int[]{41, 12, 35, 8, 27, 19, 50, 3});
        
        // 邊界條件測試
        testAndRun("空陣列測試", new int[]{});
        testAndRun("單筆資料測試", new int[]{42});
        testAndRun("已排序資料測試", new int[]{1, 2, 3, 4, 5});
        testAndRun("反向資料測試", new int[]{9, 7, 5, 3, 1});
    }
}