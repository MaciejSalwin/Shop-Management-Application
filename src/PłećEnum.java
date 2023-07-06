
public enum PłećEnum {
    MĘŻCZYZNA("MĘŻCZYZNA"),
    KOBIETA("KOBIETA");

    private final String value;

    PłećEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PłećEnum fromString(String płeć) {
        String płećEnum = płeć.toUpperCase();
        for (PłećEnum płećValue : PłećEnum.values()) {
            if (płećValue.getValue().equals(płećEnum)) {
                return płećValue;
            }
        }
        throw new IllegalArgumentException("Nieprawidłowa wartość płci: " + płeć);
    }
}
