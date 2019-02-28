import java.util.Collection;
import java.util.List;

public class TransactionLog {
    private List<Transaction> transactions;

    public TransactionLog(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addDeposit(int sum) {
        this.transactions.add(new Transaction(TransactionType.Deposit, sum));
    }

    public void addWithdrawal(int sum) {
        this.transactions.add(new Transaction(TransactionType.Withdraw, 1));
    }

    public Collection<Transaction> getTransactions() {
        return this.transactions;
    }
}
