import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
}
