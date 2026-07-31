import java.util.Arrays;

public class RangeSearchSystem {

    // 2. 修改後的 Binary Search 找出目標第一次出現位置 (Find First)
    public static int findFirst(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                result = mid; // 記錄當前位置
                high = mid - 1; // 繼續往左半邊搜尋更早出現的位置
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // 3. 找出最後一次出現位置 (Find Last)
    public static int findLast(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                result = mid; // 記錄當前位置
                low = mid + 1; // 繼續往右半邊搜尋更晚出現的位置
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        // 5. 找不到時回傳 [-1, -1] 的結果
        if (first == -1) {
            return new int[]{-1, -1};
        }
        int last = findLast(nums, target);
        return new int[]{first, last};
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業五：第一筆與最後一筆位置 ===");

        // 1. 使用包含重複資料的已排序陣列
        int[] sortedData = {5, 7, 7, 8, 8, 8, 8, 10, 12, 12, 15};
        System.out.println("原始陣列: " + Arrays.toString(sortedData));

        // 測試 1: 搜尋存在且重複的資料 (目標: 8)
        testSearch(sortedData, 8);

        // 測試 2: 搜尋存在且單一的資料 (目標: 5)
        testSearch(sortedData, 5);

        // 測試 3: 搜尋不存在的資料 (目標: 6)
        testSearch(sortedData, 6);
    }

    public static void testSearch(int[] data, int target) {
        int[] range = searchRange(data, target);
        System.out.println("\n搜尋目標: " + target);
        System.out.println("回傳結果: " + Arrays.toString(range));

        // 4. 輸出目標值的索引範圍及出現次數
        if (range[0] != -1) {
            int count = range[1] - range[0] + 1;
            System.out.printf("索引範圍: [%d, %d]\n", range[0], range[1]);
            System.out.println("出現次數: " + count + " 次");
        } else {
            System.out.println("結果：目標不存在於陣列中。");
        }
    }
}