package Model.CodiciEItems;

public class DDS extends Codice{

    public DDS() {super("DDS");}

    @Override
    public void esegui(Equipaggiamento e, Inventario i) {i.aumentaSbloccati();}
}
