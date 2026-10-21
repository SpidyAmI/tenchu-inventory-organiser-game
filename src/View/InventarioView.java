package View;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class InventarioView extends VBox {
    private List<OggettoView> listaOggettiView;
    private final int DIMENSIONE_RIGA = 3;

    public InventarioView(List<OggettoView> listaOggettiView)
    {
        this.listaOggettiView = listaOggettiView;
        boolean firstTime = true;
        HBox toFill = new HBox();
        for(int i = 0; i < this.listaOggettiView.size(); i++){
            if(i % DIMENSIONE_RIGA == 0 ){
                if(!firstTime){
                    toFill = new HBox();
                }
                else {
                    firstTime = false;
                }
                this.getChildren().add(toFill);
            }

            toFill.getChildren().add(this.listaOggettiView.get(i));
        }

        this.setSpacing(10);
    }
}
