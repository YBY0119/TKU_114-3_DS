public class EmployeeSearchSystem {

    // 3. 使用 Binary Search 依編號查詢
    public static Employee binarySearchEmployee(Employee[] employees, int targetId) {
        // 5. 空陣列處理
        if (employees == null || employees.length == 0) {
            System.out.println("【警告】員工資料庫為空，無法查詢！");
            return null;
        }

        int low = 0;
        int high = employees.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (employees[mid].getId() == targetId) {
                return employees[mid];
            } else if (employees[mid].getId() < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // 5. 檢查陣列是否有重複編號
    public static boolean hasDuplicateIds(Employee[] employees) {
        if (employees == null) return false;
        for (int i = 0; i < employees.length - 1; i++) {
            if (employees[i].getId() == employees[i + 1].getId()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== 課後作業三：員工編號查詢系統 ===");

        // 2. 員工陣列必須依編號排序
        Employee[] employees = {
            new Employee(1001, "張小明", "資訊部", "801"),
            new Employee(1005, "李美麗", "人事部", "805"),
            new Employee(1012, "陳大華", "財務部", "812"),
            new Employee(1020, "林雅婷", "研發部", "820"),
            new Employee(1035, "黃志強", "業務部", "835")
        };

        // 檢查重複編號
        if (hasDuplicateIds(employees)) {
            System.out.println("【錯誤】資料庫中包含重複的員工編號，請修正資料！");
        } else {
            System.out.println("資料庫檢查合格：無重複編號。");
        }

        System.out.println("\n--- 開始查詢測試 ---");
        
        // 測試 1: 正常查詢
        int searchId1 = 1012;
        System.out.println("查詢編號 " + searchId1 + ":");
        Employee result1 = binarySearchEmployee(employees, searchId1);
        if (result1 != null) {
            System.out.println("找到員工: " + result1);
        } else {
            System.out.println("找不到編號為 " + searchId1 + " 的員工！");
        }

        // 5. 測試找不到情況
        int searchId2 = 9999;
        System.out.println("\n查詢編號 " + searchId2 + ":");
        Employee result2 = binarySearchEmployee(employees, searchId2);
        if (result2 != null) {
            System.out.println("找到員工: " + result2);
        } else {
            // 5. 找不到時的明確處理方式
            System.out.println("結果：找不到編號為 " + searchId2 + " 的員工！");
        }

        // 5. 測試空陣列
        System.out.println("\n測試空陣列查詢:");
        binarySearchEmployee(new Employee[]{}, 1001);
    }
}