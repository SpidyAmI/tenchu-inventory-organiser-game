package View;

import Controller.OggettoController;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class OggettoView extends StackPane {
    private Rectangle r;
    private Label info;
    private final int LATO_QUADRATO = 60;
    private final int LARGHEZZA_BORDO = 3;
    private final Color COLORE_BORDO = Color.BLACK;
    private OggettoController oc;

    public OggettoView(Color coloreRettanglo, String info, OggettoController oc){
        this.oc = oc;
        this.r = new Rectangle(LATO_QUADRATO,LATO_QUADRATO,coloreRettanglo);
        this.r.setStroke(COLORE_BORDO);
        this.r.setStrokeWidth(LARGHEZZA_BORDO);
        this.info = new Label(info);
        this.getChildren().addAll(r,this.info);
        this.setOnMouseClicked(mouseEvent -> this.oc.click());
    }

    public void refresh(Color coloreRettangolo, String info){
        this.r.setFill(coloreRettangolo);
        this.info.setText(info);
    }

}
