package Model.Items;

import Model.Exceptions.OutOfStockItem;
import Model.Exceptions.NotUnlockedItem;
import javafx.scene.paint.Color;

public interface ItemInterface {
    void unlock();
    void increment();
    Color getColor();
    String getName();
    void readyToEquip() throws OutOfStockItem, NotUnlockedItem;
    void decrease();
    boolean isUnlocked();
}
