import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("=== 動態成績管理系統 ===");
        System.out.println("請輸入成績 (0-100)，輸入 -1 結束輸入：");

        while (true) {
            System.out.print("輸入成績: ");
            if (scanner.hasNextInt()) {
                int score = scanner.nextInt();
                if (score == -1) {
                    break;
                }
                if (score >= 0 && score <= 100) {
                    scores.add(score);
                } else {
                    System.out.println("錯誤：成績必須介於 0 到 100 之間！");
                }
            } else {
                System.out.println("錯誤：請輸入有效的整數！");
                scanner.next(); // 清除無效輸入
            }
        }

        if (scores.isEmpty()) {
            System.out.println("\n未輸入任何有效的成績。");
        } else {
            printStatistics(scores);
        }

        scanner.close();
    }

    // 統計與篩選方法
    public static void printStatistics(ArrayList<Integer> scores) {
        int count = scores.size();
        int max = findMax(scores);
        int min = findMin(scores);
        double average = calculateAverage(scores);
        ArrayList<Integer> passingScores = filterPassingScores(scores);

        System.out.println("\n=== 統計結果 ===");
        System.out.println("總筆數: " + count);
        System.out.printf("平均分數: %.2f\n", average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("及格名單/成績 (>=60): " + passingScores);
    }

    public static double calculateAverage(ArrayList<Integer> scores) {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    }

    public static int findMax(ArrayList<Integer> scores) {
        int max = scores.get(0);
        for (int score : scores) {
            if (score > max) max = score;
        }
        return max;
    }

    public static int findMin(ArrayList<Integer> scores) {
        int min = scores.get(0);
        for (int score : scores) {
            if (score < min) min = score;
        }
        return min;
    }

    public static ArrayList<Integer> filterPassingScores(ArrayList<Integer> scores) {
        ArrayList<Integer> passing = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passing.add(score);
            }
        }
        return passing;
    }
}