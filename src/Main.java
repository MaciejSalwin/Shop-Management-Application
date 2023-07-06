import java.time.LocalDate;
import java.util.*;

public class Main {
    private static Map<String, Zamowienie> zamowienieQualif = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Koszyk koszyk = new Koszyk();
        koszyk.setIloscProduktow(3);
        String nazwaProduktu = "";
        int cenaProduktu = koszyk.obliczCeneProduktu(nazwaProduktu, koszyk.getIloscProduktow());
        System.out.println("Cena produktu " + nazwaProduktu + ": " + cenaProduktu);

        Pracownik_Zamowienie pracownikZamowienie = new Pracownik_Zamowienie("Numer1", LocalDate.now().minusDays(10), LocalDate.now().plusDays(7));

        Osoba osoba6 = new Osoba();
        osoba6.dodajDatePracy(pracownikZamowienie.getNumerZamowienia(), pracownikZamowienie.getDataRozpoczęcia(), pracownikZamowienie.getDataZakończenia());

        osoba6.zczytajInformacjePracy();
        Osoba osoba1 = null;

        while (true) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("Witaj w portalu administracyjnym. Co chciałbyś zrobić?");
            System.out.println("1. Wprowadź dane Osoby");
            System.out.println("2. Wyświetl dane osób");
            System.out.println("3. Ekstensja trwała");
            System.out.println("4. Esktensja trwała Produktu");
            System.out.println("5. GUI");
            System.out.println("6. Wyświetl wszystkich Klientów");
            System.out.println("7. Wyświetl wszystkich pracowników");
            System.out.println("8. Wybierz typ osoby");
            System.out.println("9. Oblicz wynagrodzenie");
            System.out.println("10. Oblicz wynagrodzenie");
            System.out.println("11. Stwórz zamówienie");
            System.out.println("12. Zakończ");
            System.out.println("---------------------------------------------------------------");

            Scanner scanner = new Scanner(System.in);
            int wybor = scanner.nextInt();
            scanner.nextLine();

            switch (wybor) {
                case 1:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz wprowadzić danych klientów, ponieważ jesteś klientem.");
                    } else {
                        Osoba osoba = new Osoba();
                        osoba.wpiszDane();
                        System.out.println("Dane osoby zostały wprowadzone.");
                        Osoba.writeExtent();
                    }
                    break;
                case 2:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz wyświetlić danych osób, ponieważ jesteś klientem.");
                    } else {
                        Osoba.printInfoOsoby();
                    }
                    break;
                case 3:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz wprowadzić danych klientów, ponieważ jesteś klientem.");
                    } else {
                        Osoba.readExtent();
                    }
                    break;
                case 4:
                    Produkt.readExtent();
                    break;
                case 5:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz modyfikować ilości produktów, ponieważ jesteś klientem.");
                    } else {
                        ProductGUI productGUI1 = new ProductGUI();
                        productGUI1.loadProductsFromBinaryFile();
                        productGUI1.setVisible(true);
                    }
                    break;
                case 6:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz wyświetlić klientów, ponieważ jesteś klientem.");
                    } else {
                        Osoba.wyswietlKlientow();
                    }
                    break;
                case 7:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz wyświetlić pracowników, ponieważ jesteś klientem.");
                    } else {
                        Osoba.wyswietlPracownikow();
                    }
                    break;
                case 8:
                    osoba1 = new Osoba();
                    osoba1.wybierzTypOsoby();
                    break;
                case 9:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz obliczyć wynagrodzenia, ponieważ jesteś klientem.");
                    } else {
                        if (osoba1 != null) {
                            System.out.print("Podaj stawkę godzinową: ");
                            int stawkaGodzinowa = scanner.nextInt();

                            if (stawkaGodzinowa >= 70) {
                                System.out.println("Proces obliczania wynagrodzenia zatrzymany. Stawka godzinowa jest większa lub równa 70.");
                                break;
                            }

                            System.out.print("Podaj liczbę godzin przepracowanych w miesiącu: ");
                            int liczbaGodzin = scanner.nextInt();

                            osoba1.setIndywidualnaStawkaGodzinowa(stawkaGodzinowa);
                            osoba1.setLiczbaGodzinPrzepracowanawMiesiącu(liczbaGodzin);

                            System.out.println("Wynagrodzenie: " + osoba1.obliczWynagrodzenie(stawkaGodzinowa));
                        } else {
                            System.out.println("Najpierw wybierz typ osoby.");
                        }
                    }
                    break;
                case 10:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz stworzyć zamówienia, ponieważ jesteś klientem.");
                    } else {
                        Zamowienie zamowienie = new Zamowienie();
                        zamowienie.dodajZamowienieQualif(scanner);
                        System.out.println("Zamówienie dodane.");
                    }
                    break;

                case 11:
                    if (osoba1 != null && osoba1.getTyp() == OsobaTyp.KLIENT) {
                        System.out.println("Nie możesz znaleźć zamówienia, ponieważ jesteś klientem.");
                    } else {
                        System.out.print("Podaj numer zamówienia: ");
                        scanner.nextLine();

                        String numerZamowienia = scanner.nextLine();
                        Zamowienie znalezioneZamowienie = zamowienieQualif.get(numerZamowienia);

                        if (znalezioneZamowienie != null) {
                            System.out.println("Znalezione zamówienie: " + znalezioneZamowienie);
                        } else {
                            System.out.println("Zamówienie o podanym numerze nie zostało znalezione.");
                        }
                    }
                    break;
                case 12:
                    System.out.println("Program zakończył działanie.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Wybierz liczbę od 1 do 14.");
            }
        }

    }
}
