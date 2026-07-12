import java.util.Scanner;

public class PersonalProfileApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 1. 輸入基本資料
        System.out.print("請輸入姓名: ");
        String name = sc.nextLine();

        // 2. 輸入並驗證年齡、身高、體重（必須大於 0）
        int age = readPositiveInt(sc, "請輸入年齡: ");
        double height = readPositiveDouble(sc, "請輸入身高（公尺）: ");
        double weight = readPositiveDouble(sc, "請輸入體重（公斤）: ");

        // 3. 運算與邏輯判斷
        double bmi = calculateBmi(height, weight);
        String level = getBmiLevel(bmi);
        boolean adult = isAdult(age);

        // 4. 輸出完整報表
        printReport(name, age, adult, height, weight, bmi, level);

        sc.close();
    }

    /**
     * 讀取並驗證大於 0 的整數（年齡防呆）
     */
    public static int readPositiveInt(Scanner sc, String message) {
        int value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value > 0) {
                    break; // 合法輸入，跳出迴圈
                }
            } else {
                sc.next(); // 清除錯誤的非數字輸入
            }
            System.out.println("輸入不合法，必須大於 0，請重新輸入。");
        }
        return value;
    }

    /**
     * 讀取並驗證大於 0 的浮點數（身高、體重防呆）
     */
    public static double readPositiveDouble(Scanner sc, String message) {
        double value;
        while (true) {
            System.out.print(message);
            if (sc.hasNextDouble()) {
                value = sc.nextDouble();
                if (value > 0) {
                    break; // 合法輸入，跳出迴圈
                }
            } else {
                sc.next(); // 清除錯誤的非數字輸入
            }
            System.out.println("輸入不合法，必須大於 0，請重新輸入。");
        }
        return value;
    }

    /**
     * 計算 BMI：體重 / (身高^2)
     */
    public static double calculateBmi(double height, double weight) {
        return weight / (height * height);
    }

    /**
     * 根據 BMI 值進行分級判斷
     */
    public static String getBmiLevel(double bmi) {
        if (bmi < 18.5) {
            return "體重過輕";
        } else if (bmi >= 18.5 && bmi < 24) {
            return "普通"; // 依照執行範例中的 "Normal" 對應中文或直接回傳 Normal
        } else if (bmi >= 24 && bmi < 27) {
            return "超重";
        } else {
            // bmi >= 27
            return "肥胖";
        }
    }

    /**
     * 判斷是否成年（大於或等於 18 歲）
     */
    public static boolean isAdult(int age) {
        return age >= 18;
    }

    /**
     * 輸出個人健康資料完整報表
     */
    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        // 為了完美對齊執行範例中的 "Level: Normal"，將分級對應輸出
        String displayLevel = level;
        if (level.equals("普通")) {
            displayLevel = "Normal";
        }

        System.out.println("\n=== Personal Health Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + bmi);
        System.out.println("Level: " + displayLevel);
    }
}