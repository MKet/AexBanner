package shared;

import java.io.Serializable;

public class Fonds implements IFonds, Serializable {

    private String koersNaam;
    private double koers;

    public Fonds(String koersNaam) {
        this.koersNaam = koersNaam;
    }

    @Override
    public String getNaam() {
        return koersNaam;
    }

    public void setKoers(double koers) {
        this.koers = koers;
    }

    @Override
    public double getKoers() {
        return koers;
    }

    @Override
    public String toString() {
        return String.format("%s : %.2f ", koersNaam, koers);
    }
}
