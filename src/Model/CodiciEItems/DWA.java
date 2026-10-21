package Model.CodiciEItems;

public class DWA extends Codice{

    public DWA() {super("DWA");}

    @Override
    public void esegui(Equipaggiamento e, Inventario i) {i.sbloccaTuttiIRari();}
}
