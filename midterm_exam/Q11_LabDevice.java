public class Q11_LabDevice {
    private String code;
    private String name;
    private int usageHours;
    private boolean active;

    // 1. 建構子驗證與初始化
    public Q11_LabDevice(
        String code,
        String name,
        int usageHours,
        boolean active
    ) {
        // code 驗證
        if (code == null || code.trim().isEmpty()) {
            this.code = "UNKNOWN";
        } else {
            this.code = code.trim(); // 儲存去空白後的代碼
        }

        // name 驗證
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unnamed";
        } else {
            this.name = name.trim(); // 儲存去空白後的名稱
        }

        // usageHours 驗證
        if (usageHours < 0) {
            this.usageHours = 0;
        } else {
            this.usageHours = usageHours;
        }

        this.active = active;
    }

    // 2. Getter 方法
    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public int getUsageHours() {
        return this.usageHours;
    }

    public boolean isActive() {
        return this.active;
    }

    // 3. Setter 與行為方法
    public void setName(String name) {
        // 只接受非 null 且非空白的名稱，不合法時保留原名稱
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        }
    }

    public void addUsageHours(int hours) {
        // 只接受大於 0 的時數
        if (hours > 0) {
            this.usageHours += hours;
        }
    }

    public void deactivate() {
        // 將設備狀態改為停用
        this.active = false;
    }

    public boolean needsMaintenance() {
        // 使用時數大於或等於 100 時，回傳 true
        return this.usageHours >= 100;
    }

    // 4. 格式化 toString 輸出
    @Override
    public String toString() {
        String status = this.active ? "active" : "inactive";
        return this.code + " | " + this.name + " | " + this.usageHours + " hours | " + status;
    }

    // 5. 完全保留原始測試 main 方法
    public static void main(String[] args) {
        Q11_LabDevice device = new Q11_LabDevice(
            " D01 ",
            " Printer ",
            90,
            true
        );

        device.addUsageHours(30);
        device.addUsageHours(-5); // 負數不應該被加入
        device.setName(" 3D Printer "); // 應更新並去除空白

        System.out.println(device);
        System.out.println("需要保養 : " + device.needsMaintenance());

        device.deactivate();
        System.out.println("啟用狀態 : " + device.isActive());
    }
}