import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    public final TransactionType type;
    public final int sum;
    public LocalDate date;

    public Transaction(TransactionType type, int sum, LocalDate date) {
        this.type = type;
        this.sum = sum;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return sum == that.sum &&
                type == that.type &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, sum, date);
    }
}

