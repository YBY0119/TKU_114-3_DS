import java.util.Scanner;
public class TemperatureLevel {
 public static void main(String[]args){
   Scanner sc = new Scanner(System.in);
   
   System.out.print("Temperature:");
        int temperature = sc.nextInt(); 
       if (temperature < 15) {
            System.out.println("Cold");
        } else if (temperature < 28 && temperature >=15) {
            System.out.println("Comfortable");
        } else{
            System.out.println("Hot");
        } 
        sc.close();
 }   
}
