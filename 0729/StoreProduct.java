public class StoreProduct {
    private String id;
    private String name;
    private double price;
    private int stock;

    public StoreProduct(String id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return String.format("ID: %-5s | 名稱: %-12s | 價格: %8.1f | 庫存: %4d", id, name, price, stock);
    }
}