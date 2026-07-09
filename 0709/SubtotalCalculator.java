public class SubtotalCalculator {
    public static void main(String[] args) {
     int result = calculateSubtotal(5,10);
     System.out.println("Total: " + result);   
}
public static int calculateSubtotal(int price, int quantity) {
    return quantity * price;
}
}