import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Opinia {
    public static List<Osoba> wszystkieOpinie = new ArrayList<>();

    private String krótkiTekst;
    private String ocena;
    private Date dataWystawienia;

    public String getKrótkiTekst() {
        return krótkiTekst;
    }

    public void setKrótkiTekst(String krótkiTekst) {
        this.krótkiTekst = krótkiTekst;
    }

    public String getOcena() {
        return ocena;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }

    public Date getDataWystawienia() {
        return dataWystawienia;
    }

    public void setDataWystawienia(Date dataWystawienia) {
        this.dataWystawienia = dataWystawienia;
    }

    public static List<Osoba> getWszystkieOpinie() {
        return wszystkieOpinie;
    }

    public static void setWszystkieOpinie(List<Osoba> wszystkieOpinie) {
        Opinia.wszystkieOpinie = wszystkieOpinie;
    }
}
