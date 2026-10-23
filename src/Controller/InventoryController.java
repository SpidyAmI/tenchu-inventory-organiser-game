package Controller;

import Model.CodesAndWarehouse.Inventory;
import Model.Items.ItemInterface;
import View.InventoryView;
import View.MainView;

import java.util.ArrayList;
import java.util.List;

public class InventoryController {
    private Inventory i;
    private InventoryView iv;
    private MainView mainView;

    public InventoryController(){
        this.i = Inventory.getInstance();
        List<String> equipInfo = new ArrayList<>();
        for(ItemInterface ii : i.getEquippedItemsList()){equipInfo.add(ii.getName());}
        this.iv = new InventoryView(i.getAvailableSlots() + "", equipInfo);
    }

    public void setMv(MainView mainView){
        this.mainView = mainView;
        this.mainView.setCenterZone(this.iv);
    }

    public void refresh(){
        List<String> equipInfo = new ArrayList<>();
        for(ItemInterface ii : i.getEquippedItemsList()){equipInfo.add(ii.getName());}
        this.iv.refresh(i.getAvailableSlots() + "", equipInfo);
    }
}
