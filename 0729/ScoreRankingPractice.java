import java.util.Arrays;

public class ScoreRankingPractice {

    public static void selectionSortDescending(int[] scores) {
        int n = scores.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (scores[j] > scores[maxIndex]) {
                    maxIndex = j;
                }
            }
            // 交換
            int temp = scores[i];
            scores[i] = scores[maxIndex];
            scores[maxIndex] = temp;
        }
    }

    public static void printRankings(int[] scores) {
        // 先進行降冪排序
        selectionSortDescending(scores);

        System.out.println("名次\t分數\t是否及格");
        System.out.println("------------------------");

        int rank = 1;
        for (int i = 0; i < scores.length; i++) {
            // 如果不是第一筆，且跟前一筆分數不同，名次直接更新為 (i + 1)
            if (i > 0 && scores[i] != scores[i - 1]) {
                rank = i + 1;
            }

            String isPassed = (scores[i] >= 60) ? "及格" : "不及格";
            System.out.println("第 " + rank + " 名\t" + scores[i] + " 分\t" + isPassed);
        }
    }

    public static void main(String[] args) {
        // 測試資料：至少 8 筆且包含相同分數
        int[] scores = {85, 92, 78, 92, 55, 60, 48, 85, 90};

        System.out.println("原始成績數據: " + Arrays.toString(scores));
        System.out.println("========================================");
        printRankings(scores);
    }
}