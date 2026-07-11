public class MaxMinDemo {
    public static void main(String[] args) {
        int price1 = 10;
        int price2 = 20;
        int price3 = 5;

        int max = price1;
        if (price2 > max) {
            max = price2;
        }
        if (price3 > max) {
            max = price3;
        }

        int min = price1;
        if (price2 < min) {
            min = price2;
        }
        if (price3 < min) {
            min = price3;
        }

        System.out.println("Maximum: " + max);
        System.out.println("Minimum: " + min);
}
}