import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 * ------Atrybyt Złożony(dataZamówienia),Asocjacja Kwalifikowana(dodajZamówienie, znajdzZamówienie),Asocjacja z atrybutem(PracownikZamowienie)
 */
public class Zamowienie {
    private LocalDate dataZamowienia;
    private LocalDate planowanyTerminRealizacji;
    private String statusPrzesylki;
    private String numerZamowienia;
    public List<Pracownik_Zamowienie> pracownikZamówienie;

    private Map<String, Zamowienie> zamowienieQualif = new HashMap<>();

    public Zamowienie() {

    }

    public Zamowienie(LocalDate dataZamowienia, LocalDate planowanyTerminRealizacji, String statusPrzesylki) {
        this.dataZamowienia = dataZamowienia;
        this.planowanyTerminRealizacji = planowanyTerminRealizacji;
        this.statusPrzesylki = statusPrzesylki;
    }
    public Zamowienie(LocalDate dataZamowienia, LocalDate planowanyTerminRealizacji, String statusPrzesylki, Pracownik_Zamowienie pracownikZamówienie) {
        this.dataZamowienia = dataZamowienia;
        this.planowanyTerminRealizacji = planowanyTerminRealizacji;
        this.statusPrzesylki = statusPrzesylki;
        this.pracownikZamówienie = new ArrayList<>(); // Utwórz nową instancję listy
        this.pracownikZamówienie.add(pracownikZamówienie);
    }

    public void dodajZamowienieQualif(Scanner scanner) {
        System.out.print("Wprowadź numer zamówienia: ");
        String numerZamowieniaInput = scanner.nextLine();
        if (!zamowienieQualif.containsKey(numerZamowieniaInput)) {
            try {
                Zamowienie zamowienie = wprowadzDaneZamowienia(scanner);
                zamowienieQualif.put(numerZamowieniaInput, zamowienie);
                System.out.println("Zamówienie dodane.");
            } catch (DateTimeParseException e) {
                System.out.println("Nieprawidłowy format daty. Zamówienie nie zostało dodane.");
            }
        } else {
            System.out.println("Zamówienie o podanym numerze już istnieje.");
        }
    }

    public Zamowienie znajdzZamowienieQualif(Scanner scanner) throws Exception {
        System.out.print("Wprowadź numer zamówienia: ");
        String numerZamowieniaInput = scanner.nextLine();
        try {
            if (zamowienieQualif.containsKey(numerZamowieniaInput)) {
                return zamowienieQualif.get(numerZamowieniaInput);
            } else {
                throw new Exception("Nie można znaleźć zamówienia o numerze: " + numerZamowieniaInput);
            }
        } catch (NoSuchElementException e) {
            throw new Exception("Nie można znaleźć zamówienia o numerze: " + numerZamowieniaInput);
        }
    }

    private Zamowienie wprowadzDaneZamowienia(Scanner scanner) {
        System.out.print("Wprowadź datę zamówienia (RRRR-MM-DD): ");
        String dataZamowieniaStr = scanner.nextLine();
        LocalDate dataZamowienia = LocalDate.parse(dataZamowieniaStr);

        System.out.print("Wprowadź planowany termin realizacji (RRRR-MM-DD): ");
        String planowanyTerminRealizacjiStr = scanner.nextLine();
        LocalDate planowanyTerminRealizacji = LocalDate.parse(planowanyTerminRealizacjiStr);

        System.out.print("Wprowadź status przesyłki: ");
        String statusPrzesylki = scanner.nextLine();

        return new Zamowienie(dataZamowienia, planowanyTerminRealizacji, statusPrzesylki);
    }

    public LocalDate getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(LocalDate dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public LocalDate getPlanowanyTerminRealizacji() {
        return planowanyTerminRealizacji;
    }

    public void setPlanowanyTerminRealizacji(LocalDate planowanyTerminRealizacji) {
        this.planowanyTerminRealizacji = planowanyTerminRealizacji;
    }

    public String getStatusPrzesylki() {
        return statusPrzesylki;
    }

    public void setStatusPrzesylki(String statusPrzesylki) {
        this.statusPrzesylki = statusPrzesylki;
    }

    public String getNumerZamowienia() {
        return numerZamowienia;
    }

    public void setNumerZamowienia(String numerZamowienia) {
        this.numerZamowienia = numerZamowienia;
    }
}
