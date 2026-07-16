import java.util.Scanner;

public class Q02_ScannerInputFix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入數量:");
        int quantity = sc.nextInt();
        sc.nextLine(); // 【修正】吃掉數量後方殘留的換行字元

        System.out.print("請輸入課程名稱:");
        String courseName = sc.nextLine(); // 現在可以正常輸入包含空白的課程名稱了

        System.out.print("請輸入單價:");
        int price = sc.nextInt();
        sc.nextLine(); // 【修正】吃掉單價後方殘留的換行字元

        System.out.print("請輸入備註:");
        String note = sc.nextLine(); // 現在可以正常輸入包含空白的備註了

        int total = quantity * price;

        System.out.println("=== 登記結果 ===");
        System.out.println("課程:" + courseName);
        System.out.println("數量:" + quantity);
        System.out.println("總額:" + total);
        System.out.println("備註:" + note);

        sc.close();
    }
}