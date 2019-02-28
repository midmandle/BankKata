import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountShould {
    @Mock
    TransactionLog transactionLogMock;

    @Mock
    ConsolePrinter printer;

    @Test
    void deposit_a_sum() {
        Account account = new Account(printer, transactionLogMock);

        account.deposit(1);

        verify(transactionLogMock).addDeposit(1);
    }

    @Test
    void withdraw_a_sum() {
        Account account = new Account(printer, transactionLogMock);

        account.withdraw(1);

        verify(transactionLogMock).addWithdrawal(1);
    }

    @Test
    void print_a_blank_statement_given_no_transactions() {
        Account account = new Account(printer, transactionLogMock);
        String expectedHeader = "date || credit || debit || balance";

        account.printStatement();

        verify(printer).printLine(expectedHeader);
    }

    @Test
    void print_a_statement_with_one_line_per_transaction() {
        Account account = new Account(printer, transactionLogMock);
        String expectedHeader = "date || credit || debit || balance";
        String expectedStatementLine1 = "02/02/2019 || || 500.00 || 2500.00";
        String expectedStatementLine2 = "01/01/2019 || 2000.00 || || 3000.00";

        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();

        InOrder inOrder = Mockito.inOrder(printer);
        inOrder.verify(printer).printLine(expectedHeader);
        inOrder.verify(printer).printLine(expectedStatementLine1);
        inOrder.verify(printer).printLine(expectedStatementLine2);
    }
}
