package Model.Eccezioni;

public class OggettoNonSbloccato extends NinjaException {
    public OggettoNonSbloccato() {
        super("Non hai ancora sbloccato l'oggetto");
    }
}
