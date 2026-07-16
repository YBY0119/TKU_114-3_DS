public class Q12_BookingReport {
    public static void main(String[] args) {
        // 完全保留原始題目的測試資料
        Q12_Booking[] bookings = {
            new Q12_Booking("B001", "Amy", 2, 750, true),
            new Q12_Booking("B002", "Ben", 4, 600, false),
            new Q12_Booking("B003", "Cara", 3, 900, true),
            new Q12_Booking("B004", "Dan", 1, 1200, true)
        };

        System.out.println("已確認單數 : " + countConfirmed(bookings));
        System.out.println("已確認收入 : " + calculateConfirmedRevenue(bookings));

        Q12_Booking found = findById(bookings, "b003"); // 測試不區分大小寫搜尋
        System.out.println("搜尋結果 : " + found);

        Q12_Booking largest = findLargestConfirmed(bookings);
        System.out.println("最高確認預約 : " + largest);
    }

    // 1. 已確認單數計算
    public static int countConfirmed(Q12_Booking[] bookings) {
        if (bookings == null) {
            return 0;
        }
        int count = 0;
        for (Q12_Booking b : bookings) {
            if (b != null && b.isConfirmed()) {
                count++;
            }
        }
        return count;
    }

    // 2. 已確認收入計算
    public static double calculateConfirmedRevenue(Q12_Booking[] bookings) {
        if (bookings == null) {
            return 0.0;
        }
        double totalRevenue = 0.0;
        for (Q12_Booking b : bookings) {
            if (b != null && b.isConfirmed()) {
                totalRevenue += b.getTotalPrice();
            }
        }
        return totalRevenue;
    }

    // 3. 依代碼搜尋預約（忽略大小寫）
    public static Q12_Booking findById(Q12_Booking[] bookings, String id) {
        if (bookings == null || id == null) {
            return null;
        }
        for (Q12_Booking b : bookings) {
            if (b != null && b.getId() != null && b.getId().equalsIgnoreCase(id)) {
                return b;
            }
        }
        return null;
    }

    // 4. 尋找金額最高的已確認預約（平手時回傳較前者）
    public static Q12_Booking findLargestConfirmed(Q12_Booking[] bookings) {
        if (bookings == null) {
            return null;
        }
        
        Q12_Booking largest = null;
        double maxPrice = -1.0; // 初始化為一個不可能的負數值

        for (Q12_Booking b : bookings) {
            if (b != null && b.isConfirmed()) {
                double currentPrice = b.getTotalPrice();
                // 使用 > 確保平手時保留原先（索引較前）的 largest
                if (currentPrice > maxPrice) {
                    maxPrice = currentPrice;
                    largest = b;
                }
            }
        }
        return largest;
    }
}