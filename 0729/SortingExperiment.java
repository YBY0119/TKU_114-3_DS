import java.util.Arrays;

public class SortingExperiment {

    // 統計計數器類別
    static class Stats {
        long comparisons = 0;
        long swaps = 0;
        long moves = 0;

        void reset() {
            comparisons = 0;
            swaps = 0;
            moves = 0;
        }

        @Override
        public String toString() {
            return String.format("比較次數: %-4d | 交換次數: %-4d | 移動次數: %-4d", comparisons, swaps, moves);
        }
    }

    // Selection Sort 實作與統計
    public static void selectionSort(int[] arr, Stats stats) {
        stats.reset();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                stats.comparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
                stats.swaps++;
                stats.moves += 3; // 1次交換包含3次元素移動
            }
        }
    }

    // Insertion Sort 實作與統計
    public static void insertionSort(int[] arr, Stats stats) {
        stats.reset();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            stats.moves++; // 將 key 移出
            int j = i - 1;

            while (j >= 0) {
                stats.comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    stats.moves++; // 元素向右移
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
            stats.moves++; // 將 key 放回
        }
    }

    public static void main(String[] args) {
        int[] sortedData = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] reversedData = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] randomData = {5, 2, 9, 1, 7, 6, 3, 10, 4, 8};

        Stats stats = new Stats();

        System.out.println("===== 排序演算法操作統計實驗 =====\n");

        runExperiment("已排序資料 (Sorted)", sortedData, stats);
        runExperiment("反向排序資料 (Reversed)", reversedData, stats);
        runExperiment("隨機排列資料 (Random)", randomData, stats);

        System.out.println("\n【觀察結論】");
        System.out.println("1. 已排序資料：Insertion Sort 的比較與移動次數極少 (O(n))，效率遠高於 Selection Sort (O(n^2))。");
        System.out.println("2. 反向排序資料：Insertion Sort 需做大量移動，而 Selection Sort 的交換次數始終維持在 O(n)。");
        System.out.println("3. 隨機資料：Selection Sort 的交換次數顯著低於 Insertion Sort，但兩者比較次數相當。");
    }

    private static void runExperiment(String label, int[] original, Stats stats) {
        System.out.println("--- " + label + " ---");

        // 使用副本測試 Selection Sort
        int[] dataForSelection = Arrays.copyOf(original, original.length);
        selectionSort(dataForSelection, stats);
        System.out.printf("[Selection Sort] %s%n", stats);

        // 使用副本測試 Insertion Sort
        int[] dataForInsertion = Arrays.copyOf(original, original.length);
        insertionSort(dataForInsertion, stats);
        System.out.printf("[Insertion Sort] %s%n", stats);
        System.out.println();
    }
}