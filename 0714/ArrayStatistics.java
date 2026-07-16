import java.util.Scanner;
import java.util.Arrays;

public class ArrayStatistics {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== 成績陣列統計系統 ===");

        // 1. 輸入資料筆數並驗證
        int count = readCount(sc);

        // 2. 建立對應長度的陣列
        int[] scores = new int[count];

        // 3. 逐筆輸入成績並驗證
        inputScores(sc, scores);

        System.out.println("\n--------------------------------");

        // 4. 顯示全部成績
        System.out.print("輸入的全部成績為: ");
        System.out.println(Arrays.toString(scores));

        // 5. 計算並顯示總分、平均、最高分、最低分
        int total = calculateTotal(scores);
        double average = (double) total / count;
        int max = findMax(scores);
        int min = findMin(scores);

        System.out.println("\n[ 成績統計結果 ]");
        System.out.println("總分: " + total + " 分");
        System.out.printf("平均: %.2f 分\n", average);
        System.out.println("最高分: " + max + " 分");
        System.out.println("最低分: " + min + " 分");

        // 6. 顯示及格與不及格人數
        int passCount = countPass(scores);
        int failCount = count - passCount;
        System.out.println("及格人數: " + passCount + " 人");
        System.out.println("不及格人數: " + failCount + " 人");

        System.out.println("--------------------------------");

        // 7. 輸入目標成績並尋找索引
        System.out.print("\n請輸入要搜尋的目標成績: ");
        while (!sc.hasNextInt()) {
            System.out.print("請輸入有效的整數成績: ");
            sc.next();
        }
        int target = sc.nextInt();

        int index = findIndex(scores, target);
        if (index != -1) {
            System.out.println("成績 " + target + " 第一次出現的索引位置（Index）為: " + index);
        } else {
            System.out.println("找不到成績為 " + target + " 的資料。");
        }

        sc.close();
        System.out.println("\n系統結束，謝謝使用！");
    }

    /**
     * 輸入資料筆數，範圍必須在 1 到 50 之間
     */
    public static int readCount(Scanner sc) {
        int count = 0;
        while (true) {
            System.out.print("請輸入要輸入的資料筆數 (1 ~ 50): ");
            if (sc.hasNextInt()) {
                count = sc.nextInt();
                if (count >= 1 && count <= 50) {
                    break;
                } else {
                    System.out.println("錯誤：筆數超出範圍！請重新輸入。");
                }
            } else {
                System.out.println("錯誤：請輸入整數數值！");
                sc.next(); // 消耗無效輸入
            }
        }
        return count;
    }

    /**
     * 逐筆輸入成績，範圍必須在 0 到 100 之間，錯誤時需重新輸入
     */
    public static void inputScores(Scanner sc, int[] scores) {
        System.out.println("\n請開始輸入每位學生的成績（範圍：0 ~ 100）:");
        for (int i = 0; i < scores.length; i++) {
            while (true) {
                System.out.print("第 " + (i + 1) + " 筆成績: ");
                if (sc.hasNextInt()) {
                    int score = sc.nextInt();
                    if (score >= 0 && score <= 100) {
                        scores[i] = score;
                        break; // 輸入正確，跳出此筆成績的 loop
                    } else {
                        System.out.println("錯誤：成績範圍必須在 0 到 100 之間！請重新輸入。");
                    }
                } else {
                    System.out.println("錯誤：請輸入整數數值！");
                    sc.next(); // 消耗無效輸入
                }
            }
        }
    }

    /**
     * 計算所有成績的總分
     */
    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

    /**
     * 尋找成績中的最高分
     */
    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    /**
     * 尋找成績中的最低分
     */
    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    /**
     * 計算及格人數（成績 >= 60）
     */
    public static int countPass(int[] scores) {
        int passCount = 0;
        for (int score : scores) {
            if (score >= 60) {
                passCount++;
            }
        }
        return passCount;
    }

    /**
     * 尋找目標成績第一次出現的索引，若找不到則回傳 -1
     */
    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i; // 找到即回傳目前索引
            }
        }
        return -1; // 找不到回傳 -1
    }
}