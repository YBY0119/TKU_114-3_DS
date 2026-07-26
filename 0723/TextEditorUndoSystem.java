import java.util.Stack;

public class TextEditorUndoSystem {
    private StringBuilder currentText = new StringBuilder();
    private Stack<String> historyStack = new Stack<>();

    // 每次修改前保存狀態
    private void saveState() {
        historyStack.push(currentText.toString());
    }

    // 新增文字
    public void type(String newText) {
        saveState();
        currentText.append(newText);
        System.out.println("輸入文字: \"" + newText + "\"");
    }

    // 刪除最後數個字元
    public void deleteLast(int count) {
        if (count <= 0) return;
        saveState();
        int length = currentText.length();
        if (count >= length) {
            currentText.setLength(0);
        } else {
            currentText.delete(length - count, length);
        }
        System.out.println("刪除最後 " + count + " 個字元");
    }

    // Undo 復原
    public void undo() {
        if (historyStack.isEmpty()) {
            System.out.println("[提示] 沒有歷史紀錄，無法 Undo！");
            return;
        }
        currentText = new StringBuilder(historyStack.pop());
        System.out.println("執行 Undo -> 恢復文字內容");
    }

    // 顯示內容
    public void display() {
        System.out.println("當前文字內容: \"" + currentText.toString() + "\"");
    }

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 編輯與 Undo 驗證 ===");
        editor.type("Hello ");
        editor.type("World");
        editor.display();

        editor.deleteLast(3);
        editor.display();

        editor.type(" Java!");
        editor.display();

        System.out.println("\n--- 開始連續三次 Undo 測試 ---");
        editor.undo(); // 復原 1
        editor.display();

        editor.undo(); // 復原 2
        editor.display();

        editor.undo(); // 復原 3
        editor.display();

        editor.undo(); // 額外 Undo 測試無歷史狀態
    }
}