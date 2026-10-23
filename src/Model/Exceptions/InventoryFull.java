package Model.Exceptions;

public class InventoryFull extends NinjaException {
    public InventoryFull() {
        super("Inventory full!");
    }
}
