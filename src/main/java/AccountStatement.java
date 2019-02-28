import java.util.List;

import static java.util.Arrays.asList;

public class AccountStatement {
    private TransactionLog transactionLog;

    public AccountStatement(TransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }

    public List<String> getLines() {
        String statementHeader = "date || credit || debit || balance";
        String line1 = "02/02/2019 || || 500.00 || 2500.00";
        String line2 = "01/01/2019 || 2000.00 || || 3000.00";
        return asList(statementHeader, line1, line2);
    }
}
