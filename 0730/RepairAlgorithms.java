import java.util.ArrayList;
import java.util.List;

public class RepairAlgorithms {

    /**
     * Merge Sort: 依優先等級降冪；相同等級保持登記順序 (穩定排序)
     */
    public static void mergeSort(List<RepairTask> tasks, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(tasks, left, mid);
        mergeSort(tasks, mid + 1, right);
        merge(tasks, left, mid, right);
    }

    private static void merge(List<RepairTask> tasks, int left, int mid, int right) {
        List<RepairTask> leftList = new ArrayList<>(tasks.subList(left, mid + 1));
        List<RepairTask> rightList = new ArrayList<>(tasks.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            RepairTask t1 = leftList.get(i);
            RepairTask t2 = rightList.get(j);

            // 優先等級降冪（數字大者優先）
            if (t1.getPriority() > t2.getPriority()) {
                tasks.set(k++, leftList.get(i++));
            } else if (t1.getPriority() < t2.getPriority()) {
                tasks.set(k++, rightList.get(j++));
            } else {
                // 相同等級時，依登記順序升冪 (保留原順序)
                if (t1.getOrderIndex() <= t2.getOrderIndex()) {
                    tasks.set(k++, leftList.get(i++));
                } else {
                    tasks.set(k++, rightList.get(j++));
                }
            }
        }

        while (i < leftList.size()) tasks.set(k++, leftList.get(i++));
        while (j < rightList.size()) tasks.set(k++, rightList.get(j++));
    }

    public static RepairTask searchById(List<RepairTask> tasks, String id) {
        for (RepairTask t : tasks) {
            if (t.getId().equalsIgnoreCase(id)) return t;
        }
        return null;
    }

    public static List<RepairTask> searchByDeviceName(List<RepairTask> tasks, String deviceName) {
        List<RepairTask> result = new ArrayList<>();
        for (RepairTask t : tasks) {
            if (t.getDeviceName().equalsIgnoreCase(deviceName)) {
                result.add(t);
            }
        }
        return result;
    }
}