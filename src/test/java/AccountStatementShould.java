import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountStatementShould {
    @ParameterizedTest
    @MethodSource("transactionsGenerator")
    void generate_statement_lines(List<String> expectedStatement, List<Transaction> transactions) {
        assertEquals(expectedStatement, AccountStatement.getLines(transactions));
    }

    private static Stream<Arguments> transactionsGenerator(){
        String header = "date || credit || debit || balance";
        Transaction transaction_deposit_1 = new Transaction(TransactionType.Deposit, 1, LocalDate.of(2019, 02, 28));
        String deposit1 = "28/02/2019 || 1.00 || || 1.00";
        String deposit2 = "28/02/2019 || 1.00 || || 2.00";
        return Stream.of(
                Arguments.of(asList(header), asList()),
                Arguments.of(asList(header, deposit1), asList(transaction_deposit_1)),
                Arguments.of(asList(header, deposit2, deposit1), asList(transaction_deposit_1, transaction_deposit_1))
        );
    }
}
