import java.util.Stack;

public class BrowserUndoSystem {
    private Stack<String> historyStack = new Stack<>();

    // 1. 開啟新頁
    public void openPage(String url) {
        historyStack.push(url);
        System.out.println("開啟新頁面: " + url);
    }

    // 2. 返回上一頁
    public void back() {
        if (historyStack.size() <= 1) {
            // 沒有上一頁可返回（無歷史或只剩當前頁）
            System.out.println("[提示] 沒有上一頁可供返回！");
            return;
        }
        String current = historyStack.pop();
        System.out.println("從 " + current + " 返回至: " + historyStack.peek());
    }

    // 3. 查看目前頁面
    public void getCurrentPage() {
        if (historyStack.isEmpty()) {
            System.out.println("[提示] 目前沒有開啟任何頁面。");
        } else {
            System.out.println("目前頁面: " + historyStack.peek());
        }
    }

    // 4. 至少 8 次操作測試
    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 開始瀏覽器操作測試 ===");
        browser.getCurrentPage();                  // 操作 1
        browser.back();                             // 操作 2 (無上一頁提示)
        browser.openPage("https://google.com");     // 操作 3
        browser.openPage("https://github.com");     // 操作 4
        browser.getCurrentPage();                  // 操作 5
        browser.openPage("https://stackoverflow.com");// 操作 6
        browser.back();                             // 操作 7
        browser.back();                             // 操作 8
        browser.back();                             // 操作 9 (測試空狀態保護)
    }
}