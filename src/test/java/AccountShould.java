import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountShould {
    @Mock
    TransactionLog transactionLogMock;

    @Mock
    ConsolePrinter printerMock;

    @Mock
    LocalCalendar localCalendarMock;

    @Test
    void deposit_a_sum() {
        Account account = new Account(localCalendarMock, printerMock, transactionLogMock);
        LocalDate date = LocalDate.now();
        when(localCalendarMock.getLocalDate()).thenReturn(date);

        account.deposit(1);

        verify(transactionLogMock).addDeposit(1, date);
    }

    @Test
    void withdraw_a_sum() {
        Account account = new Account(localCalendarMock, printerMock, transactionLogMock);
        LocalDate date = LocalDate.now();
        when(localCalendarMock.getLocalDate()).thenReturn(date);

        account.withdraw(1);

        verify(transactionLogMock).addWithdrawal(1, date);
    }

    @Test
    void print_a_blank_statement_given_no_transactions() {
        Account account = new Account(localCalendarMock, printerMock, transactionLogMock);
        String expectedHeader = "date || credit || debit || balance";

        account.printStatement();

        verify(printerMock).printLine(expectedHeader);
    }

    @Test
    void print_a_statement_with_one_line_per_transaction() {
        Account account = new Account(localCalendarMock, printerMock, transactionLogMock);
        String expectedHeader = "date || credit || debit || balance";
        String expectedStatementLine1 = "02/02/2019 || || 500.00 || 1500.00";
        String expectedStatementLine2 = "01/01/2019 || 2000.00 || || 2000.00";
        when(transactionLogMock.getTransactions()).thenReturn(asList(
                new Transaction(TransactionType.Deposit, 2000, LocalDate.of(2019, 01, 01)),
                new Transaction(TransactionType.Withdraw, 500, LocalDate.of(2019, 02, 02))
        ));

        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();

        InOrder inOrder = Mockito.inOrder(printerMock);
        inOrder.verify(printerMock).printLine(expectedHeader);
        inOrder.verify(printerMock).printLine(expectedStatementLine1);
        inOrder.verify(printerMock).printLine(expectedStatementLine2);
    }
}
