import java.util.Scanner;
public class AgeCheck {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Your Age:");
        int age = sc.nextInt();
        boolean pass = age >= 18;

        System.out.println("Age:" + age);
        System.out.println("Adult:" + pass);
        sc.close();
    }
}
