package Model.CodiciEItems;

public class DDA extends Codice{

    public DDA() {super("DDA");}

    @Override
    public void esegui(Equipaggiamento e, Inventario i) {e.setMaxDim(99);}
}
