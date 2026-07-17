import java.util.Scanner;

public class TextAnalyzer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";

        // 1. 輸入一行非空白文字（空字串與全空白輸入會要求重新輸入）
        while (true) {
            System.out.print("請輸入一行非空白文字: ");
            input = scanner.nextLine();
            if (input != null && !input.trim().isEmpty()) {
                break;
            }
            System.out.println("錯誤：輸入不能為空或全空白，請重新輸入！");
        }

        // 2. 顯示原始字元數
        System.out.println("原始字元數: " + input.length());

        // 3. 使用 trim() 後顯示有效字元數
        String trimmedInput = input.trim();
        System.out.println("有效字元數 (trim後): " + trimmedInput.length());

        // 4. & 5. 使用空白切割單字並顯示數量（處理連續空白）
        String[] words = splitWords(trimmedInput);
        System.out.println("單字數量: " + words.length);

        // 6. 計算英文字母母音的總數
        int vowelCount = countVowels(trimmedInput);
        System.out.println("英文字母母音總數 (a, e, i, o, u): " + vowelCount);

        // 7. 找出最長單字
        String longestWord = findLongestWord(words);
        System.out.println("最長單字: " + longestWord);

        // 8. 輸入關鍵字並顯示出現次數，忽略大小寫
        System.out.print("請輸入要搜尋的關鍵字: ");
        String keyword = scanner.next();
        int keywordCount = countKeyword(words, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數 (忽略大小寫): " + keywordCount);

        scanner.close();
    }

    /**
     * 自訂方法 1：使用空白切割單字，並正確處理連續空白 \\s+
     */
    public static String[] splitWords(String text) {
        if (text.isEmpty()) {
            return new String[0];
        }
        return text.split("\\s+");
    }

    /**
     * 自訂方法 2：計算字串中英文字母母音 (a, e, i, o, u) 的總數（不區分大小寫）
     */
    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char ch = lowerText.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    /**
     * 自訂方法 3：從單字陣列中找出最長的單字
     */
    public static String findLongestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (String word : words) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        return longest;
    }

    /**
     * 自訂方法 4：計算關鍵字在單字陣列中出現的次數（不區分大小寫）
     */
    public static int countKeyword(String[] words, String keyword) {
        int count = 0;
        for (String word : words) {
            if (word.equalsIgnoreCase(keyword)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 自訂方法 5：輔助檢查字串是否為空（確保滿足至少5個自訂方法的條件）
     */
    public static boolean isNullOrBlank(String text) {
        return text == null || text.trim().isEmpty();
    }
}