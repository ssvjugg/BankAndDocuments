import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

final public class BankAccount {
    final public static Logger logger = Logger.getLogger(BankAccount.class.getName());

    public enum TypeOfClient {
        PE("Физическое лицо"), LE("Юридическое лицо"), SB("Малый бизнес");
        private final String abbreviation;
        private TypeOfClient(String abbreviation) {
            this.abbreviation = abbreviation;
        }
        public String getAbbreviation() {
            return abbreviation;
        }
    }

    private BigDecimal balance;

    final private List<Document> documents = new ArrayList<>();

    final private String ownerId;

    private TypeOfClient typeOfClient;

    public BankAccount(String ownerId, Document d) {
        if (ownerId == null || ownerId.isBlank())
            throw new IllegalArgumentException("Строка не должна быть пуста");
        this.ownerId = ownerId;
        balance = BigDecimal.valueOf(0);
        if (d instanceof Documents.Passport p) {
            System.out.println("Для регистрации был предъявлен паспорт: " + p.getSeries() + " " + p.getNumber());
            typeOfClient = TypeOfClient.PE;
            documents.add(p);
        } else if (d instanceof Documents.InternationalPassport p) {
            System.out.println("Для регистрации был предъявлен загранпаспорт" + p.getSeries() + " " + p.getNumber());
            typeOfClient = TypeOfClient.PE;
            documents.add(p);
        } else {
            throw new IllegalArgumentException("Предъявлено неверное удостоверение личности");
        }
    }

    /**
     * Эта функция увеличивает значение кошелька на amount
     * @param amount
     */
    public void deposit(BigDecimal amount) {
        assert amount.compareTo(BigDecimal.ZERO) >= 0 : "Сумма пополнения должна быть положительной";
        balance = balance.add(amount);
        logger.info("Произведено пополнение на сумму: " + amount);
    }

    /**
     * Эта функция уменьшает значение кошелька на amount и если значение balance < amount то выбрасывает исключение
     * @param amount
     * @return
     * @throws InsufficientFundsException
     */
    public BigDecimal withdraw(BigDecimal amount) throws InsufficientFundsException {
        assert amount.compareTo(BigDecimal.ZERO) >= 0 : "Сумма снятия должна быть положительной";
        if (amount.compareTo(balance) >= 0) {
            throw new InsufficientFundsException("Недостаточно средств на счету номер - " + ownerId);
        }
        balance = balance.subtract(amount);
        logger.info("Произведено снятие на сумму: " + amount);
        return amount;
    }

    /**
     * Эта функция совершает перевод на сумму amount. Кошелек который получает сумму указывается в other.
     * В случае balance < amount выбрасывает исключение
     * @param other
     * @param amount
     * @throws InsufficientFundsException
     */
    public void makeTransaction(BankAccount other, BigDecimal amount) throws InsufficientFundsException {
        assert other != null : "Кошелек получателя не должен быть null";
        assert amount.compareTo(BigDecimal.ZERO) >= 0 : "Сумма перевода должна быть положительной";
        BigDecimal t = withdraw(amount);
        other.deposit(t);
        logger.info("Произведена транзакция на счет - " + other + " сумма транзакции: " + amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void showDocuments() {
        StringBuilder sb = new StringBuilder();
        for (Document d : documents) {
            sb.append(d.getName()).append('\n').append(d.getSeries()).append('\n').append(d.getNumber()).append('\n').append(d.getIssuedDate()).append('\n');
            if (d instanceof Documents.InternationalPassport p)
                sb.append(p.getIssuedDate().toString()).append('\n');
        }
        System.out.println(sb);
    }

    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static class InsufficientFundsException extends Exception {
        public InsufficientFundsException(String message) {
            super(message);
        }
    }
}
