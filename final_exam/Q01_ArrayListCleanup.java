import java.util.ArrayList;
import java.util.Arrays;

public class Q01_ArrayListCleanup {
    public static void main(String[] args) {
        ArrayList<Integer> scores = new ArrayList<>(
            Arrays.asList(72, 35, 28, 80, 41, 39, 90)
        );

        int removed = removeBelow(scores, 40);
        System.out.println("移除筆數：" + removed);
        System.out.println("保留資料：" + scores);
    }

    public static int removeBelow(
        ArrayList<Integer> scores,
        int minimum
    ) {
        int removed = 0;

        for (int index = 0; index < scores.size(); index++) {
            if (scores.get(index) < minimum) {
                scores.remove(index);
                removed++;
                index--; // 關鍵修改：刪除後將 index 減 1，抵消 index++，避免漏掉連續不合格的分數
            }
        }

        return removed;
    }
}