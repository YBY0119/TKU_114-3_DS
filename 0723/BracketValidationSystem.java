import java.util.Stack;

public class BracketValidationSystem {

    // 判斷括號是否配對的 Method
    public static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }

    // 驗證字串括號是否正確
    public static boolean validateBrackets(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char ch : expression.toCharArray()) {
            // 1. 忽略非括號字元，只處理括號
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty()) {
                    return false; // 缺少左括號
                }
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false; // 順序或類型不匹配
                }
            }
        }
        return stack.isEmpty(); // 若 Stack 不為空，代表缺少右括號
    }

    public static void main(String[] args) {
        String[] testCases = {
            "{[()]}",                   // 正確多層巢狀
            "a * (b + [c * {d}])",       // 包含非括號字元
            "([)]",                     // 順序錯誤
            "((())",                    // 缺少右括號
            "())",                      // 缺少左括號
            "System.out.println(\"Hi\");"// 程式碼字串
        };

        System.out.println("=== 括號驗證測試結果 ===");
        for (String test : testCases) {
            boolean isValid = validateBrackets(test);
            System.out.printf("測試字串: %-25s -> 驗證結果: %s\n", "\"" + test + "\"", isValid ? "合法" : "非法");
        }
    }
}