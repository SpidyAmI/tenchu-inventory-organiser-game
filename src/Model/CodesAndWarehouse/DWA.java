package Model.CodesAndWarehouse;

/** Unlocks every rare item. */
public class DWA extends Code {

    public DWA() {super("DWA");}

    @Override
    public void execute(Inventory i, Warehouse w) {w.unlockAllRares();}
}
