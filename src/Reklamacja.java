import java.util.Date;

public class Reklamacja {
    private int numerReklamacji;
    private Date dataZlozenia;
    private Date dataPrzyjecia;
    private String opisProblemu;
    private String statusPrzyjecia;

    public int getNumerReklamacji() {
        return numerReklamacji;
    }

    public void setNumerReklamacji(int numerReklamacji) {
        this.numerReklamacji = numerReklamacji;
    }

    public Date getDataZlozenia() {
        return dataZlozenia;
    }

    public void setDataZlozenia(Date dataZlozenia) {
        this.dataZlozenia = dataZlozenia;
    }

    public Date getDataPrzyjecia() {
        return dataPrzyjecia;
    }

    public void setDataPrzyjecia(Date dataPrzyjecia) {
        this.dataPrzyjecia = dataPrzyjecia;
    }

    public String getOpisProblemu() {
        return opisProblemu;
    }

    public void setOpisProblemu(String opisProblemu) {
        this.opisProblemu = opisProblemu;
    }

    public String getStatusPrzyjecia() {
        return statusPrzyjecia;
    }

    public void setStatusPrzyjecia(String statusPrzyjecia) {
        this.statusPrzyjecia = statusPrzyjecia;
    }
}
