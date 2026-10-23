package Model.Items;

import Model.Exceptions.OutOfStockItem;
import Model.Exceptions.NotUnlockedItem;
import javafx.scene.paint.Color;

import java.util.Objects;
/**
 * Base class for every item the ninja can collect.
 */
public abstract class Item implements ItemInterface, Comparable<Item>{
    protected final String name;
    protected int amount;
    protected final int MAX_AMOUNT;
    protected boolean isUnlocked;

    Item(boolean isUnlocked, int MAX_AMOUNT, int amount, String name) {
        this.isUnlocked = isUnlocked;
        this.MAX_AMOUNT = MAX_AMOUNT;
        this.amount = amount;
        this.name = name;
    }

    @Override
    public void unlock() {this.isUnlocked = true;}

    @Override
    public void increment() {
        if (this.amount < MAX_AMOUNT) this.amount++;
        if(! this.isUnlocked) this.isUnlocked = true;
    }

    @Override
    public Color getColor() {
        if(this.isUnlocked)
            return Color.LIGHTGRAY;
        return Color.DARKGRAY;
    }

    @Override
    public int compareTo(Item o) {return this.name.compareTo(o.name);}

    @Override
    public String getName() {return this.name;}

    @Override
    public String toString() {return this.name + "\n    " + this.amount;}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return amount == item.amount && MAX_AMOUNT == item.MAX_AMOUNT && isUnlocked == item.isUnlocked && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, MAX_AMOUNT, isUnlocked);
    }

    /**
     * Checks that the item can be equipped.
     * @throws NotUnlockedItem if the item is still locked (checked first)
     * @throws OutOfStockItem  if the item is unlocked but its amount is zero
     */
    @Override
    public void readyToEquip() throws OutOfStockItem, NotUnlockedItem {
        if(! this.isUnlocked)
            throw new NotUnlockedItem();
        if(this.amount == 0)
            throw new OutOfStockItem();
    }

    @Override
    public void decrease() {this.amount--;}

    @Override
    public boolean isUnlocked() {return isUnlocked;}
}
