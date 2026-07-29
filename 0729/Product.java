public class Product {
    private String id;       // 商品編號 (String)
    private String name;     // 商品名稱 (String)
    private double price;    // 商品價格 (double)
    private int quantity;    // 商品數量/庫存 (int)

    // 必須包含這個 (String, String, double, int) 的建構子
    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getter 與 Setter 方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("編號: %-6s | 名稱: %-10s | 價格: %8.1f | 數量: %3d", id, name, price, quantity);
    }
}