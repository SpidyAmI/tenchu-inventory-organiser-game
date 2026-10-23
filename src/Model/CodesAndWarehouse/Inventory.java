package Model.CodesAndWarehouse;

import Model.Exceptions.InventoryFull;
import Model.Items.ItemInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The ninja's inventory, shared by the whole application (singleton).
 */
public class Inventory {
    private int slots = 5;
    private List<ItemInterface> itemList = new ArrayList<>();
    private static final Inventory instance = new Inventory();

    private Inventory(){};

    public static Inventory getInstance(){return instance;}

    /**
     * Adds an item to the inventory. A null item is ignored.
     * @throws InventoryFull if there are no free slots
     */
    public void addItem(ItemInterface ii) throws InventoryFull {
        if (this.itemList.size() >= this.slots)
            throw new InventoryFull();
        else if (ii != null)
            this.itemList.add(ii);
    }

    // Following function callable only by codes since it is package-private and in the same package

    void setMaxDim(int max){this.slots = max;}

    public List<ItemInterface> getEquippedItemsList(){ return itemList; }

    public int getAvailableSlots(){return this.slots - this.itemList.size();}
}
