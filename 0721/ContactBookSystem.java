import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== 聯絡人管理系統 ===");
            System.out.println("1. 新增聯絡人");
            System.out.println("2. 搜尋聯絡人");
            System.out.println("3. 修改電話");
            System.out.println("4. 刪除聯絡人");
            System.out.println("5. 顯示完整清單");
            System.out.println("6. 離開");
            System.out.print("請選擇功能: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addContactUI(scanner);
                    break;
                case "2":
                    searchContactUI(scanner);
                    break;
                case "3":
                    updatePhoneUI(scanner);
                    break;
                case "4":
                    deleteContactUI(scanner);
                    break;
                case "5":
                    listAllContacts();
                    break;
                case "6":
                    System.out.println("系統已結束。");
                    scanner.close();
                    return;
                default:
                    System.out.println("無效的選項，請重新輸入！");
            }
        }
    }

    // 自定義 Method 1: 新增
    public static void addContactUI(Scanner scanner) {
        System.out.print("輸入代碼: ");
        String id = scanner.nextLine().trim();
        System.out.print("輸入姓名: ");
        String name = scanner.nextLine().trim();
        System.out.print("輸入電話: ");
        String phone = scanner.nextLine().trim();
        System.out.print("輸入Email: ");
        String email = scanner.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()) {
            System.out.println("錯誤：代碼與空白姓名不可加入！");
            return;
        }
        if (getContactById(id) != null) {
            System.out.println("錯誤：代碼重複，無法新增！");
            return;
        }

        contacts.add(new Contact(id, name, phone, email));
        System.out.println("聯絡人新增成功！");
    }

    // 自定義 Method 2: 搜尋
    public static void searchContactUI(Scanner scanner) {
        System.out.print("請輸入要搜尋的代碼或姓名: ");
        String query = scanner.nextLine().trim();
        boolean found = false;
        for (Contact c : contacts) {
            if (c.getId().equalsIgnoreCase(query) || c.getName().equalsIgnoreCase(query)) {
                System.out.println("找到聯絡人: " + c);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到符合條件的聯絡人。");
        }
    }

    // 自定義 Method 3: 修改電話
    public static void updatePhoneUI(Scanner scanner) {
        System.out.print("請輸入要修改電話的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        Contact c = getContactById(id);
        if (c != null) {
            System.out.print("請輸入新電話: ");
            String newPhone = scanner.nextLine().trim();
            c.setPhone(newPhone);
            System.out.println("電話修改成功！");
        } else {
            System.out.println("錯誤：找不到該代碼的聯絡人。");
        }
    }

    // 自定義 Method 4: 刪除
    public static void deleteContactUI(Scanner scanner) {
        System.out.print("請輸入要刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();
        Contact c = getContactById(id);
        if (c != null) {
            contacts.remove(c);
            System.out.println("刪除成功！");
        } else {
            System.out.println("刪除失敗：找不到該代碼。");
        }
    }

    // 自定義 Method 5: 顯示清單
    public static void listAllContacts() {
        if (contacts.isEmpty()) {
            System.out.println("通訊錄目前沒有資料。");
        } else {
            System.out.println("--- 完整聯絡人清單 ---");
            for (Contact c : contacts) {
                System.out.println(c);
            }
        }
    }

    // 輔助 Method: 依代碼取得物件
    private static Contact getContactById(String id) {
        for (Contact c : contacts) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
}