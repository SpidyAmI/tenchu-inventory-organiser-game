package Model.Exceptions;

public class OutOfStockItem extends NinjaException {
    public OutOfStockItem() {super("Item not equippable, out of stock!");}
}
