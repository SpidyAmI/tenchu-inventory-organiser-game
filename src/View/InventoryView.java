package View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class InventoryView extends VBox {
    private Label itemLeft;
    private HBox equippedItems;
    private List<Label> infoEquippedItems;

    public InventoryView(String itemLeft, List<String> infoEquippedItems)
    {
        this.itemLeft = new Label("");
        this.infoEquippedItems = new ArrayList<>();
        this.equippedItems = new HBox();
        this.refresh(itemLeft, infoEquippedItems);
        this.getChildren().addAll(this.itemLeft, this.equippedItems);
    }

    public void refresh(String infoItemLeft, List<String> infoEquippedItems){
        this.itemLeft.setText("Items left: " + infoItemLeft);
        // Cleaning
        this.infoEquippedItems = new ArrayList<>();
        for(String s : infoEquippedItems){this.infoEquippedItems.add(new Label(s));}
        //Cleaning
        this.equippedItems.getChildren().clear();
        for(Label l : this.infoEquippedItems){this.equippedItems.getChildren().add(l);}
    }
}
