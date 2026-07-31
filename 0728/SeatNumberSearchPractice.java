import java.util.Scanner;

public class SeatNumberSearchPractice {

    public static void main(String[] args) {
        // 1. 使用至少 12 筆已排序座位編號
        int[] seats = {101, 103, 105, 108, 112, 115, 120, 125, 130, 135, 140, 150};

        // 5. 測試第一筆、最後一筆、中間資料及不存在的資料
        System.out.println("=== 自動邊界測試 ===");
        binarySearchWithLog(seats, 101); // 第一筆
        binarySearchWithLog(seats, 150); // 最後一筆
        binarySearchWithLog(seats, 120); // 中間資料
        binarySearchWithLog(seats, 999); // 不存在的資料

        System.out.println("\n--------------------------------");
        // 2. 由鍵盤輸入座位編號
        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入要搜尋的座位編號: ");
        if (scanner.hasNextInt()) {
            int target = scanner.nextInt();
            binarySearchWithLog(seats, target);
        }
        scanner.close();
    }

    // 3. 使用 Binary Search 回傳索引，並印出每輪資訊
    public static int binarySearchWithLog(int[] seats, int target) {
        System.out.println("\n>>> 開始搜尋目標座位: " + target);
        int low = 0;
        int high = seats.length - 1;
        int round = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            
            // 4. 每一輪顯示 low, mid, high
            System.out.printf("第 %d 輪 -> low: %2d | mid: %2d | high: %2d (Seats[mid] = %d)\n", 
                              round++, low, mid, high, seats[mid]);

            if (seats[mid] == target) {
                System.out.println("成功找到！索引值為: " + mid);
                return mid;
            } else if (seats[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("搜尋結束，找不到該座位編號（回傳 -1）。");
        return -1;
    }
}