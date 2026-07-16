public class Q08_ArrayRemove {
    public static void main(String[] args) {
        // 完全保留原始題目的測試輸出與資料
        int[] values = {4, 7, 2, 7, 9, 7, 1};
        int target = 7;

        System.out.println("出現次數 : " + countOccurrences(values, target));
        System.out.println("最後索引 : " + findLastIndex(values, target));

        int[] result = removeAll(values, target);
        System.out.print("移除後 : ");
        printArray(result);
        System.out.print("原始陣列 : ");
        printArray(values);
    }

    // 1. 回傳指定值出現的次數
    public static int countOccurrences(int[] data, int target) {
        if (data == null) {
            return 0;
        }
        int count = 0;
        for (int val : data) {
            if (val == target) {
                count++;
            }
        }
        return count;
    }

    // 2. 回傳指定值最後一次出現的索引，找不到回傳 -1
    public static int findLastIndex(int[] data, int target) {
        if (data == null) {
            return -1;
        }
        // 反向遞減遍歷
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 3. 建立並回傳不包含指定值的新陣列（長度需剛好足夠、不可修改原始陣列、不可使用 ArrayList）
    public static int[] removeAll(int[] data, int target) {
        if (data == null) {
            return new int[0];
        }

        // 計算移除目標後所需的精確長度
        int occurrences = countOccurrences(data, target);
        int[] result = new int[data.length - occurrences];

        int newIndex = 0;
        for (int val : data) {
            // 只保留不等於 target 的元素
            if (val != target) {
                result[newIndex] = val;
                newIndex++;
            }
        }

        return result;
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