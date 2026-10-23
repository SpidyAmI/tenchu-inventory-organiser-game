package Model.CodesAndWarehouse;

/** Adds one unit to every item that is already unlocked. Locked items are unchanged. */
public class DDS extends Code {

    public DDS() {super("DDS");}

    @Override
    public void execute(Inventory i, Warehouse w) {w.increaseUnlocked();}
}
