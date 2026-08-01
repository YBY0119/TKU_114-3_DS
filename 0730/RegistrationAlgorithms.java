import java.util.ArrayList;
import java.util.List;

public class RegistrationAlgorithms {

    public static void mergeSortById(List<Registration> list, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSortById(list, left, mid);
        mergeSortById(list, mid + 1, right);
        merge(list, left, mid, right);
    }

    private static void merge(List<Registration> list, int left, int mid, int right) {
        List<Registration> L = new ArrayList<>(list.subList(left, mid + 1));
        List<Registration> R = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < L.size() && j < R.size()) {
            if (L.get(i).getId().compareTo(R.get(j).getId()) <= 0) {
                list.set(k++, L.get(i++));
            } else {
                list.set(k++, R.get(j++));
            }
        }
        while (i < L.size()) list.set(k++, L.get(i++));
        while (j < R.size()) list.set(k++, R.get(j++));
    }

    public static Registration binarySearchById(List<Registration> list, String id) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comp = list.get(mid).getId().compareTo(id);
            if (comp == 0) return list.get(mid);
            if (comp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    public static List<Registration> sequentialSearchByName(List<Registration> list, String name) {
        List<Registration> res = new ArrayList<>();
        for (Registration r : list) {
            if (r.getName().equalsIgnoreCase(name)) {
                res.add(r);
            }
        }
        return res;
    }
}