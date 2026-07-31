// 1. Employee 包含編號、姓名、部門及分機
public class Employee {
    private int id;
    private String name;
    private String department;
    private String extension;

    public Employee(int id, String name, String department, String extension) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.extension = extension;
    }

    public int getId() {
        return id;
    }

    // 4. 顯示完整員工資料
    @Override
    public String toString() {
        return String.format("[員工編號: %d | 姓名: %-6s | 部門: %-6s | 分機: %s]", 
                             id, name, department, extension);
    }
}