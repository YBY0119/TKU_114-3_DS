import java.util.Scanner;

public class GradeStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;
        int total = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int passCount = 0;
        int failCount = 0;

        while (true) {
            System.out.print("請輸入成績（輸入 -1 結束）：");
            
            // 防呆驗證：確保使用者輸入的是整數
            if (!sc.hasNextInt()) {
                System.out.println("輸入不合法，請輸入 0 到 100 的整數，或輸入 -1 結束。");
                sc.next(); // 清除迴圈中錯誤的輸入
                continue;
            }

            int score = sc.nextInt();

            // 輸入 -1 則跳出迴圈，結束輸入
            if (score == -1) {
                break;
            }

            // 呼叫 isValidScore 方法檢查成績是否在 0 ~ 100 之間
            if (!isValidScore(score)) {
                System.out.println("成績超出範圍，必須在 0 到 100 之間，請重新輸入。");
                continue;
            }

            // 統計數據累加
            count++;
            total += score;

            // 比較並更新最高分與最低分
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }

            // 呼叫 isPassing 方法統計及格與不及格人數
            if (isPassing(score)) {
                passCount++;
            } else {
                failCount++;
            }
        }

        // 如果使用者一開始就輸入 -1，印出 No scores entered
        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            // 計算平均分數（轉成 double 避免整數除法失去小數點）
            double average = (double) total / count;
            
            // 呼叫 getGrade 方法取得平均分數的等第 (A, B, C, D, F)
            String grade = getGrade(average);
            
            // 呼叫 printSummary 方法印出最終統計結果報表
            printSummary(count, total, average, max, min, passCount, failCount, grade);
        }

        sc.close();
    }

    /**
     * 1. 驗證成績是否在 0 到 100 的合法範圍內
     */
    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    /**
     * 2. 判斷分數是否及格（大於或等於 60 分）
     */
    public static boolean isPassing(int score) {
        return score >= 60;
    }

    /**
     * 3. 根據平均分數輸出對應的等第 (A, B, C, D, F)
     */
    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    /**
     * 4. 印出完整成績統計結果報表
     */
    public static void printSummary(int count, int total, double average, int max, int min, int passCount, int failCount, String grade) {
        System.out.println("\n=== Grade Summary ===");
        System.out.println("Count: " + count);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        System.out.println("Average grade: " + grade);
    }
}