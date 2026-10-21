package Model.Eccezioni;

public class CodiceNonEsistente extends NinjaException {
    public CodiceNonEsistente() {
        super("Il codice che hai inserito non è valido");
    }
}
