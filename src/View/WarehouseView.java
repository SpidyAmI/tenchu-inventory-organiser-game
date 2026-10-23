package View;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class WarehouseView extends VBox {
    private List<ItemView> itemViewList;
    private final int ROW_DIMENSION = 3;

    public WarehouseView(List<ItemView> itemViewList)
    {
        this.itemViewList = itemViewList;
        boolean firstTime = true;
        HBox toFill = new HBox();
        for(int i = 0; i < this.itemViewList.size(); i++){
            if(i % ROW_DIMENSION == 0 ){
                if(!firstTime){
                    toFill = new HBox();
                }
                else {
                    firstTime = false;
                }
                this.getChildren().add(toFill);
            }

            toFill.getChildren().add(this.itemViewList.get(i));
        }

        this.setSpacing(10);
    }
}
