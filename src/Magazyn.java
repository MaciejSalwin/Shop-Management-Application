import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Magazyn {
    private Map<String, Integer> ilośćNaStanieProduktów;
    private int ogólnaIlośćProduktówWMagazynie;

    public Magazyn() {
        ilośćNaStanieProduktów = new HashMap<>();
        ogólnaIlośćProduktówWMagazynie = 0;
    }

    public void dodajProdukt(String nazwaProduktu, int ilość) {
        ilośćNaStanieProduktów.put(nazwaProduktu, ilość);
        ogólnaIlośćProduktówWMagazynie += ilość;
    }

    public int getIlośćNaStanieProduktu(String nazwaProduktu) {
        return ilośćNaStanieProduktów.getOrDefault(nazwaProduktu, 0);
    }

    public int getOgólnaIlośćProduktówWMagazynie() {
        return ogólnaIlośćProduktówWMagazynie;
    }

    public static void main(String[] args) {
        Magazyn magazyn = new Magazyn();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dodawanie produktów do magazynu:");

        while (true) {
            System.out.print("Podaj nazwę produktu (lub wpisz 'koniec' aby zakończyć): ");
            String nazwaProduktu = scanner.nextLine();

            if (nazwaProduktu.equalsIgnoreCase("koniec")) {
                break;
            }

            System.out.print("Podaj ilość produktu '" + nazwaProduktu + "': ");
            int ilość = scanner.nextInt();
            scanner.nextLine(); // Pobierz pozostały znak nowej linii

            magazyn.dodajProdukt(nazwaProduktu, ilość);
        }

        System.out.println("Ilość wszystkich produktów w magazynie: " + magazyn.getOgólnaIlośćProduktówWMagazynie());

        System.out.print("Podaj nazwę produktu, aby sprawdzić ilość na stanie: ");
        String nazwaProduktu = scanner.nextLine();
        int ilośćNaStanie = magazyn.getIlośćNaStanieProduktu(nazwaProduktu);
        System.out.println("Ilość produktu '" + nazwaProduktu + "' na stanie: " + ilośćNaStanie);
    }
}

