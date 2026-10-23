package Model.CodesAndWarehouse;

import Model.Exceptions.InventoryFull;
import Model.Items.CommonItem;
import Model.Items.ItemInterface;
import Model.Items.RareItem;

import java.util.ArrayList;
import java.util.List;

/**
 * The clan's stock: every common and rare item, whether unlocked or not.
 */
public class Warehouse {
    private List<CommonItem> commonItemsList;
    private List<RareItem> rareItemsList;

    public Warehouse(List<CommonItem> commonItemsList, List<RareItem> rareItemsList) {
        this.commonItemsList = commonItemsList;
        this.rareItemsList = rareItemsList;
    }

    // Following two functions callable only by codes since they are package-private and in the same package

    void unlockAllRares(){
        for(RareItem ri : rareItemsList){
            ri.unlock();
        }
    }

    void increaseUnlocked(){
        List<ItemInterface> listIF = getItemInterfaceList();
        for(ItemInterface ii : listIF){
            if(ii.isUnlocked())
                ii.increment();
        }
    }

    public List<ItemInterface> getItemInterfaceList(){
        List<ItemInterface> listIF = new ArrayList<>();
        listIF.addAll(commonItemsList);
        listIF.addAll(rareItemsList);
        return listIF;
    }

    public void equip(ItemInterface ii) throws InventoryFull {
        Inventory.getInstance().addItem(ii);
        ii.decrease();
    }
}
