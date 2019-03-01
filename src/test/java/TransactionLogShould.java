import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionLogShould {
    @Test
    void store_a_deposit() {
        List<Transaction> transactions = new ArrayList<>();
        TransactionLog transactionLog = new TransactionLog(transactions);
        LocalDate date = LocalDate.now();
        List<Transaction> expectedTransactions = asList(new Transaction(TransactionType.Deposit, 1, date));

        transactionLog.addDeposit(1, date);

        assertEquals(expectedTransactions, transactionLog.getTransactions());
    }

    @Test
    void store_a_withdrawal() {
        List<Transaction> transactions = new ArrayList<>();
        TransactionLog transactionLog = new TransactionLog(transactions);
        LocalDate date = LocalDate.now();
        List<Transaction> expectedTransactions = asList(new Transaction(TransactionType.Withdraw, 1, date));

        transactionLog.addWithdrawal(1, date);

        assertEquals(expectedTransactions, transactionLog.getTransactions());
    }
}
