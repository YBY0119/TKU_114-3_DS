import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[]args){
Scanner sc = new Scanner(System.in);

System.out.print("請輸入姓名:");
String name = sc.nextLine();

System.out.print("請輸入 Java 成績: ");
int javaScore = sc.nextInt();

System.out.print("請輸入 English 成績: ");
int EnglishScore = sc.nextInt();

System.out.print("請輸入 Math 成績: ");
int MathScore = sc.nextInt();

double average = (javaScore + EnglishScore + MathScore)/3;

String status;
if(average >= 60 ){
    status = "及格";
}else{
    status = "不及格";
}

 char grade;
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80 && average <= 89) {
            grade = 'B';
        } else if (average >= 70 && average <= 79) {
            grade = 'C';
        } else if (average >= 60 && average <= 69) {
            grade = 'D';
        } else {
            grade = 'F';
        }

System.out.println("=== 成績報表 ===");
System.out.println("姓名: " + name);
System.out.println("Java: " + javaScore);
System.out.println("English: " + EnglishScore);
System.out.println("Math: " + MathScore);
System.out.println("平均: " + average);

 int choice = -1; 
        while (choice != 0) {
            System.out.println("--- 功能選單 ---");
            System.out.println("1 : 顯示平均分數");
            System.out.println("2 : 顯示及格狀態");
            System.out.println("3 : 顯示等第");
            System.out.println("0 : 離開");
            System.out.print("請選擇操作項目: ");
            choice = sc.nextInt();
            
switch (choice) {
                case 1:
                    // 格式化輸出平均分數，保留小數點後兩位
                    System.out.printf("平均分數為:", average);
                    break;
                case 2:
                    System.out.println("及格狀態: " + status + "\n");
                    break;
                case 3:
                    System.out.println("綜合等第: " + grade + "\n");
                    break;
                case 0:
                    System.out.println("程式已離開，謝謝使用！");
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。\n");
                    break;
            }
        }
sc.close();
}
}
