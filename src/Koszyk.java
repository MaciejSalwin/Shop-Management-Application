import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;

public class Koszyk {
    private int iloscProduktow;
    private int koszt;
    private String kodRabatowy;

    public int obliczCeneProduktu(String nazwaProduktu, int iloscProduktow) {
        int cenaProduktu = 0;
        try {
            FileInputStream fileIn = new FileInputStream("Produkt.bin");
            DataInputStream in = new DataInputStream(fileIn);

            int liczbaProduktow = in.readInt();

            for (int i = 0; i < liczbaProduktow; i++) {
                Produkt produkt = new Produkt();
                try {
                    produkt.read(in);
                } catch (EOFException e) {
                    break; // Zakończ pętlę, gdy nie ma więcej danych do odczytu
                }

                if (produkt.getNazwa().equals(nazwaProduktu)) {
                    int cena = Integer.parseInt(produkt.getCena());
                    cenaProduktu += cena * iloscProduktow; // Dodaj aktualną cenę do sumy
                    break;
                }
            }

            in.close();
            fileIn.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cenaProduktu;
    }

    public int getIloscProduktow() {
        return iloscProduktow;
    }

    public void setIloscProduktow(int iloscProduktow) {
        this.iloscProduktow = iloscProduktow;
    }

    public int getKoszt() {
        return koszt;
    }

    public void setKoszt(int koszt) {
        this.koszt = koszt;
    }

    public String getKodRabatowy() {
        return kodRabatowy;
    }

    public void setKodRabatowy(String kodRabatowy) {
        this.kodRabatowy = kodRabatowy;
    }
}
