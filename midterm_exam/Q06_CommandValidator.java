public class Q06_CommandValidator {
    public static void main(String[] args) {
        // 完全保留原始題目的測試輸出與陣列
        String[] commands = {
            "START",
            new String("stop"),
            "  Pause  ",
            "RUN",
            "  ",
            null
        };

        for (String command : commands) {
            System.out.println(command + " -> " + isValidCommand(command));
        }
    }

    public static boolean isValidCommand(String command) {
        // 1. 正確處理 null：必須最先判斷，防止 NullPointerException
        if (command == null) {
            return false;
        }

        // 2. 移除前後空白：處理完後存回一個乾淨的字串
        String cleanedCommand = command.trim();

        // 3. 忽略大小寫並精確比對三種合法指令
        // 4. 拒絕空字串（因為空字串 trim() 後仍為空字串 ""，不符合這三種，故會自動回傳 false）
        return cleanedCommand.equalsIgnoreCase("START")
            || cleanedCommand.equalsIgnoreCase("STOP")
            || cleanedCommand.equalsIgnoreCase("PAUSE");
    }
}