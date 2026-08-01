import java.util.*;

public class AlgorithmComparisonReport {

    public static long selectionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
        return comparisons;
    }

    public static long insertionSort(int[] arr) {
        long comparisons = 0;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return comparisons;
    }

    private static long mergeComparisons = 0;

    public static long mergeSort(int[] arr) {
        mergeComparisons = 0;
        if (arr == null || arr.length <= 1) return 0;
        runMergeSort(arr, 0, arr.length - 1);
        return mergeComparisons;
    }

    private static void runMergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        runMergeSort(arr, left, mid);
        runMergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            mergeComparisons++;
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        String[] types = {"已排序 (Sorted)", "反向 (Reversed)", "固定亂數 (Random)"};

        System.out.printf("%-10s | %-18s | %-15s | %-15s | %-15s\n", 
                "資料量 (N)", "資料型態", "Selection Sort", "Insertion Sort", "Merge Sort");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int n : sizes) {
            for (String type : types) {
                int[] baseData = generateData(n, type);

                int[] d1 = Arrays.copyOf(baseData, baseData.length);
                int[] d2 = Arrays.copyOf(baseData, baseData.length);
                int[] d3 = Arrays.copyOf(baseData, baseData.length);

                long selComp = selectionSort(d1);
                long insComp = insertionSort(d2);
                long merComp = mergeSort(d3);

                System.out.printf("%-12d | %-20s | %-15d | %-15d | %-15d\n", 
                        n, type, selComp, insComp, merComp);
            }
            System.out.println("-----------------------------------------------------------------------------------------");
        }

        System.out.println("\n=== 觀察與分析結論 ===");
        System.out.println("1. Selection Sort 比較次數只與資料量 N 有關，時間複雜度恆為 O(N^2)。");
        System.out.println("2. Insertion Sort 對『已排序資料』效率最佳（比較次數僅 N-1 次，O(N)），但對『反向資料』最差（O(N^2)）。");
        System.out.println("3. Merge Sort 比較次數穩定落在 O(N log N) 範圍，在極大資料量 (N=1024) 下遠優於 Selection/Insertion Sort。");
    }

    private static int[] generateData(int n, String type) {
        int[] arr = new int[n];
        if (type.startsWith("已排序")) {
            for (int i = 0; i < n; i++) arr[i] = i;
        } else if (type.startsWith("反向")) {
            for (int i = 0; i < n; i++) arr[i] = n - i;
        } else {
            Random rand = new Random(42);
            for (int i = 0; i < n; i++) arr[i] = rand.nextInt(10000);
        }
        return arr;
    }
}