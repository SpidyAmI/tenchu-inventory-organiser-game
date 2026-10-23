package Controller;

import Model.Items.ItemInterface;
import View.ItemView;

public class ItemController {

    private ItemInterface ii;
    private ItemView iv;
    private WarehouseController wc;

    public ItemController(ItemInterface ii, WarehouseController wc){
        this.ii = ii;
        this.wc = wc;
        this.iv = new ItemView(ii.getColor(), ii.toString(), this);
    }

    public void refresh(){this.iv.refresh(ii.getColor(), ii.toString());}

    public void click(){
        this.wc.handle(this.ii);
    }

    public ItemView getIv(){return this.iv;}
}
