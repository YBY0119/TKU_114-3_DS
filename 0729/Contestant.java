public class Contestant {
    private String id;
    private String name;
    private int score;
    private double timeInSeconds;

    public Contestant(String id, String name, int score, double timeInSeconds) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.timeInSeconds = timeInSeconds;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getScore() { return score; }
    public double getTimeInSeconds() { return timeInSeconds; }

    @Override
    public String toString() {
        return String.format("編號: %-6s | 姓名: %-8s | 分數: %3d | 完成秒數: %5.2f 秒", id, name, score, timeInSeconds);
    }
}