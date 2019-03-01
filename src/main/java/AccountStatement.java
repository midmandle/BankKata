import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AccountStatement {
    private static final String STATEMENT_HEADER = "date || credit || debit || balance";

    public static List<String> getLines(List<Transaction> transactions) {
        List<String> statementLines = new ArrayList<>();
        statementLines.add(STATEMENT_HEADER);
        if(hasTransactions(transactions))
            statementLines.addAll(formatTransactions(transactions));
        return statementLines;
    }

    private static List<String> formatTransactions(List<Transaction> transactions) {
        List<String> formattedTransactions = new ArrayList<>();
        BigDecimal balance = new BigDecimal(0.00);
        balance.setScale(2, RoundingMode.HALF_UP);

        for (Transaction transaction :
                transactions) {
            balance = calculateBalance(balance, transaction);
            formattedTransactions.add(formatToStatementLine(transaction, balance.doubleValue()));
        }
        Collections.reverse(formattedTransactions);
        return formattedTransactions;
    }

    private static BigDecimal calculateBalance(BigDecimal balance, Transaction transaction) {
        if(isDepositTransaction(transaction))
            balance = balance.add(BigDecimal.valueOf(transaction.sum));

        if(isWithdrawalTransaction(transaction))
            balance = balance.subtract(BigDecimal.valueOf(transaction.sum));

        return balance;
    }

    private static String formatToStatementLine(Transaction transaction, double balance) {
        String formattedTransaction;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String formattedDate = transaction.date.format(formatter);

        formattedTransaction = formatTransaction(transaction, balance, formattedDate);
        return formattedTransaction;
    }

    private static String formatTransaction(Transaction transaction, double balance, String formattedDate) {
        String formattedTransaction = "";
        if(isDepositTransaction(transaction))
            formattedTransaction = formatDepositTransaction(transaction, balance, formattedDate);
        if(isWithdrawalTransaction(transaction))
            formattedTransaction = formatWithdrawalTransaction(transaction, balance, formattedDate);
        return formattedTransaction;
    }

    private static boolean hasTransactions(List<Transaction> transactions) {
        return transactions.size() > 0;
    }

    private static String formatWithdrawalTransaction(Transaction transaction, double balance, String formattedDate) {
        return String.format("%s || || %d.00 || %.2f", formattedDate, transaction.sum, balance);
    }

    private static String formatDepositTransaction(Transaction transaction, double balance, String formattedDate) {
        return String.format("%s || %d.00 || || %.2f", formattedDate, transaction.sum, balance);
    }

    private static boolean isWithdrawalTransaction(Transaction transaction) {
        return transaction.type == TransactionType.Withdraw;
    }

    private static boolean isDepositTransaction(Transaction transaction) {
        return transaction.type == TransactionType.Deposit;
    }
}
