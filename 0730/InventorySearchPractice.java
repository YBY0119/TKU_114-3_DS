import java.util.Arrays;

public class InventorySearchPractice {

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(arr, left, mid + 1);
        int[] R = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        while (i < L.length && j < R.length) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < L.length) arr[k++] = L[i++];
        while (j < R.length) arr[k++] = R[j++];
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1; // 找不到
    }

    public static void performSearch(int[] arr, int target) {
        int index = binarySearch(arr, target);
        if (index != -1) {
            System.out.printf("搜尋目標 [%d]: 成功找到，位於索引位置 %d\n", target, index);
        } else {
            System.out.printf("搜尋目標 [%d]: 未找到該資料 (索引: -1)\n", target);
        }
    }

    public static void main(String[] args) {
        // 1. 建立至少 12 筆未排序庫存編號
        int[] inventory = {1050, 1012, 1099, 1001, 1033, 1075, 1020, 1088, 1005, 1060, 1042, 1018};

        System.out.println("4. 顯示排序前:");
        System.out.println("   " + Arrays.toString(inventory));

        // 2. Merge Sort 依編號排序
        mergeSort(inventory, 0, inventory.length - 1);

        System.out.println("4. 顯示排序後:");
        System.out.println("   " + Arrays.toString(inventory));
        System.out.println("----------------------------------------");

        // 5. 測試邊界案例
        System.out.println("測試 1: 搜尋第一筆資料 (最小編號)");
        performSearch(inventory, inventory[0]);

        System.out.println("\n測試 2: 搜尋最後一筆資料 (最大編號)");
        performSearch(inventory, inventory[inventory.length - 1]);

        System.out.println("\n測試 3: 搜尋不存在的編號");
        performSearch(inventory, 9999);
    }
}