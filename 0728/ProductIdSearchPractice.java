import java.util.Scanner;

public class ProductIdSearchPractice {

    public static void main(String[] args) {
        // 1. 建立至少 8 筆未排序商品編號
        int[] productIds = {105, 302, 101, 888, 405, 203, 999, 512};

        // 5. 測試第一筆、最後一筆及不存在的編號
        System.out.println("【自動測試邊界案例】");
        testSearch(productIds, 105); // 第一筆
        testSearch(productIds, 512); // 最後一筆
        testSearch(productIds, 777); // 不存在

        System.out.println("\n--------------------------------");
        // 2. 由鍵盤輸入要搜尋的編號
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的商品編號: ");
        if (scanner.hasNextInt()) {
            int target = scanner.nextInt();
            testSearch(productIds, target);
        }
        scanner.close();
    }

    public static void testSearch(int[] productIds, int target) {
        int index = -1;
        int compareCount = 0;

        // 循序搜尋 (Sequential Search)
        for (int i = 0; i < productIds.length; i++) {
            compareCount++; // 4. 額外顯示實際比較次數
            if (productIds[i] == target) {
                index = i;
                break;
            }
        }

        System.out.println("\n搜尋目標: " + target);
        // 3. 找到時顯示索引，找不到時顯示明確訊息
        if (index != -1) {
            System.out.println("結果：找到商品，索引值 (Index) 為: " + index);
        } else {
            System.out.println("結果：找不到該商品編號（回傳 " + index + "）");
        }
        System.out.println("實際比較次數: " + compareCount + " 次");
    }
}