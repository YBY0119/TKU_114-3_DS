public class Q03_AccessDecision {
    public static void main(String[] args) {
        // 完全保留原始題目的測試輸出
        System.out.println(decideAccess(20, true, false, false));
        System.out.println(decideAccess(20, false, true, false));
        System.out.println(decideAccess(17, true, true, false));
        System.out.println(decideAccess(30, true, true, true));
        System.out.println(decideAccess(130, true, true, false));
    }

    public static String decideAccess(
        int age,
        boolean member,
        boolean hasInvitation,
        boolean suspended
    ) {
        // 1. 年齡小於 0 或大於 120，回傳 INVALID
        if (age < 0 || age > 120) {
            return "INVALID";
        }

        // 2. 帳號已停權，回傳 DENIED
        if (suspended) {
            return "DENIED";
        }

        // 3. 年滿 18 歲，而且具有會員資格或邀請碼，回傳 ALLOWED
        // 注意：(member || hasInvitation) 必須加括號以確保優先運算
        if (age >= 18 && (member || hasInvitation)) {
            return "ALLOWED";
        }

        // 4. 其他情況回傳 REVIEW
        return "REVIEW";
    }
}