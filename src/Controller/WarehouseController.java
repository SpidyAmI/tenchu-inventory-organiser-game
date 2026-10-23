package Controller;

import Controller.Alerts.Alerts;
import Model.CodesAndWarehouse.Warehouse;
import Model.Exceptions.NinjaException;
import Model.Items.ItemInterface;
import View.WarehouseView;
import View.MainView;
import View.ItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles a click on an item: checks if it can be equipped, then equips it.
 * Errors are shown in an alert and do not stop the game. The views are
 * refreshed in every case.
 */
public class WarehouseController {
    private Warehouse w;
    private List<ItemController> itemControllerList;
    private WarehouseView wv;
    private MainView mainView;
    private InventoryController ic;

    public WarehouseController(Warehouse w, InventoryController ic){
        this.w = w;
        this.ic = ic;
        this.itemControllerList = new ArrayList<>();
        List<ItemInterface> itemList = w.getItemInterfaceList();
        for(ItemInterface ii : itemList) {this.itemControllerList.add(new ItemController(ii,this));}
        this.createInventoryView();
    }

    public void setMv(MainView mainView) {
        this.mainView = mainView;
        this.mainView.setTopZone(this.wv);
    }

    private void createInventoryView(){
        List<ItemView> itemViewList = new ArrayList<>();
        for(ItemController ic : this.itemControllerList) itemViewList.add(ic.getIv());
        this.wv = new WarehouseView(itemViewList);
    }

    public void handle(ItemInterface ii ){
        try{
            ii.readyToEquip();
            w.equip(ii);
        } catch (NinjaException ne){
            Alerts.launchError(ne.getMessage());
        } finally {
            this.ic.refresh();
            this.refresh();
        }
    }

    public void refresh() {
        for (ItemController ic : itemControllerList) {ic.refresh();}
        this.ic.refresh();
    }
}
