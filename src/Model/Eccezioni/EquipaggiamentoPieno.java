package Model.Eccezioni;

public class EquipaggiamentoPieno extends NinjaException {
    public EquipaggiamentoPieno() {
        super("Nessuno slot libero nell'equipaggiamento");
    }
}
