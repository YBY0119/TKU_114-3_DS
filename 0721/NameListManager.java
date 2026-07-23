import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    private static ArrayList<String> nameList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== 名單管理系統 ===");
            System.out.println("1. 新增姓名");
            System.out.println("2. 搜尋姓名");
            System.out.println("3. 修改姓名");
            System.out.println("4. 刪除姓名");
            System.out.println("5. 列出全部");
            System.out.println("6. 離開");
            System.out.print("請選擇操作 (1-6): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("請輸入要新增的姓名: ");
                    String nameToAdd = scanner.nextLine();
                    addName(nameToAdd);
                    break;
                case "2":
                    System.out.print("請輸入要搜尋的姓名: ");
                    String nameToSearch = scanner.nextLine();
                    searchName(nameToSearch);
                    break;
                case "3":
                    System.out.print("請輸入要修改的舊姓名: ");
                    String oldName = scanner.nextLine();
                    System.out.print("請輸入新姓名: ");
                    String newName = scanner.nextLine();
                    updateName(oldName, newName);
                    break;
                case "4":
                    System.out.print("請輸入要刪除的姓名: ");
                    String nameToDelete = scanner.nextLine();
                    deleteName(nameToDelete);
                    break;
                case "5":
                    listAllNames();
                    break;
                case "6":
                    System.out.println("系統已結束。");
                    scanner.close();
                    return;
                default:
                    System.out.println("錯誤：無效的選項，請重新輸入。");
            }
        }
    }

    public static void addName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("錯誤：不得加入空白姓名！");
            return;
        }
        nameList.add(name.trim());
        System.out.println("成功新增姓名: " + name.trim());
    }

    public static void searchName(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("錯誤：搜尋名稱不可為空！");
            return;
        }
        boolean found = false;
        for (String item : nameList) {
            if (item.equalsIgnoreCase(name.trim())) {
                System.out.println("找到相符姓名: " + item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到姓名: " + name);
        }
    }

    public static void updateName(String oldName, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            System.out.println("錯誤：新姓名不可為空白！");
            return;
        }
        int index = findIndexIgnoreCase(oldName);
        if (index != -1) {
            nameList.set(index, newName.trim());
            System.out.println("修改成功！已更新為: " + newName.trim());
        } else {
            System.out.println("修改失敗：找不到姓名 " + oldName);
        }
    }

    public static void deleteName(String name) {
        int index = findIndexIgnoreCase(name);
        if (index != -1) {
            String removed = nameList.remove(index);
            System.out.println("刪除成功！已移除: " + removed);
        } else {
            System.out.println("刪除失敗：找不到姓名 " + name);
        }
    }

    public static void listAllNames() {
        if (nameList.isEmpty()) {
            System.out.println("目前名單為空。");
        } else {
            System.out.println("--- 姓名清單 ---");
            for (int i = 0; i < nameList.size(); i++) {
                System.out.println((i + 1) + ". " + nameList.get(i));
            }
        }
    }

    private static int findIndexIgnoreCase(String name) {
        if (name == null) return -1;
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(name.trim())) {
                return i;
            }
        }
        return -1;
    }
}