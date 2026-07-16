public class Q04_LoopRepair {
    public static void main(String[] args) {
        // 完全保留原始題目的測試輸出
        System.out.println(sumOddRange(3, 7));   // 預期：15 (3 + 5 + 7)
        System.out.println(sumOddRange(7, 3));   // 預期：15 (同上，反向區間)
        System.out.println(sumOddRange(2, 2));   // 預期：0  (2不是奇數)
        System.out.println(sumOddRange(-3, 3));  // 預期：0  (-3 + -1 + 1 + 3 = 0)
    }

    public static int sumOddRange(int start, int end) {
        int sum = 0;

        // 1. 處理反向區間：若 start 大於 end，將兩者數值對調 (Swap)
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }

        // 2. 迴圈控制：使用 <= 確保包含終點
        for (int i = start; i <= end; i++) {
            // 3. 判斷奇數：使用 != 0 確保能正確判定正奇數與負奇數
            if (i % 2 != 0) {
                sum += i;
            }
        }

        return sum;
    }
}
