public class Account {
    private ConsolePrinter printer;

    public Account(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void deposit(int sum) {
        throw new UnsupportedOperationException();
    }

    public void withdraw(int sum) {
        throw new UnsupportedOperationException();
    }

    public void printStatement() {
        throw new UnsupportedOperationException();
    }
}
