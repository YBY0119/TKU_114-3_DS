import java.util.*;

public class LibraryManagementSystem {
    private List<Book> bookList = new ArrayList<>();
    private Set<String> existingIds = new HashSet<>();

    public boolean addBook(Book book) {
        if (book == null) {
            System.out.println("❌ 新增失敗：書籍資料不能為空！");
            return false;
        }
        if (existingIds.contains(book.getId())) {
            System.out.println("❌ 新增失敗：重複的書籍編號 [" + book.getId() + "]");
            return false;
        }
        existingIds.add(book.getId());
        bookList.add(book);
        System.out.println("✅ 成功新增書籍: " + book);
        return true;
    }

    public void sortBooks() {
        if (bookList.isEmpty()) {
            System.out.println("⚠️ 系統無任何書籍，無法進行排序。");
            return;
        }
        BookAlgorithms.mergeSort(bookList, 0, bookList.size() - 1);
        System.out.println("✅ 已完成 Merge Sort (編號升冪 / 借閱次數降冪)。");
    }

    public Book findBookById(String id) {
        return BookAlgorithms.binarySearchById(bookList, id);
    }

    public List<Book> findBooksByCategory(String category) {
        return BookAlgorithms.sequentialSearchByCategory(bookList, category);
    }

    public void displayAll() {
        if (bookList.isEmpty()) {
            System.out.println("（目前系統中沒有任何書籍）");
            return;
        }
        for (Book b : bookList) {
            System.out.println("  " + b);
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem sys = new LibraryManagementSystem();

        System.out.println("=== 1. 測試新增與防重編號 ===");
        sys.addBook(new Book("B003", "Data Structures", "CS", 15));
        sys.addBook(new Book("B001", "Java Programming", "CS", 40));
        sys.addBook(new Book("B005", "Operating Systems", "CS", 25));
        sys.addBook(new Book("B002", "Calculus", "Math", 10));
        sys.addBook(new Book("B001", "Duplicate Book", "CS", 5)); // 重複測試

        System.out.println("\n=== 2. 排序前所有書籍 ===");
        sys.displayAll();

        System.out.println("\n=== 3. 執行 Merge Sort 排序 ===");
        sys.sortBooks();
        sys.displayAll();

        System.out.println("\n=== 4. 測試 Binary Search 依編號搜尋 ===");
        Book b = sys.findBookById("B002");
        System.out.println("搜尋 B002 號書籍結果: " + (b != null ? b : "未找到"));

        Book notFound = sys.findBookById("B999");
        System.out.println("搜尋 B999 號書籍結果: " + (notFound != null ? notFound : "未找到"));

        System.out.println("\n=== 5. 測試 Sequential Search 依分類搜尋 ===");
        System.out.println("搜尋 CS 分類書籍:");
        List<Book> csBooks = sys.findBooksByCategory("CS");
        csBooks.forEach(bk -> System.out.println("  " + bk));

        System.out.println("搜尋 History (不存在) 分類書籍:");
        List<Book> historyBooks = sys.findBooksByCategory("History");
        System.out.println("找到筆數: " + historyBooks.size());
    }
}