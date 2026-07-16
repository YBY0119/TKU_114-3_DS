public class Q10_RecordParser {
    public static void main(String[] args) {
        // 完全保留原始題目的測試資料
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104||1|300"
        };

        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }

        System.out.println("合法筆數 : " + countValidRecords(records));
        System.out.println("總金額 : " + calculateGrandTotal(records));
    }

    // 1. 驗證單筆紀錄是否合法
    public static boolean isValidRecord(String record) {
        // 規則 6：紀錄為 null 或只包含空白時，不合法
        if (record == null || record.trim().isEmpty()) {
            return false;
        }

        // 規則 1：使用 limit = -1 確保空欄位不被丟棄，驗證是否剛好有 4 個欄位
        String[] tokens = record.split("\\|", -1);
        if (tokens.length != 4) {
            return false;
        }

        // 規則 2：代碼與名稱移除前後空白後不可為空字串
        String code = tokens[0].trim();
        String name = tokens[1].trim();
        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        // 規則 5：文字無法轉換為整數時，捕捉異常並判定為不合法，程式不可中斷
        try {
            int quantity = Integer.parseInt(tokens[2].trim());
            int price = Integer.parseInt(tokens[3].trim());

            // 規則 3：數量必須是大於 0 的整數
            if (quantity <= 0) {
                return false;
            }

            // 規則 4：單價必須是大於或等於 0 的整數
            if (price < 0) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false; // 無法成功轉為數字，回傳不合法
        }

        return true;
    }

    // 2. 計算單筆紀錄的總金額，若不合法則回傳 -1
    public static int calculateRecordTotal(String record) {
        if (!isValidRecord(record)) {
            return -1;
        }
        
        // 既然已經通過 isValidRecord 驗證，此處轉換數值絕對安全
        String[] tokens = record.split("\\|", -1);
        int quantity = Integer.parseInt(tokens[2].trim());
        int price = Integer.parseInt(tokens[3].trim());
        
        return quantity * price;
    }

    // 3. 計算合法紀錄的總筆數
    public static int countValidRecords(String[] records) {
        if (records == null) {
            return 0;
        }
        int count = 0;
        for (String record : records) {
            if (isValidRecord(record)) {
                count++;
            }
        }
        return count;
    }

    // 4. 計算所有合法紀錄的加總金額（不合法紀錄不予計算）
    public static int calculateGrandTotal(String[] records) {
        if (records == null) {
            return 0;
        }
        int grandTotal = 0;
        for (String record : records) {
            if (isValidRecord(record)) {
                grandTotal += calculateRecordTotal(record);
            }
        }
        return grandTotal;
    }
}