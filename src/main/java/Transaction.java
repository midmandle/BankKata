import java.util.Objects;

public class Transaction {
    private final TransactionType type;
    private final int sum;

    public Transaction(TransactionType type, int sum) {
        this.type = type;
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return sum == that.sum &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sum);
    }
}
