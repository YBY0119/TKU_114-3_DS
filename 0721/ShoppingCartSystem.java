import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    private static ArrayList<CartItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== 購物車系統 ===");
            System.out.println("1. 加入商品");
            System.out.println("2. 修改商品數量");
            System.out.println("3. 移除商品");
            System.out.println("4. 計算總額與查看購物車");
            System.out.println("5. 離開");
            System.out.print("請選擇操作: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addItemUI(scanner);
                    break;
                case "2":
                    updateQuantityUI(scanner);
                    break;
                case "3":
                    removeItemUI(scanner);
                    break;
                case "4":
                    showCartAndTotal();
                    break;
                case "5":
                    System.out.println("謝謝使用購物車系統！");
                    scanner.close();
                    return;
                default:
                    System.out.println("無效選項，請重新輸入！");
            }
        }
    }

    public static void addItemUI(Scanner scanner) {
        try {
            System.out.print("輸入商品代碼: ");
            String id = scanner.nextLine().trim();
            
            CartItem existingItem = findItemById(id);
            if (existingItem != null) {
                System.out.print("商品已存在，請輸入要增加的數量: ");
                int addQty = Integer.parseInt(scanner.nextLine().trim());
                if (addQty <= 0) {
                    System.out.println("錯誤：增加數量必須大於 0！");
                    return;
                }
                existingItem.setQuantity(existingItem.getQuantity() + addQty);
                System.out.println("已增加數量，目前數量為: " + existingItem.getQuantity());
            } else {
                System.out.print("輸入商品名稱: ");
                String name = scanner.nextLine().trim();
                System.out.print("輸入單價: ");
                double price = Double.parseDouble(scanner.nextLine().trim());
                System.out.print("輸入數量: ");
                int quantity = Integer.parseInt(scanner.nextLine().trim());

                if (quantity <= 0 || price < 0) {
                    System.out.println("錯誤：數量必須大於 0 且單價不能為負數！");
                    return;
                }
                cart.add(new CartItem(id, name, price, quantity));
                System.out.println("商品已加入購物車！");
            }
        } catch (NumberFormatException e) {
            System.out.println("錯誤：請輸入有效的數值格式！");
        }
    }

    public static void updateQuantityUI(Scanner scanner) {
        System.out.print("請輸入要修改數量的商品代碼: ");
        String id = scanner.nextLine().trim();
        CartItem item = findItemById(id);

        if (item == null) {
            System.out.println("錯誤：購物車中找不到該商品！");
            return;
        }

        try {
            System.out.print("請輸入新數量: ");
            int newQty = Integer.parseInt(scanner.nextLine().trim());
            
            // 規則要求：數量小於等於 0 時不接受更新
            if (newQty <= 0) {
                System.out.println("錯誤：數量小於或等於 0 時不接受更新！");
                return;
            }

            item.setQuantity(newQty);
            System.out.println("更新成功！新數量為: " + newQty);
        } catch (NumberFormatException e) {
            System.out.println("錯誤：請輸入有效的整數數量！");
        }
    }

    public static void removeItemUI(Scanner scanner) {
        System.out.print("請輸入要移除的商品代碼: ");
        String id = scanner.nextLine().trim();
        CartItem item = findItemById(id);

        if (item != null) {
            cart.remove(item);
            System.out.println("商品已從購物車移除！");
        } else {
            System.out.println("錯誤：購物車中無此商品。");
        }
    }

    public static void showCartAndTotal() {
        if (cart.isEmpty()) {
            System.out.println("購物車目前是空的。");
            return;
        }

        System.out.println("--- 購物車內容 ---");
        double total = 0;
        for (CartItem item : cart) {
            System.out.println(item);
            total += item.getSubtotal();
        }
        System.out.println("----------------------------------------");
        System.out.printf("購物車總金額: $%.2f\n", total);
    }

    private static CartItem findItemById(String id) {
        for (CartItem item : cart) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }
}