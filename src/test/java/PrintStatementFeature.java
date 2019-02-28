import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PrintStatementFeature {
    @Mock
    ConsolePrinter printerMock;

    @Test
    void an_account_can_print_a_statement() {
        Account account = new Account(printerMock);
        account.deposit(1000);
        account.deposit(2000);
        account.withdraw(500);
        account.printStatement();

        Mockito.verify(printerMock).printLine("date || credit || debit || balance");
        Mockito.verify(printerMock).printLine("14/01/2012 || || 500.00 || 2500.00");
        Mockito.verify(printerMock).printLine("13/01/2012 || 2000.00 || || 3000.00");
        Mockito.verify(printerMock).printLine("10/01/2012 || 1000.00 || || 1000.00");
    }
}
