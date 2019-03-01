import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrintStatementFeature {
    @Mock
    ConsolePrinter printerMock;
    @Mock
    LocalCalendar localCalendarMock;

    @Test
    void an_account_can_print_a_statement() {
        List<Transaction> transactions = new ArrayList<>();
        TransactionLog transactionLog = new TransactionLog(transactions);
        Account account = new Account(localCalendarMock, printerMock, transactionLog);
        when(localCalendarMock.getLocalDate()).thenReturn(LocalDate.of(2012, 1, 10))
            .thenReturn(LocalDate.of(2012, 1, 13))
            .thenReturn(LocalDate.of(2012, 1, 14));

        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();

        InOrder inOrder = Mockito.inOrder(printerMock);
        inOrder.verify(printerMock).printLine("date || credit || debit || balance");
        inOrder.verify(printerMock).printLine("14/01/2012 || || 500.00 || 2500.00");
        inOrder.verify(printerMock).printLine("13/01/2012 || 2000.00 || || 3000.00");
        inOrder.verify(printerMock).printLine("10/01/2012 || 1000.00 || || 1000.00");
    }
}
