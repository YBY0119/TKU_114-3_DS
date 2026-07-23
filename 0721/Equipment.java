public class Equipment {
    private String code;
    private String name;
    private boolean isAvailable;

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
        this.isAvailable = true; // 預設可借用
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return String.format("代碼: %-8s | 名稱: %-12s | 狀態: %s", 
                code, name, (isAvailable ? "可借用" : "已借出"));
    }
}