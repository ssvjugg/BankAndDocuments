import java.time.LocalDate;

public interface Document {
    String getName();
    String getSeries();
    String getNumber();
    LocalDate getIssuedDate();
    default LocalDate getExpiryDate() {
        return null;
    }
    String getOwnerId();
}

