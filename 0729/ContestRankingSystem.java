public class ContestRankingSystem {

    // 實作 Insertion Sort
    // 規則：分數高者在前；分數相同時，完成秒數少者在前
    public static void insertionSort(Contestant[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Contestant key = arr[i];
            int j = i - 1;

            while (j >= 0 && shouldSwap(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 判斷 current 是否應該排在 key 的後面（若 current 較差則回傳 true）
    private static boolean shouldSwap(Contestant current, Contestant key) {
        if (current.getScore() < key.getScore()) {
            return true; // 分數較低，往後移
        } else if (current.getScore() == key.getScore()) {
            return current.getTimeInSeconds() > key.getTimeInSeconds(); // 分數相同，秒數較大者往後移
        }
        return false;
    }

    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C001", "張小明", 85, 45.2),
            new Contestant("C002", "李華", 95, 50.1),
            new Contestant("C003", "王大同", 85, 40.0), // 分數同 C001，秒數較少
            new Contestant("C004", "陳美玲", 100, 55.8),
            new Contestant("C005", "林阿生", 95, 48.5)  // 分數同 C002，秒數較少
        };

        System.out.println("=== 排序前參賽者名單 ===");
        for (Contestant c : contestants) {
            System.out.println(c);
        }

        insertionSort(contestants);

        System.out.println("\n=== 參賽者最終排名 ===");
        for (int i = 0; i < contestants.length; i++) {
            System.out.printf("第 %d 名 -> %s%n", (i + 1), contestants[i]);
        }
    }
}