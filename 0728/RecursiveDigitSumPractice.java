public class RecursiveDigitSumPractice {

    // 1. 建立 digitSum(int number) 方法
    // 2. 不使用字串轉換及迴圈
    public static int digitSum(int number) {
        // 完成標準：具有可到達的 base case (小於 10 時直接回傳該數字)
        if (number < 10) {
            return number;
        }
        // 每次呼叫都縮小數字：加上最後一位 (number % 10)，並將剩下的數字去除個位數 (number / 10)
        return (number % 10) + digitSum(number / 10);
    }

    public static void main(String[] args) {
        // 5. 在程式中測試至少 5 組資料 (包含 0 及正整數)
        int[] testCases = {5729, 0, 8, 12345, 9999};


        for (int test : testCases) {
            int result = digitSum(test);
            System.out.println("digitSum(" + test + ") = " + result);
        }
    }
}