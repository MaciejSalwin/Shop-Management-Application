public enum OsobaTyp {
    PRACOWNIK("Pracownik"),
    KLIENT("Klient");

    private String value;

    OsobaTyp(String value) {
        this.value = value;
    }

    public static OsobaTyp fromString(String typ) {
        String osobaTyp = typ.toUpperCase();
        if (osobaTyp.equals("PRACOWNIK")) {
            return PRACOWNIK;
        } else if (osobaTyp.equals("KLIENT")) {
            return KLIENT;
        } else {
            throw new IllegalArgumentException("Nieznany typ osoby: " + typ);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    public boolean isKlient() {
        return this == KLIENT;
    }

    public boolean isPracownik() {
        return this == PRACOWNIK;
    }

    public boolean canBeKlient() {
        return this == PRACOWNIK;
    }

    public boolean canBePracownik() {
        return true;
    }
}
