package Model.Exceptions;

public class NotUnlockedItem extends NinjaException {
    public NotUnlockedItem() {
        super("Item not unlocked");
    }
}
