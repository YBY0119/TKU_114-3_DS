public static void searchAllOccurrences(int[] array, int target) {
    List<Integer> foundIndices = new ArrayList<>();
    int compareCount = 0;

    for (int i = 0; i < array.length; i++) {
        compareCount++; // 記錄比較次數
        if (array[i] == target) {
            foundIndices.add(i);
        }
    }

    System.out.println("\n搜尋目標值: " + target);
    // 4. 找不到時顯示明確訊息
    if (foundIndices.isEmpty()) {
        System.out.println("結果：找不到符合條件的資料！");
    } else {
        System.out.println("結果：在索引 " + foundIndices + " 找到目標");
    }
    System.out.println("出現總次數: " + foundIndices.size());
    System.out.println("實際比較次數: " + compareCount + " 次");
}

public static void main(String[] args) {
    System.out.println("=== 課後作業二：搜尋全部相同資料 ===");
    int[] data = {45, 12, 88, 12, 67, 12, 90, 33, 12};

    // 測試 1：存在多筆資料
    searchAllOccurrences(data, 12);

    // 測試 2：存在單筆資料
    searchAllOccurrences(data, 88);

    // 測試 3：找不到資料
    searchAllOccurrences(data, 999);
}