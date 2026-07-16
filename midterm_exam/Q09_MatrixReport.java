public class Q09_MatrixReport {
    public static void main(String[] args) {
        // 完全保留原始題目的測試資料與輸出
        int[][] data = {
            {5, 8, 2},
            {9, 4, 7},
            {3, 6, 10}
        };

        System.out.println("第 1 列總和 : " + rowSum(data, 1));
        System.out.println("第 2 欄總和 : " + columnSum(data, 2));
        System.out.println("大於等於 7 的筆數 : " + countAtLeast(data, 7));
        System.out.println("總和最大的列索引 : " + findRowWithLargestTotal(data));
    }

    // 1. 計算指定列的總和，若列索引不合法時回傳 -1
    public static int rowSum(int[][] data, int row) {
        if (data == null || row < 0 || row >= data.length) {
            return -1;
        }
        
        int sum = 0;
        // 防禦：萬一該列本身為 null
        if (data[row] == null) {
            return -1;
        }
        for (int val : data[row]) {
            sum += val;
        }
        return sum;
    }

    // 2. 計算指定欄的總和，若欄索引不合法時回傳 -1
    public static int columnSum(int[][] data, int column) {
        if (data == null || data.length == 0 || column < 0) {
            return -1;
        }

        int sum = 0;
        boolean hasValidColumn = false;

        for (int i = 0; i < data.length; i++) {
            // 防禦：檢查該列是否存在，且該列的長度是否大於指定欄索引
            if (data[i] != null && column < data[i].length) {
                sum += data[i][column];
                hasValidColumn = true; // 只要有任何一列能成功提供該欄資料，此欄即為合法
            }
        }

        // 若所有列都沒有這個欄索引，代表此欄不合法，回傳 -1
        return hasValidColumn ? sum : -1;
    }

    // 3. 計算大於或等於門檻的資料筆數
    public static int countAtLeast(int[][] data, int target) {
        if (data == null) {
            return 0;
        }
        int count = 0;
        for (int[] row : data) {
            if (row != null) {
                for (int val : row) {
                    if (val >= target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // 4. 回傳總和最大的列索引。若多列總和相同，回傳索引較小的列。無資料回傳 -1。
    public static int findRowWithLargestTotal(int[][] data) {
        if (data == null || data.length == 0) {
            return -1;
        }

        int maxRowIndex = -1;
        int maxSum = Integer.MIN_VALUE;
        boolean hasData = false;

        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                hasData = true;
                int currentSum = 0;
                for (int val : data[i]) {
                    currentSum += val;
                }
                
                // 使用 > 確保在「平手」時不更新，進而保留索引較小的列
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    maxRowIndex = i;
                }
            }
        }

        return hasData ? maxRowIndex : -1;
    }
}