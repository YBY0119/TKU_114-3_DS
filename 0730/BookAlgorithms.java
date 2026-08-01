import java.util.ArrayList;
import java.util.List;

public class BookAlgorithms {

    /**
     * Merge Sort: 依編號升冪；若編號相同則依借閱次數降冪
     */
    public static void mergeSort(List<Book> books, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(books, left, mid);
        mergeSort(books, mid + 1, right);
        merge(books, left, mid, right);
    }

    private static void merge(List<Book> books, int left, int mid, int right) {
        List<Book> leftList = new ArrayList<>(books.subList(left, mid + 1));
        List<Book> rightList = new ArrayList<>(books.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;
        while (i < leftList.size() && j < rightList.size()) {
            Book b1 = leftList.get(i);
            Book b2 = rightList.get(j);

            int idComp = b1.getId().compareTo(b2.getId());
            if (idComp < 0) {
                books.set(k++, leftList.get(i++));
            } else if (idComp > 0) {
                books.set(k++, rightList.get(j++));
            } else {
                // 編號相同時，借閱次數降冪（次數大者優先）
                if (b1.getBorrowCount() >= b2.getBorrowCount()) {
                    books.set(k++, leftList.get(i++));
                } else {
                    books.set(k++, rightList.get(j++));
                }
            }
        }

        while (i < leftList.size()) books.set(k++, leftList.get(i++));
        while (j < rightList.size()) books.set(k++, rightList.get(j++));
    }

    /**
     * Binary Search: 依排序後的書籍編號查詢
     */
    public static Book binarySearchById(List<Book> books, String targetId) {
        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = books.get(mid);
            int comp = midBook.getId().compareTo(targetId);

            if (comp == 0) {
                return midBook;
            } else if (comp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    /**
     * Sequential Search: 依分類找出全部書籍
     */
    public static List<Book> sequentialSearchByCategory(List<Book> books, String category) {
        List<Book> result = new ArrayList<>();
        if (books == null || category == null) return result;

        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }
}