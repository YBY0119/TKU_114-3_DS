public class SearchEfficiencyReport {

    // 循序搜尋 (傳回比較次數)
    public static int sequentialSearchCount(int[] arr, int target) {
        int count = 0;
        for (int val : arr) {
            count++;
            if (val == target) {
                break;
            }
        }
        return count;
    }

    // 二分搜尋 (傳回比較次數)
    public static int binarySearchCount(int[] arr, int target) {
        int count = 0;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            count++;
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                break;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // 1. 建立 16、128、1024 筆已排序資料
        int[] sizes = {16, 128, 1024};

        System.out.println("===============================================================");
        System.out.println("              課後作業四：搜尋效率比較測試報告                 ");
        System.out.println("===============================================================");

        for (int size : sizes) {
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = (i + 1) * 10; // 產生排序資料: 10, 20, 30...
            }

            // 2. 測試第一筆、最後一筆及不存在資料
            int first = data[0];
            int last = data[size - 1];
            int notExist = -999;

            System.out.printf("\n【資料筆數: %d】\n", size);
            System.out.printf("%-12s | %-15s | %-15s\n", "搜尋目標", "Sequential 次數", "Binary 次數");
            System.out.println("---------------------------------------------------------------");

            // 3. 顯示每次搜尋的比較次數 (不使用執行時間作為唯一依據)
            System.out.printf("%-12s | %-15d | %-15d\n", "第一筆 (" + first + ")", 
                              sequentialSearchCount(data, first), binarySearchCount(data, first));
            
            System.out.printf("%-12s | %-15d | %-15d\n", "最後一筆 (" + last + ")", 
                              sequentialSearchCount(data, last), binarySearchCount(data, last));
            
            System.out.printf("%-12s | %-15d | %-15d\n", "不存在 (" + notExist + ")", 
                              sequentialSearchCount(data, notExist), binarySearchCount(data, notExist));
        }

        // 5. 在程式輸出最後寫出觀察結果
        System.out.println("\n===============================================================");
        System.out.println("                        【觀察與分析結果】                      ");
        System.out.println("===============================================================");
        System.out.println("1. 循序搜尋 (Sequential Search):");
        System.out.println("   - 時間複雜度為 O(N)。搜尋第一筆時最快 (1次)，但當目標在最後一筆");
        System.out.println("     或不存在時，比較次數達到最大值 N (例如 1024 筆資料需比較 1024 次)。");
        System.out.println("\n2. 二分搜尋 (Binary Search):");
        System.out.println("   - 時間複雜度為 O(log N)。無論搜尋最後一筆或不存在的資料，");
        System.out.println("     比較次數都不會超過 ceil(log2(N)) + 1 次 (1024 筆資料最多僅需約 10~11 次)。");
        System.out.println("\n3. 總結:");
        System.out.println("   - 當資料量擴大時 (16 -> 128 -> 1024)，Sequential Search 的最差比較次數");
        System.out.println("     呈線性暴增；而 Binary Search 的比較次數成長極為緩和，展示出 O(log N)");
        System.out.println("     在處理大量已排序資料時的巨大效率優勢。");
        System.out.println("===============================================================");
    }
}