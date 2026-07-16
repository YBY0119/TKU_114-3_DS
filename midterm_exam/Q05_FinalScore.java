public class Q05_FinalScore {
    public static void main(String[] args) {
        // 完全保留原始題目的測試輸出
        System.out.println(calculateFinalScore(80, 90, 5));
        System.out.println(calculateFinalScore(100, 100, 10));
        System.out.println(calculateFinalScore(-1, 80, 5));
        System.out.println(calculateFinalScore(70, 60, 11));
    }

    public static double calculateFinalScore(
        int examScore,
        int assignmentScore,
        int bonus
    ) {
        // 規則 1、2、3：驗證參數合法性，任一不合法即回傳 -1.0
        if (examScore < 0 || examScore > 100) {
            return -1.0;
        }
        if (assignmentScore < 0 || assignmentScore > 100) {
            return -1.0;
        }
        if (bonus < 0 || bonus > 10) {
            return -1.0;
        }

        // 規則 4：計算原始成績 (考試 * 40% + 作業 * 60%)
        double rawScore = (examScore * 0.4) + (assignmentScore * 0.6);

        // 原始成績加上加分
        double finalScore = rawScore + bonus;

        // 規則 5：最高 100 分限制
        if (finalScore > 100.0) {
            return 100.0;
        }

        return finalScore;
    }
}