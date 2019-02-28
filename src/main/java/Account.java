public class Account {
    private ConsolePrinter printer;
    private TransactionLog transactionLog;

    public Account(ConsolePrinter printer, TransactionLog transactionLog) {
        this.printer = printer;
        this.transactionLog = transactionLog;
    }

    public void deposit(int sum) {
        transactionLog.addDeposit(sum);
    }

    public void withdraw(int sum) {
        transactionLog.addWithdrawal(sum);
    }

    public void printStatement() {
        throw new UnsupportedOperationException();
    }
}
