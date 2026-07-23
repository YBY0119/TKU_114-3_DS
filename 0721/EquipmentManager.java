import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    private static ArrayList<Equipment> equipmentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== 設備管理系統 ===");
            System.out.println("1. 新增設備");
            System.out.println("2. 依代碼搜尋設備");
            System.out.println("3. 借出設備");
            System.out.println("4. 歸還設備");
            System.out.println("5. 列出可借設備");
            System.out.println("6. 離開");
            System.out.print("請選擇操作 (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("輸入設備代碼: ");
                    String code = scanner.nextLine().trim();
                    System.out.print("輸入設備名稱: ");
                    String name = scanner.nextLine().trim();
                    addEquipment(code, name);
                    break;
                case "2":
                    System.out.print("輸入搜尋代碼: ");
                    searchEquipment(scanner.nextLine().trim());
                    break;
                case "3":
                    System.out.print("輸入借出設備代碼: ");
                    borrowEquipment(scanner.nextLine().trim());
                    break;
                case "4":
                    System.out.print("輸入歸還設備代碼: ");
                    returnEquipment(scanner.nextLine().trim());
                    break;
                case "5":
                    listAvailableEquipment();
                    break;
                case "6":
                    System.out.println("系統結束。");
                    scanner.close();
                    return;
                default:
                    System.out.println("錯誤：無效的選項！");
            }
        }
    }

    public static void addEquipment(String code, String name) {
        if (code.isEmpty() || name.isEmpty()) {
            System.out.println("錯誤：代碼與名稱不可為空白！");
            return;
        }
        if (findEquipmentByCode(code) != null) {
            System.out.println("錯誤：代碼已存在，不可重複！");
            return;
        }
        equipmentList.add(new Equipment(code, name));
        System.out.println("新增設備成功！");
    }

    public static void searchEquipment(String code) {
        Equipment eq = findEquipmentByCode(code);
        if (eq != null) {
            System.out.println("找到設備: " + eq);
        } else {
            System.out.println("找不到代碼為 " + code + " 的設備。");
        }
    }

    public static void borrowEquipment(String code) {
        Equipment eq = findEquipmentByCode(code);
        if (eq == null) {
            System.out.println("錯誤：找不到該設備！");
        } else if (!eq.isAvailable()) {
            System.out.println("提示：該設備已被借出，無法重複借出！");
        } else {
            eq.setAvailable(false);
            System.out.println("借出成功！");
        }
    }

    public static void returnEquipment(String code) {
        Equipment eq = findEquipmentByCode(code);
        if (eq == null) {
            System.out.println("錯誤：找不到該設備！");
        } else if (eq.isAvailable()) {
            System.out.println("提示：該設備目前本來就是可借用狀態！");
        } else {
            eq.setAvailable(true);
            System.out.println("歸還成功！");
        }
    }

    public static void listAvailableEquipment() {
        System.out.println("--- 可借用設備清單 ---");
        boolean found = false;
        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前無可借用設備。");
        }
    }

    private static Equipment findEquipmentByCode(String code) {
        for (Equipment eq : equipmentList) {
            if (eq.getCode().equalsIgnoreCase(code)) {
                return eq;
            }
        }
        return null;
    }
}