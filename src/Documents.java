import java.time.LocalDate;

public class Documents {
    public static abstract class IdDocType implements Document {
        final protected String name;
        final protected String series;
        final protected String number;
        final protected LocalDate issuedDate;
        final protected String ownerID;
        final protected String departmentCode;
        final protected char sex;

        public IdDocType(String name, String series, String number, LocalDate issuedDate, String ownerID, String departmentCode, char sex) {
            if (name == null || name.isBlank()
                    && series == null || series.isBlank()
                    && number == null || number.isBlank()
                    && ownerID == null || ownerID.isBlank()
                    && departmentCode == null || departmentCode.isBlank()) {
                throw new IllegalArgumentException("Строка не должна быть пуста");
            }
            if (sex != 'M' && sex != 'W')
                throw new IllegalArgumentException("Пол должен быть M или W");
            this.name = name;
            this.series = series;
            this.number = number;
            this.issuedDate = issuedDate;
            this.ownerID = ownerID;
            this.departmentCode = departmentCode;
            this.sex = sex;
        }

        @Override
        public String getName() {
            return name;
        }
        @Override
        public String getSeries() {
            return series;
        }
        @Override
        public String getNumber() {
            return number;
        }
        @Override
        public LocalDate getIssuedDate() {
            return issuedDate;
        }
        @Override
        public String getOwnerId() {
            return ownerID;
        }
        public String getDepartmentCode() {
            return departmentCode;
        }
        public char getSex() {
            return sex;
        }
    }

    public static class Passport extends IdDocType {
        final private String FIO;
        final private LocalDate birthDate;
        final private String birthPlace;

        public Passport(String name, String series, String number, LocalDate issuedDate, String ownerID, String departmentCode, String FIO, LocalDate birthDate, String birthPlace, char sex) {
            super(name, series, number, issuedDate, ownerID, departmentCode, sex);
            if (FIO == null || FIO.isBlank() && birthPlace == null || birthPlace.isBlank()) {
                throw new IllegalArgumentException("Строка не должна быть пуста");
            }
            this.FIO = FIO;
            this.birthDate = birthDate;
            this.birthPlace = birthPlace;
        }

        public String getFIO() {
            return FIO;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        @Override
        public String toString() {
            return "Passport{" +
                    "name='" + name + '\'' +
                    ", series='" + series + '\'' +
                    ", number='" + number + '\'' +
                    ", issuedDate=" + issuedDate +
                    ", ownerID='" + ownerID + '\'' +
                    ", departmentCode='" + departmentCode + '\'' +
                    ", FIO='" + FIO + '\'' +
                    ", birthDate=" + birthDate +
                    ", birthPlace='" + birthPlace + '\'' +
                    ", sex=" + sex +
                    '}';
        }
    }

    public static class InternationalPassport extends IdDocType {
        final private String nationality;
        final private LocalDate expiryDate;
        final private String birthPlace;

        public InternationalPassport(String name, String series, String number, LocalDate issuedDate, String ownerID, String departmentCode, String nationality, LocalDate expiryDate, String birthPlace, char sex) {
            super(name, series, number, issuedDate, ownerID, departmentCode, sex);
            if (birthPlace == null || birthPlace.isBlank() && nationality == null || nationality.isBlank()) {
                throw new IllegalArgumentException("Строка не должна быть пуста");
            }
            this.nationality = nationality;
            this.expiryDate = expiryDate;
            this.birthPlace = birthPlace;
        }

        public String getNationality() {
            return nationality;
        }
        @Override
        public LocalDate getExpiryDate() {
            return expiryDate;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        @Override
        public String toString() {
            return "Passport{" +
                    "name='" + name + '\'' +
                    ", series='" + series + '\'' +
                    ", number='" + number + '\'' +
                    ", issuedDate=" + issuedDate +
                    ", ownerID='" + ownerID + '\'' +
                    ", departmentCode='" + departmentCode + '\'' +
                    ", expiryDate=" + expiryDate +
                    ", birthDate='" + nationality + '\'' +
                    ", birthPlace='" + birthPlace + '\'' +
                    ", sex=" + sex +
                    '}';
        }
    }
}
