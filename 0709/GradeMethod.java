import java.util.Scanner;

public class GradeMethod {
  public static void main(String[]args){
Scanner sc = new Scanner(System.in);

System.out.print("請輸入 Java 成績: ");
int javaScore = sc.nextInt();

System.out.print("請輸入 English 成績: ");
int EnglishScore = sc.nextInt();

System.out.print("請輸入 Math 成績: ");
int MathScore = sc.nextInt();

double average = calculateAverage(javaScore, EnglishScore, MathScore);
String grade = getGrade(average);
System.out.println("Level:" + grade);
sc.close();
  }
public static double calculateAverage(int javaScore,int EnglishScore,int MathScore){
return (javaScore + EnglishScore + MathScore)/3.0;
}

public static String getGrade(double average){
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
        return String.valueOf(grade);
    }
}
