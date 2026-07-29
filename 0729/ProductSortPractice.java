// 如果 Product 與 ProductSortPractice 放在同一檔名下，Product 前面不能加 public
class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;

    // 關鍵的建構子
    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return String.format("編號: %-6s | 名稱: %-10s | 價格: %8.1f | 數量: %3d", id, name, price, quantity);
    }
}

public class ProductSortPractice {
    public static void main(String[] args) {
        // 初始化 8 筆資料
        Product[] products = {
            new Product("P001", "無線滑鼠", 650.0, 50),
            new Product("P002", "機械鍵盤 A", 2500.0, 20),
            new Product("P003", "27吋螢幕 A", 12000.0, 15),
            new Product("P004", "USB 隨身碟", 350.0, 100),
            new Product("P005", "電競耳機", 2500.0, 30),      // 價格與 P002 相同
            new Product("P006", "筆記型電腦 A", 25000.0, 10),
            new Product("P007", "27吋螢幕 B", 12000.0, 8),     // 價格與 P003 相同
            new Product("P008", "筆記型電腦 B", 25000.0, 5)     // 價格與 P006 相同
        };

        System.out.println("--- 排序前商品列表 ---");
        for (Product p : products) {
            System.out.println(p);
        }
        
        // 此處可接續排序邏輯...
    }
}