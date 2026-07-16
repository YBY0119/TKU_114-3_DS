public class Q01_ParkingFeeFix {
    public static void main(String[] args) {
        int[] testMinutes = {-20, 30, 31, 60, 61, 120, 121, 420};

        for (int minutes : testMinutes) {
            int fee = calculateFee(minutes);
            System.out.println("停車 " + minutes + " 分鐘的費用:" + fee + " 元");
        }
    }

    public static int calculateFee(int minutes) {
        // 規則 1：停車時間小於 0 分鐘，回傳 -1
        if (minutes < 0) {
            return -1;
        }
        
        // 規則 2：停車時間為 0～30 分鐘，免費
        if (minutes <= 30) {
            return 0;
        }
        
        // 規則 3：超過 30 分鐘但不超過 120 分鐘，超過的部分每開始 30 分鐘收費 20 元
        if (minutes <= 120) {
            int extraMinutes = minutes - 30;
            int periods = (extraMinutes + 29) / 30; // 無條件進位
            return periods * 20;
        }
        
        // 規則 4：超過 120 分鐘，前 120 分鐘收費 60 元，之後每開始 60 分鐘加收 30 元
        int extraMinutes = minutes - 120;
        int periods = (extraMinutes + 59) / 60; // 無條件進位
        int fee = 60 + periods * 30;
        
        // 規則 5：單次停車費最高為 180 元
        if (fee > 180) {
            return 180;
        }
        
        return fee;
    }
}