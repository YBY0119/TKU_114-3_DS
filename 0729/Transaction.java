public class Transaction {
    private String id;
    private String accountNumber;
    private double amount;
    private int timeSeq; // 時間序號

    public Transaction(String id, String accountNumber, double amount, int timeSeq) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.timeSeq = timeSeq;
    }

    public String getId() { return id; }
    public String getAccountNumber() { return accountNumber; }
    public double getAmount() { return amount; }
    public int getTimeSeq() { return timeSeq; }

    @Override
    public String toString() {
        return String.format("交易編號: %-6s | 帳號: %-10s | 金額: %10.2f | 時間序號: %d",
                id, accountNumber, amount, timeSeq);
    }
}