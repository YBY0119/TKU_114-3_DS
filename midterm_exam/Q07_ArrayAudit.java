public class Q07_ArrayAudit {
    private static final int MIN_VALID = 10;
    private static final int MAX_VALID = 60;
    private static final int TARGET = 35;
    private static final int INVALID_MARK = -1;

    public static void main(String[] args) {
        int[] readings = {12, 71, 35, -4, 35, 22, 60, 9, 48, 61};

        System.out.println("有效筆數 : " + countValid(readings));
        System.out.printf("有效平均 : %.2f%n", averageValid(readings));
        System.out.println("最後符合門檻的索引 : " + findLastAtLeast(readings, TARGET));

        int[] cleaned = createCleanCopy(readings);
        System.out.print("清理後資料 : ");
        printArray(cleaned);
        System.out.print("原始資料 : ");
        printArray(readings);
    }

    private static boolean isValid(int value) {
        return value >= MIN_VALID && value <= MAX_VALID;
    }

    // 1. 計算有效筆數
    public static int countValid(int[] data) {
        if (data == null) {
            return 0;
        }
        int count = 0;
        for (int val : data) {
            if (isValid(val)) {
                count++;
            }
        }
        return count;
    }

    // 2. 計算有效均值
    public static double averageValid(int[] data) {
        if (data == null || data.length == 0) {
            return -1.0;
        }
        
        double sum = 0;
        int count = 0;
        
        for (int val : data) {
            if (isValid(val)) {
                sum += val;
                count++;
            }
        }
        
        // 邊界防禦：若無任何有效資料，回傳 -1.0 (避免除以 0)
        if (count == 0) {
            return -1.0;
        }
        
        return sum / count;
    }

    // 3. 從後往前搜尋符合門檻的最末有效資料之索引
    public static int findLastAtLeast(int[] data, int target) {
        if (data == null) {
            return -1;
        }
        
        // 從後往前（由 data.length - 1 開始）遞減搜尋
        for (int i = data.length - 1; i >= 0; i--) {
            if (isValid(data[i]) && data[i] >= target) {
                return i;
            }
        }
        return -1; // 找不到符合條件的元素
    }

    // 4. 建立清理過的新陣列複本 (不影響原陣列)
    public static int[] createCleanCopy(int[] data) {
        if (data == null) {
            return new int[0];
        }
        
        // 建立一個新陣列，不破壞原陣列
        int[] cleanCopy = new int[data.length];
        
        for (int i = 0; i < data.length; i++) {
            if (isValid(data[i])) {
                cleanCopy[i] = data[i];
            } else {
                cleanCopy[i] = INVALID_MARK; // 無效資料改為 -1
            }
        }
        return cleanCopy;
    }

    private static void printArray(int[] data) {
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}