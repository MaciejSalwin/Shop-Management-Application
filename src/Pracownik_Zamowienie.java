import java.time.LocalDate;

public class Pracownik_Zamowienie {
    private String numerZamowienia;
    private LocalDate dataRozpoczęcia;
    private LocalDate dataZakończenia;
    Osoba osoba;
    Zamowienie zamowienie;
    public Pracownik_Zamowienie(String numerZamowienia, LocalDate dataRozpoczęcia, LocalDate dataZakończenia) {
        this.numerZamowienia = numerZamowienia;
        this.dataRozpoczęcia = dataRozpoczęcia;
        this.dataZakończenia = dataZakończenia;
    }

    public Pracownik_Zamowienie(String numerZamowienia, LocalDate dataRozpoczęcia, LocalDate dataZakończenia, Osoba osoba, Zamowienie zamowienie) {
        this.numerZamowienia = numerZamowienia;
        this.dataRozpoczęcia = dataRozpoczęcia;
        this.dataZakończenia = dataZakończenia;
        this.osoba = osoba;
        this.zamowienie = zamowienie;
    }

    public String getNumerZamowienia() {
        return numerZamowienia;
    }

    public void setNumerZamowienia(String numerZamowienia) {
        this.numerZamowienia = numerZamowienia;
    }

    public LocalDate getDataRozpoczęcia() {
        return dataRozpoczęcia;
    }

    public void setDataRozpoczęcia(LocalDate dataRozpoczęcia) {
        this.dataRozpoczęcia = dataRozpoczęcia;
    }

    public LocalDate getDataZakończenia() {
        return dataZakończenia;
    }

    public void setDataZakończenia(LocalDate dataZakończenia) {
        this.dataZakończenia = dataZakończenia;
    }
}
