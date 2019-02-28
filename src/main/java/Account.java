import java.util.List;

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
        AccountStatement statement = new AccountStatement(transactionLog);

        List<String> statementLines = statement.getLines();

        for (String line : statementLines) {
            this.printer.printLine(line);
        }
    }
}
