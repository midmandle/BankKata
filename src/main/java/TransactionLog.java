import java.util.Collection;

public class TransactionLog {
    private Collection<Transaction> transactions;

    public TransactionLog(Collection<Transaction> transactions) {
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
