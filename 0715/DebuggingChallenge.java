import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        // 修正 1：將 <= 改為 <，避免 ArrayIndexOutOfBoundsException
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        // 修正 2：將 total 強制轉型成 double，避免整數除法自動捨去小數點
        double average = (double) total / scores.length;
        System.out.printf("平均: %.2f%n", average);

        System.out.print("請輸入年齡: ");
        int age = sc.nextInt();
        
        // 修正 4：清除緩衝區中殘留的換行符（Enter鍵）
        sc.nextLine(); 

        System.out.print("請輸入指令: ");
        String command = sc.nextLine();

        // 修正 5：字串物件內容比較應使用 .equals()，不可使用 ==
        if ("exit".equals(command)) {
            System.out.println("系統結束，年齡: " + age);
        }

        sc.close();
    } // 修正 3：確保此處 class 的結尾大括號對齊且閉合
}