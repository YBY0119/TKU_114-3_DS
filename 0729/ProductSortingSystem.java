import java.util.Arrays;

public class ProductSortingSystem {

    // 自行實作排序：依價格升冪
    public static void sortByPriceAsc(StoreProduct[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getPrice() > arr[j + 1].getPrice()) {
                    StoreProduct temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 自行實作排序：依價格降冪
    public static void sortByPriceDesc(StoreProduct[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getPrice() < arr[j + 1].getPrice()) {
                    StoreProduct temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // 自行實作排序：依庫存降冪
    public static void sortByStockDesc(StoreProduct[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getStock() < arr[j + 1].getStock()) {
                    StoreProduct temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void printList(String title, StoreProduct[] list) {
        System.out.println("\n【" + title + "】");
        for (StoreProduct p : list) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        // 至少 10 筆商品
        StoreProduct[] originalData = {
            new StoreProduct("P01", "無線滑鼠", 650.0, 50),
            new StoreProduct("P02", "機械鍵盤", 2500.0, 20),
            new StoreProduct("P03", "27吋顯示器", 12000.0, 15),
            new StoreProduct("P04", "USB 隨身碟", 350.0, 100),
            new StoreProduct("P05", "電競耳機", 2200.0, 30),
            new StoreProduct("P06", "筆記型電腦", 35000.0, 8),
            new StoreProduct("P07", "藍芽喇叭", 1500.0, 45),
            new StoreProduct("P08", "網頁攝影機", 1800.0, 25),
            new StoreProduct("P09", "行動電源", 800.0, 60),
            new StoreProduct("P10", "外接硬碟", 2800.0, 18)
        };

        // 1. 價格升冪
        StoreProduct[] test1 = Arrays.copyOf(originalData, originalData.length);
        sortByPriceAsc(test1);
        printList("排序欄位:價格 | 排序方向：升冪", test1);

        // 2. 價格降冪
        StoreProduct[] test2 = Arrays.copyOf(originalData, originalData.length);
        sortByPriceDesc(test2);
        printList("排序欄位:價格 | 排序方向：降冪", test2);

        // 3. 庫存降冪
        StoreProduct[] test3 = Arrays.copyOf(originalData, originalData.length);
        sortByStockDesc(test3);
        printList("排序欄位:庫存 | 排序方向：降冪", test3);
    }
}