public class RecursiveNameSearchPractice {

    // 1. 建立 search(String[] names, String target, int index)
    // 2. 不使用迴圈
    // 3. 字串使用 equals() 比較
    // 4. 找到時回傳索引，找不到時回傳 -1
    public static int search(String[] names, String target, int index) {
        // base case 1: 處理空陣列或已查找到陣列結尾 (找不到)
        if (names == null || index >= names.length) {
            return -1;
        }
        
        // base case 2: 找到目標字串
        if (names[index] != null && names[index].equals(target)) {
            return index;
        }

        // 遞迴呼叫下一筆索引
        return search(names, target, index + 1);
    }

    public static void main(String[] args) {
        String[] nameArray = {"Alice", "Bob", "Charlie", "David", "Eve"};
        String[] emptyArray = {};

        System.out.println("=== 課堂實作題四：遞迴版文字搜尋測試 ===");

        // 5. 測試空陣列、第一筆、最後一筆及不存在資料
        System.out.println("1. 測試空陣列 (搜尋 'Alice'): " 
                + search(emptyArray, "Alice", 0)); // 預期: -1

        System.out.println("2. 測試第一筆 (搜尋 'Alice'): " 
                + search(nameArray, "Alice", 0));  // 預期: 0

        System.out.println("3. 測試最後一筆 (搜尋 'Eve'): " 
                + search(nameArray, "Eve", 0));    // 預期: 4

        System.out.println("4. 測試不存在資料 (搜尋 'Frank'): " 
                + search(nameArray, "Frank", 0));  // 預期: -1
    }
}