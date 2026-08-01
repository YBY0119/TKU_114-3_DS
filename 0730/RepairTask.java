public class RepairTask {
    private String id;
    private String deviceName;
    private int priority; // 數字越大優先度越高
    private int orderIndex; // 登記順序

    public RepairTask(String id, String deviceName, int priority, int orderIndex) {
        this.id = id;
        this.deviceName = deviceName;
        this.priority = priority;
        this.orderIndex = orderIndex;
    }

    public String getId() { return id; }
    public String getDeviceName() { return deviceName; }
    public int getPriority() { return priority; }
    public int getOrderIndex() { return orderIndex; }

    @Override
    public String toString() {
        return String.format("Task[ID=%s, Device='%s', Priority=%d, Order=%d]", 
                id, deviceName, priority, orderIndex);
    }
}