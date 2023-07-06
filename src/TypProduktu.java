public enum TypProduktu {

    ARTYKUŁY_BIUROWE("Linux", "ARTYKUŁY_BIUROWE"),
    ARTYKUŁY_GAMINGOWE("Logitec", "ARTYKUŁY_GAMINGOWE"),
    ARTYKUŁY_DLA_PASJONATÓW("Ender", "ARTYKUŁY_DLA_PASJONATÓW");

    private String producent;
    private String kategoria;

    TypProduktu(String producent, String kategoria) {
        this.producent = producent;
        this.kategoria = kategoria;
    }

    public String getProducent() {
        return producent;
    }

    public String getKategoria() {
        return kategoria;
    }

    @Override
    public String toString() {
        return kategoria + " - " + producent;
    }

    public static TypProduktu fromString(String kategoria, String producent) {
        for (TypProduktu typ : TypProduktu.values()) {
            if (typ.getKategoria().equalsIgnoreCase(kategoria) && typ.getProducent().equalsIgnoreCase(producent)) {
                return typ;
            }
        }
        throw new IllegalArgumentException("Nieznany typ produktu: " + producent + " - " + kategoria);
    }

    public static String[] getKategorie() {
        TypProduktu[] typyProduktow = TypProduktu.values();
        String[] kategorie = new String[typyProduktow.length];
        for (int i = 0; i < typyProduktow.length; i++) {
            kategorie[i] = typyProduktow[i].getKategoria();
        }
        return kategorie;
    }

    public static String[] getProducenci() {
        TypProduktu[] typyProduktow = TypProduktu.values();
        String[] producenci = new String[typyProduktow.length];
        for (int i = 0; i < typyProduktow.length; i++) {
            producenci[i] = typyProduktow[i].getProducent();
        }
        return producenci;
    }
}
