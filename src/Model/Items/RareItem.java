package Model.Items;

public class RareItem extends Item {

    protected boolean isUnique;

    RareItem(String name, boolean isUnique) {
        super(false, isUnique ? 1 : 3, 0, name);
        this.isUnique = isUnique;
    }
}
