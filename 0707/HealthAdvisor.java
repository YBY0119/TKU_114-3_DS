import java.util.Scanner;
public class HealthAdvisor{
    public static void main(String[]args){
Scanner sc = new Scanner(System.in);

while(true){
System.out.print("請輸入姓名:");
String name = sc.nextLine();

System.out.print("請輸入身高（公尺）:");
double height = sc.nextDouble();

System.out.print("請輸入體重（公斤）:");
double weight = sc.nextDouble();

double BMI = weight / (height * height);

String level;
if (BMI < 18.5) {
            level = "Underweight";
        } else if (BMI < 24 && BMI >= 18.5) {
            level = "Normal";
        } else if (BMI < 27 && BMI >= 24) {
            level = "Overweight";
        } else {
            level = "Obese";
        }

System.out.println("\n=== BMI Report ===");
System.out.println("姓名: " + name);
System.out.println("BMI: " + BMI);
System.out.println("Level: " + level);

String choice;
while (true){
System.out.print("\n是否繼續輸入下一筆?(y/n):");
choice = sc.next(); 

 if (choice.equals("y") || choice.equals("n")) {
                    sc.nextLine(); 
                    break; 
                }
               
                System.out.println("WRONG");
            }

            
            if (choice.equals("n")) {
                break;
            }
            
        }

sc.close();
}
}  


