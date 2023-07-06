import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * ------Atrybut opcjonalny(DrugieImię), Atrybut powtarzalny(adresEmail), Ekstensja(WszystkieOsoby), Ekstensja trwałość(writeExntend)
 * ------Metoda klasowa(InfoOOosba), Atrybut pochodny(obliczWynagrodzenie), Przeciążenie metody(Oblicz Wynagrodzenie)
 * ------Asocjacja z Atrybutem(pracownikZamowienie)
 * ------Ograniczenie Atrybutu(SetIndywidualnaStawkaGodzinowa), Ograniczenie Unique(PESEL)
 * ------4
 */
public class Osoba {
    private String imie;
    private String nazwisko;
    private String PESEL;
    private List<String> adresEmail;
    private Date dataUrodzenia;
    private String Ulica;
    private String numerMieszkania;
    private String miasto;
    private String kodPocztowy;
    private String numerTelefony;
    private Date dataZatrudnienia;
    private int indywidualnaStawkaGodzinowa;
    private int liczbaGodzinPrzepracowanawMiesiącu;
    private int minimalnaStawkaZaGodzine = 30;
    private Płeć płeć;
    private static Set<String> unikalnePESEL = new HashSet<>();
    private Optional<List<String>> drugieImie;
    private OsobaTyp typ; // Pole reprezentujące typ osoby

    private static List<Osoba> wszystkieOsoby = new ArrayList<>();
    public static List<Osoba> wszyscyPracownicy = new ArrayList<>();

    //-----------------------ASOCJACJA Z ATRYBUTEM------------
    public List<Pracownik_Zamowienie> pracownikZamówienie;
    private List<String> informacjePracy;

    //-----------------------ASOCJACJA Z ATRYBUTEM------------
    public Osoba(int liczbaGodzinPrzepracowanych, int stawkaGodzinowa, Pracownik_Zamowienie pracownikZamówienie) {
        this.liczbaGodzinPrzepracowanawMiesiącu = liczbaGodzinPrzepracowanych;
        this.indywidualnaStawkaGodzinowa = stawkaGodzinowa;
        this.pracownikZamówienie = new ArrayList<>(); // nowa instancja listy
        this.pracownikZamówienie.add(pracownikZamówienie);

    }

    //--------------------------------------------------------
    public Osoba(OsobaTyp typ) {
        this.typ = typ;
    }

    public Osoba() {
        this.informacjePracy = new ArrayList<>();
    }

    public void ustawDrugieImie(List<String> drugieImie) {
        this.drugieImie = Optional.ofNullable(drugieImie);
    }

    public void wpiszDane() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj typ osoby (PRACOWNIK/KLIENT): ");
        String typOsobyString = scanner.nextLine().toUpperCase();

        try {
            OsobaTyp typOsoby = OsobaTyp.fromString(typOsobyString);
            setTyp(typOsoby);
        } catch (IllegalArgumentException e) {
            System.out.println("Niepoprawny typ osoby. Ustawiono domyślny typ: KLIENT");
            setTyp(OsobaTyp.KLIENT);
        }
        System.out.print("Podaj płeć (MĘŻCZYZNA/KOBIETA): ");
        String płećString = scanner.nextLine().toUpperCase();

        try {
            PłećEnum płećEnum = PłećEnum.fromString(płećString);
            setPłeć(new Płeć(płećEnum));
        } catch (IllegalArgumentException e) {
            System.out.println("Niepoprawna płeć. Ustawiono domyślną wartość: MĘŻCZYZNA");
            setPłeć(new Płeć(PłećEnum.MĘŻCZYZNA));
        }
        System.out.print("Podaj imię: ");
        String imie = scanner.nextLine();
        setImie(imie);

        System.out.print("Podaj drugie imię (lub naciśnij enter, aby pominąć): ");
        String drugieImie = scanner.nextLine();
        if (!drugieImie.isBlank()) {
            setDrugieImie(Optional.of(drugieImie));
        } else {
            setDrugieImie(Optional.empty());
        }

        System.out.print("Podaj nazwisko: ");
        String nazwisko = scanner.nextLine();
        setNazwisko(nazwisko);

        System.out.print("Podaj swoją datę urodzenia (yyyy-MM-dd): ");
        String dataUrodzeniaString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dataUrodzenia = LocalDate.parse(dataUrodzeniaString, formatter);
        setDataUrodzenia(Date.from(dataUrodzenia.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        // Sprawdzenie wieku pracownika
        int wiek = Period.between(dataUrodzenia, LocalDate.now()).getYears();
        if (typ == OsobaTyp.PRACOWNIK && wiek < 19) {
            System.out.println("Osoba jest za młoda, aby być pracownikiem.");
            return;
        }

        System.out.print("Podaj PESEL: ");
        String pesel = scanner.nextLine();
        if (!sprawdzUnikalnoscPESEL(pesel)) {
            System.out.println("Nie można dodać osoby. PESEL jest zdublowany.");
            return;
        }
        setPESEL(pesel);

        System.out.print("Podaj ulicę: ");
        String ulica = scanner.nextLine();
        setUlica(ulica);

        System.out.print("Podaj numer mieszkania: ");
        String numerMieszkania = scanner.nextLine();
        setNumerMieszkania(numerMieszkania);

        System.out.print("Podaj miasto: ");
        String miasto = scanner.nextLine();
        setMiasto(miasto);

        System.out.print("Podaj kod pocztowy: ");
        String kodPocztowy = scanner.nextLine();
        setKodPocztowy(kodPocztowy);

        System.out.print("Podaj numer telefonu: ");
        String numerTelefonu = scanner.nextLine();
        setNumerTelefony(numerTelefonu);

        System.out.print("Podaj adres email: ");
        List<String> adresyEmail = new ArrayList<>();
        String adresEmail = scanner.nextLine();
        adresyEmail.add(adresEmail);

        System.out.print("Czy chcesz dodać kolejny adres email? (tak/nie): ");
        String odpowiedz = scanner.nextLine();
        while (odpowiedz.equals("tak")) {
            System.out.print("Podaj adres email: ");
            adresEmail = scanner.nextLine();
            adresyEmail.add(adresEmail);
            System.out.print("Czy chcesz dodać kolejny adres email? (tak/nie): ");
            odpowiedz = scanner.nextLine();
        }

        setAdresEmail(adresyEmail);
        wszystkieOsoby.add(this);
        if (typ == OsobaTyp.PRACOWNIK) { // Dodajemy osobę tylko do listy pracowników, jeśli jej typ to PRACOWNIK
            wszyscyPracownicy.add(this);
        }
    }

    private boolean sprawdzUnikalnoscPESEL(String pesel) {
        if (unikalnePESEL.contains(pesel)) {
            return false;
        } else {
            unikalnePESEL.add(pesel);
            return true;
        }
    }

    public static void printInfoOsoby() {
        if (wszystkieOsoby.isEmpty()) {
            System.out.println("Brak danych Osób.");
        } else {
            System.out.println("Dane osób:");
            System.out.println("----------------------------");
            for (Osoba k : wszystkieOsoby) {
                System.out.println(k.toString());
                System.out.println("----------------------------");
            }

        }
    }

    //Estensja Trwałość
    public static void writeExtent() {
        try {
            FileOutputStream fileOut = new FileOutputStream("Osoba.bin");
            DataOutputStream out = new DataOutputStream(fileOut);
            out.writeInt(wszystkieOsoby.size());

            for (Osoba osoba : wszystkieOsoby) {
                osoba.write(out);
                System.out.println("Typ osoby: " + osoba.getTyp());
                System.out.println(osoba);
                System.out.println("-------------------------");
            }
            out.close();
            fileOut.close();
            System.out.println("Dane osoby zostały zapisane.");
        } catch (IOException i) {
            System.out.println("Wystąpił błąd podczas zapisywania danych klientów.");
            i.printStackTrace();
        }
    }

    public static void readExtent() {
        try {
            FileInputStream fileIn = new FileInputStream("Osoba.bin");
            DataInputStream in = new DataInputStream(fileIn);

            int liczbaKlientow = in.readInt();
            System.out.println("Liczba wczytanych obiektów: " + liczbaKlientow);

            // Odczytujemy dane każdego klienta i dodajemy go do listy klientów
            for (int i = 0; i < liczbaKlientow; i++) {
                Osoba osoba = new Osoba();
                osoba.read(in);
                wszystkieOsoby.add(osoba);

                System.out.println("Typ osoby: " + osoba.getTyp());
                System.out.println(osoba);
                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void write(DataOutputStream stream) throws IOException {
        stream.writeUTF(getTyp().toString());
        stream.writeUTF(getImie());

        // Dodajemy zapis drugiego imienia
        Optional<String> drugieImie = getDrugieImie();
        if (drugieImie.isPresent()) {
            stream.writeUTF(drugieImie.get());
        } else {
            stream.writeUTF(""); // Puste drugie imię, jeśli nie jest obecne
        }

        stream.writeUTF(getNazwisko());
        stream.writeUTF(getPESEL());
        stream.writeInt(getAdresEmail().size());
        for (String email : getAdresEmail()) {
            stream.writeUTF(email);
        }
        stream.writeLong(getDataUrodzenia().getTime());
        stream.writeUTF(getUlica());
        stream.writeUTF(getNumerMieszkania());
        stream.writeUTF(getMiasto());
        stream.writeUTF(getKodPocztowy());
        stream.writeUTF(getNumerTelefony());

        // Zapisujemy informację o płci
        stream.writeUTF(getPłeć().toString());

        System.out.println("Typ osoby: " + getTyp());
    }

    public void read(DataInputStream stream) throws IOException {
        String typString = stream.readUTF();
        this.setTyp(OsobaTyp.fromString(typString));
        this.setImie(stream.readUTF());
        String drugieImieString = stream.readUTF();
        if (!drugieImieString.isEmpty()) {
            this.setDrugieImie(Optional.of(drugieImieString));
        } else {
            this.setDrugieImie(Optional.empty());
        }

        this.setNazwisko(stream.readUTF());
        this.setPESEL(stream.readUTF());

        int emailCount = stream.readInt();
        List<String> emails = new ArrayList<>();
        for (int i = 0; i < emailCount; i++) {
            emails.add(stream.readUTF());
        }
        this.setAdresEmail(emails);

        long dataUrodzeniaMillis = stream.readLong();
        this.setDataUrodzenia(new Date(dataUrodzeniaMillis));

        this.setUlica(stream.readUTF());
        this.setNumerMieszkania(stream.readUTF());
        this.setMiasto(stream.readUTF());
        this.setKodPocztowy(stream.readUTF());
        this.setNumerTelefony(stream.readUTF());

        String płećString = stream.readUTF();
        try {
            PłećEnum płećEnum = PłećEnum.fromString(płećString);
            this.setPłeć(new Płeć(płećEnum));
        } catch (IllegalArgumentException e) {
            System.out.println();
            this.setPłeć(new Płeć(PłećEnum.MĘŻCZYZNA));
        }

    }

    public int obliczWiek() {
        LocalDate dzisiaj = LocalDate.now();
        LocalDate dataUrodzeniaLocalDate = dataUrodzenia.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period roznica = Period.between(dataUrodzeniaLocalDate, dzisiaj);
        return roznica.getYears();
    }

    public int obliczWynagrodzenie() {
        if (typ == OsobaTyp.PRACOWNIK) {
            return minimalnaStawkaZaGodzine * liczbaGodzinPrzepracowanawMiesiącu;
        } else {
            throw new UnsupportedOperationException("Metoda obliczWynagrodzenie dostępna tylko dla pracowników");
        }
    }

    public int obliczWynagrodzenie(int indywidualnaStawkaGodzinowa) {
        if (typ == OsobaTyp.PRACOWNIK) {
            if (indywidualnaStawkaGodzinowa >= 70) {
                throw new UnsupportedOperationException("Stawka jest za duża");
            }
            return indywidualnaStawkaGodzinowa * liczbaGodzinPrzepracowanawMiesiącu;
        } else {
            throw new UnsupportedOperationException("Metoda obliczWynagrodzenie dostępna tylko dla pracowników");
        }
    }

    public static void wyswietlKlientow() {
        List<Osoba> klienci = new ArrayList<>();
        for (Osoba osoba : wszystkieOsoby) {
            if (osoba.getTyp() == OsobaTyp.KLIENT) {
                klienci.add(osoba);
            }
        }

        if (klienci.isEmpty()) {
            System.out.println("Brak danych klientów.");
        } else {
            System.out.println("Klienci:");
            System.out.println("-------------------------");
            for (Osoba klient : klienci) {
                System.out.println(klient.toString());
            }
            System.out.println("-------------------------");
        }
    }

    public static void wyswietlPracownikow() {
        List<Osoba> pracownicy = new ArrayList<>();
        for (Osoba osoba : wszystkieOsoby) {
            if (osoba.getTyp() == OsobaTyp.PRACOWNIK) {
                pracownicy.add(osoba);
            }
        }

        if (pracownicy.isEmpty()) {
            System.out.println("Brak danych pracowników.");
        } else {
            System.out.println("Pracownicy:");
            System.out.println("-------------------------");
            for (Osoba pracownik : pracownicy) {
                System.out.println(pracownik.toString());
            }
            System.out.println("-------------------------");
        }
    }

    public void wybierzTypOsoby() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Wybierz typ osoby (PRACOWNIK/KLIENT): ");
        String typOsobyString = scanner.nextLine().toUpperCase();

        try {
            OsobaTyp typOsoby = OsobaTyp.fromString(typOsobyString);
            setTyp(typOsoby);
        } catch (IllegalArgumentException e) {
            System.out.println("Niepoprawny typ osoby. Ustawiono domyślny typ: KLIENT");
            setTyp(OsobaTyp.KLIENT);
        }
    }


    public void dodajDatePracy(String numerZamowienia, LocalDate dataRozpoczecia, LocalDate dataZakonczenia) {
        String informacje = "Numer zamówienia: " + numerZamowienia +
                ", Data rozpoczęcia: " + dataRozpoczecia +
                ", Data zakończenia: " + dataZakonczenia;
        informacjePracy.add(informacje);
    }

    public void zczytajInformacjePracy() {
        for (String informacje : informacjePracy) {
            System.out.println(informacje);
        }
    }

    public void zakupProdukt() {
        try {
            FileInputStream fileIn = new FileInputStream("produkt.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            List<Produkt> produkty = (List<Produkt>) in.readObject();
            in.close();
            fileIn.close();

            // Wyświetlenie dostępnych produktów
            System.out.println("Dostępne produkty:");
            for (int i = 0; i < produkty.size(); i++) {
                System.out.println((i + 1) + ". " + produkty.get(i));
            }

            System.out.print("Podaj numer produktu, który chcesz zakupić: ");
            Scanner scanner = new Scanner(System.in);
            int numerProduktu = scanner.nextInt();

            // Sprawdzenie poprawności numeru produktu
            if (numerProduktu >= 1 && numerProduktu <= produkty.size()) {
                Produkt wybranyProdukt = produkty.get(numerProduktu - 1);
                System.out.println("Wybrany produkt: " + wybranyProdukt);
                System.out.println("Dokonuję zakupu...");

                System.out.println("Zakup zakończony pomyślnie!");
            } else {
                System.out.println("Nieprawidłowy numer produktu.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Wystąpił błąd podczas odczytu pliku produkt.bin.");
            e.printStackTrace();
        }
    }

    public void setTyp(OsobaTyp typ) {
        this.typ = typ;
    }

    public OsobaTyp getTyp() {
        return typ;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPESEL() {
        return PESEL;
    }

    public List<String> getAdresEmail() {
        return adresEmail;
    }

    public void setAdresEmail(List<String> adresEmail) {
        this.adresEmail = adresEmail;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public Date getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Date dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getUlica() {
        return Ulica;
    }

    public void setUlica(String ulica) {
        Ulica = ulica;
    }

    public String getNumerMieszkania() {
        return numerMieszkania;
    }

    public void setNumerMieszkania(String numerMieszkania) {
        this.numerMieszkania = numerMieszkania;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getNumerTelefony() {
        return numerTelefony;
    }

    public void setNumerTelefony(String numerTelefony) {
        this.numerTelefony = numerTelefony;
    }

    public Date getDataZatrudnienia() {
        return dataZatrudnienia;
    }

    public void setDataZatrudnienia(Date dataZatrudnienia) {
        this.dataZatrudnienia = dataZatrudnienia;
    }

    public int getIndywidualnaStawkaGodzinowa() {
        return indywidualnaStawkaGodzinowa;
    }

    public Płeć getPłeć() {
        return płeć;
    }

    public void setPłeć(Płeć płeć) {
        this.płeć = płeć;
    }

    public void setIndywidualnaStawkaGodzinowa(int indywidualnaStawkaGodzinowa) {
        if (indywidualnaStawkaGodzinowa >= 70) {
            System.out.println("Proces obliczania wynagrodzenia zatrzymany. Stawka godzinowa jest większa lub równa 70.");
        } else {
            this.indywidualnaStawkaGodzinowa = indywidualnaStawkaGodzinowa;
        }
    }

    public int getLiczbaGodzinPrzepracowanawMiesiącu() {
        return liczbaGodzinPrzepracowanawMiesiącu;
    }

    public void setLiczbaGodzinPrzepracowanawMiesiącu(int liczbaGodzinPrzepracowanawMiesiącu) {
        this.liczbaGodzinPrzepracowanawMiesiącu = liczbaGodzinPrzepracowanawMiesiącu;
    }

    public int getMinimalnaStawkaZaGodzine() {
        return minimalnaStawkaZaGodzine;
    }

    public void setMinimalnaStawkaZaGodzine(int minimalnaStawkaZaGodzine) {
        this.minimalnaStawkaZaGodzine = minimalnaStawkaZaGodzine;
    }

    public Optional getDrugieImie() {
        return drugieImie;
    }

    public void setDrugieImie(Optional drugieImie) {
        this.drugieImie = drugieImie;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Imię: ").append(imie);

        if (drugieImie != null && drugieImie.isPresent()) {
            sb.append("\nDrugie imię: ").append(drugieImie.get());
        } else {
            sb.append("\nDrugie imię: Brak");
        }

        sb.append("\nNazwisko: ").append(nazwisko);
        sb.append("\nPESEL: ").append(PESEL);
        sb.append("\nAdresy email: ").append(adresEmail);

        sb.append("\nData urodzenia: ").append(dataUrodzenia);
        sb.append("\nUlica: ").append(Ulica);
        sb.append("\nNumer mieszkania: ").append(numerMieszkania);
        sb.append("\nMiasto: ").append(miasto);
        sb.append("\nKod pocztowy: ").append(kodPocztowy);
        sb.append("\nNumer telefonu: ").append(numerTelefony);

        sb.append("\nPłeć: ").append(płeć).append("\n");

        return sb.toString();
    }


}
