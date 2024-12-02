import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
            String id = String.valueOf(ThreadLocalRandom.current().nextInt(10000));
            Documents.Passport p = new Documents.Passport("Паспорт РФ", "6094", "274543", LocalDate.of(2007, 6, 12), id, "6054", "Иванов Иван Иванович", LocalDate.of(1993, 6, 12), "Ростов на Дону", 'M');
            BankAccount ba = new BankAccount(id, p);
            ba.showDocuments();
            ba.deposit(BigDecimal.valueOf(-1));
            ba.deposit(BigDecimal.valueOf(30));
            ba.withdraw(BigDecimal.valueOf(3000));
        } catch (IllegalArgumentException | BankAccount.InsufficientFundsException e) {
            BankAccount.logger.log(Level.SEVERE, e.getMessage());
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}