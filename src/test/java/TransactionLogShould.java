import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionLogShould {
    @Test
    void store_a_deposit() {
        List<Transaction> transactions = new ArrayList<>();
        TransactionLog transactionLog = new TransactionLog(transactions);
        List<Transaction> expectedTransactions = asList(new Transaction(TransactionType.Deposit, 1));

        transactionLog.addDeposit(1);

        assertEquals(expectedTransactions, transactionLog.getTransactions());
    }

    @Test
    void store_a_withdrawal() {
        List<Transaction> transactions = new ArrayList<>();
        TransactionLog transactionLog = new TransactionLog(transactions);
        List<Transaction> expectedTransactions = asList(new Transaction(TransactionType.Withdraw, 1));

        transactionLog.addWithdrawal(1);

        assertEquals(expectedTransactions, transactionLog.getTransactions());
    }
}
