package Model.Eccezioni;

public class OggettoFinito extends NinjaException {
    public OggettoFinito() {super("Non ci sono abbastanza oggetti di quel tipo nell'inventario per metterne uno nell' equipaggiamento");}
}
