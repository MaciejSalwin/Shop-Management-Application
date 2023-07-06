import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * -----ASOCJACJA ZWYKŁA(ID, produktID, findprodukt)
 */
public class Produkt {
    private String nazwa;
    private String cena;
    private String szczegółowyOpis;
    private int ID;
    public int[] produktID;
    private TypProduktu typProduktu;
    public static List<Produkt> wszystkieProdukty = new ArrayList<>();

    public Produkt(String nazwa, String cena, String szczegolowyOpis, int ID, int[] produktID, TypProduktu typProduktu) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.szczegółowyOpis = szczegolowyOpis;
        this.ID = ID;
        this.produktID = produktID;
        this.typProduktu = typProduktu;
    }

    public Produkt() {
    }


    public static Produkt findProdukt(int produktID) throws Exception {
        for (Produkt produkt : Produkt.getWszystkieProdukty()) {
            if (produkt.getID() == produktID) {
                return produkt;
            }
        }
        throw new Exception("Nie można znaleźć produktu z tym ID: " + produktID);
    }

    public static void writeExtent() {
        try {
            FileOutputStream fileOut = new FileOutputStream("Produkt.bin");
            DataOutputStream out = new DataOutputStream(fileOut);

            out.writeInt(wszystkieProdukty.size());

            for (Produkt produkt : wszystkieProdukty) {
                produkt.write(out);
            }

            out.close();
            fileOut.close();

            System.out.println("Dane produktów zostały zapisane.");
        } catch (IOException e) {
            System.out.println("Wystąpił błąd podczas zapisywania danych produktów.");
            e.printStackTrace();
        }
    }

    public static void readExtent() {
        try {
            FileInputStream fileIn = new FileInputStream("Produkt.bin");
            DataInputStream in = new DataInputStream(fileIn);

            int liczbaProduktow = in.readInt();

            wszystkieProdukty.clear();

            for (int i = 0; i < liczbaProduktow; i++) {
                Produkt produkt = new Produkt();
                try {
                    produkt.read(in);
                } catch (EOFException e) {
                    break; // Zakończ pętlę, gdy nie ma więcej danych do odczytu
                }
                wszystkieProdukty.add(produkt);
                System.out.println(produkt);
            }

            in.close();
            fileIn.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void write(DataOutputStream stream) throws IOException {
        stream.writeUTF(getNazwa());
        stream.writeUTF(getCena());
        stream.writeUTF(getSzczegółowyOpis());
        stream.writeInt(getID());

        if (produktID != null) {
            stream.writeInt(produktID.length);
            for (int id : produktID) {
                stream.writeInt(id);
            }
        } else {
            stream.writeInt(0);
        }

        if (typProduktu != null) {
            stream.writeUTF(getTypProduktu().name());
        } else {
            stream.writeUTF("");
        }
    }

    public void read(DataInputStream stream) throws IOException {
        setNazwa(stream.readUTF());
        setCena(stream.readUTF());
        setSzczegółowyOpis(stream.readUTF());
        setID(stream.readInt());
        int produktIdCount = stream.readInt();
        if (produktIdCount > 0) {
            produktID = new int[produktIdCount];
            for (int i = 0; i < produktIdCount; i++) {
                produktID[i] = stream.readInt();
            }
        } else {
            produktID = null;
        }
        String typProduktuStr = stream.readUTF();
        if (!typProduktuStr.isEmpty()) {
            setTypProduktu(TypProduktu.valueOf(typProduktuStr));
        } else {
            setTypProduktu(null);
        }
    }

    public static void wpiszDaneProduktu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dodawanie nowego produktu:");

        System.out.print("Podaj nazwę produktu: ");
        String nazwa = scanner.nextLine();

        System.out.print("Podaj cenę produktu: ");
        String cena = scanner.nextLine();

        System.out.print("Podaj szczegółowy opis produktu: ");
        String opis = scanner.nextLine();

        int noweID = generujNoweID();

        System.out.print("Podaj kategorię produktu: ");
        String kategoria = scanner.nextLine();

        System.out.print("Podaj producenta produktu: ");
        String producent = scanner.nextLine();

        TypProduktu typProduktu = TypProduktu.fromString(kategoria, producent);

        Produkt produkt = new Produkt(nazwa, cena, opis, noweID, null, typProduktu);

        Produkt.getWszystkieProdukty().add(produkt);

        System.out.println("Produkt został dodany do listy.");
    }

    private static int generujNoweID() {
        int maxID = 0;
        for (Produkt produkt : Produkt.getWszystkieProdukty()) {
            if (produkt.getID() > maxID) {
                maxID = produkt.getID();
            }
        }

        return maxID + 1;
    }


    public TypProduktu getTypProduktu() {
        return typProduktu;
    }

    public void setTypProduktu(TypProduktu typProduktu) {
        this.typProduktu = typProduktu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getSzczegółowyOpis() {
        return szczegółowyOpis;
    }

    public void setSzczegółowyOpis(String szczegółowyOpis) {
        this.szczegółowyOpis = szczegółowyOpis;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int[] getProduktID() {
        return produktID;
    }

    public void setProduktID(int[] produktID) {
        this.produktID = produktID;
    }

    public static List<Produkt> getWszystkieProdukty() {
        return wszystkieProdukty;
    }

    public static void setWszystkieProdukty(List<Produkt> wszystkieProdukty) {
        Produkt.wszystkieProdukty = wszystkieProdukty;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "nazwa='" + nazwa + '\'' +
                ", cena='" + cena + '\'' +
                ", szczegółowyOpis='" + szczegółowyOpis + '\'' +
                ", ID=" + ID +
                ", produktID=" + Arrays.toString(produktID) +
                ", typProduktu=" + typProduktu +
                '}';
    }
}
