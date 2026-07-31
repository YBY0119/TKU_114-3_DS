public class RecursiveDigitCounter {

    // 1. 建立 countDigit(int number, int target)
    // 2. 不使用字串轉換及迴圈
    // 3. target 必須介於 0 到 9
    public static int countDigit(int number, int target) {
        // 檢查 target 範圍
        if (target < 0 || target > 9) {
            throw new IllegalArgumentException("Target 必須介於 0 到 9 之間！");
        }

        // 處理負數情況
        if (number < 0) {
            number = Math.abs(number);
        }

        // Base case: 個位數時
        if (number < 10) {
            return (number == target) ? 1 : 0;
        }

        // 檢查當前最後一位是否符合
        int lastDigit = number % 10;
        int match = (lastDigit == target) ? 1 : 0;

        // 遞迴呼叫剩餘高位數
        return match + countDigit(number / 10, target);
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業一：遞迴統計數字出現次數 ===");

        // 5. 至少準備 6 組測試資料 (4. 正確處理重複數字及目標不存在)
        int[][] testCases = {
            {733231, 3},  // 重複數字：3 次
            {123456, 7},  // 目標不存在：0 次
            {0, 0},       // 邊界 0 測試
            {99999, 9},   // 全重複：5 次
            {808080, 0},  // 0 的重複：3 次
            {5, 5}        // 單一數字匹配：1 次
        };

        for (int i = 0; i < testCases.length; i++) {
            int num = testCases[i][0];
            int target = testCases[i][1];
            int count = countDigit(num, target);
            System.out.printf("測試 %d: 數字 = %-8d, 目標 = %d -> 出現次數 = %d\n", 
                              (i + 1), num, target, count);
        }
    }
}