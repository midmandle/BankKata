import java.util.List;

public class Account {
    private final LocalCalendar localCalendar;
    private ConsolePrinter printer;
    private TransactionLog transactionLog;

    public Account(LocalCalendar localCalendar, ConsolePrinter printer, TransactionLog transactionLog) {
        this.localCalendar = localCalendar;
        this.printer = printer;
        this.transactionLog = transactionLog;
    }

    public void deposit(int sum) {
        transactionLog.addDeposit(sum, localCalendar.getLocalDate());
    }

    public void withdraw(int sum) {
        transactionLog.addWithdrawal(sum, localCalendar.getLocalDate());
    }

    public void printStatement() {
        List<Transaction> transactions = (List<Transaction>) this.transactionLog.getTransactions();

        List<String> statementLines = AccountStatement.getLines(transactions);

        for (String line : statementLines) {
            this.printer.printLine(line);
        }
    }
}
