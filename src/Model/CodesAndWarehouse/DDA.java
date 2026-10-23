package Model.CodesAndWarehouse;

/** Raises the inventory capacity to 99 slots. */
public class DDA extends Code {

    public DDA() {super("DDA");}

    @Override
    public void execute(Inventory i, Warehouse w) {i.setMaxDim(99);}
}
