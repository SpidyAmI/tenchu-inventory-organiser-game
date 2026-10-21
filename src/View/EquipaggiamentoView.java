package View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;


public class EquipaggiamentoView extends VBox {
    private Label oggettiRimanenti;
    private HBox oggettiEquip;
    private List<Label> infoOggettiEquip;

    public EquipaggiamentoView(String infoOggettiRimasti, List<String> infoOggettiEquip)
    {
        this.oggettiRimanenti = new Label("");
        this.infoOggettiEquip = new ArrayList<>();
        this.oggettiEquip = new HBox();
        this.refresh(infoOggettiRimasti,infoOggettiEquip);
        this.getChildren().addAll(this.oggettiRimanenti, this.oggettiEquip);
    }

    public void refresh(String infoOggettiRimasti, List<String> infoOggettiEquip){
        this.oggettiRimanenti.setText("Oggetti rimanenti: " + infoOggettiRimasti);
        // Pulisco
        this.infoOggettiEquip = new ArrayList<>();
        for(String s : infoOggettiEquip){this.infoOggettiEquip.add(new Label(s));}
        //Pulisco
        this.oggettiEquip.getChildren().clear();
        for(Label l : this.infoOggettiEquip){this.oggettiEquip.getChildren().add(l);}
    }
}
