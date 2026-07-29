public class TransactionSortingSystem {

    // 依金額降冪排序；金額相同時，依時間序號升冪排序
    public static void sortTransactions(Transaction[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (shouldSwap(arr[j], arr[j + 1])) {
                    Transaction temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static boolean shouldSwap(Transaction t1, Transaction t2) {
        // 金額小者往後排 (降冪)
        if (t1.getAmount() < t2.getAmount()) {
            return true;
        } 
        // 金額相同，時間序號大者往後排 (升冪)
        else if (t1.getAmount() == t2.getAmount()) {
            return t1.getTimeSeq() > t2.getTimeSeq();
        }
        return false;
    }

    public static void main(String[] args) {
        // 包含相同金額與不同時間序號的測試資料
        Transaction[] transactions = {
            new Transaction("TX101", "ACC-001", 5000.0, 3),
            new Transaction("TX102", "ACC-002", 12000.0, 1),
            new Transaction("TX103", "ACC-003", 5000.0, 1),  // 與 TX101 金額相同，序號較早
            new Transaction("TX104", "ACC-001", 25000.0, 2),
            new Transaction("TX105", "ACC-004", 5000.0, 2)   // 與 TX101, TX103 金額相同
        };

        System.out.println("=== 排序前交易紀錄 ===");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        sortTransactions(transactions);

        System.out.println("\n=== 排序後交易紀錄 (金額降冪，相同金額依時間序號升冪) ===");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
