import java.time.LocalDate;
import java.util.Collection;

public class TransactionLog {
    private Collection<Transaction> transactions;

    public TransactionLog(Collection<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addDeposit(int sum, LocalDate date) {
        this.transactions.add(new Transaction(TransactionType.Deposit, sum, date));
    }

    public void addWithdrawal(int sum, LocalDate date) {
        this.transactions.add(new Transaction(TransactionType.Withdraw, sum, date));
    }

    public Collection<Transaction> getTransactions() {
        return this.transactions;
    }
}
