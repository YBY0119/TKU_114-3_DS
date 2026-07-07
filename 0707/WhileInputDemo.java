import java.util.Scanner;
public class WhileInputDemo {
    public static void main(String[]args){
  Scanner sc = new Scanner(System.in);
  System.out.print("Enter a number : ");
  int number = sc.nextInt();
while (number != 0){

System.out.println("You entered: " + number);
System.out.println("請繼續輸入整數:");
            number = sc.nextInt();
        }
        
        System.out.println("偵測到輸入為 0,程式已結束.");
        sc.close(); // 關閉資源
}
    }


