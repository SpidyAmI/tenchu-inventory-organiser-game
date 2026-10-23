package View;

import Controller.ItemController;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ItemView extends StackPane {
    private Rectangle r;
    private Label info;
    private final int Q_SIDE = 60;
    private final int EDGE_WIDTH = 3;
    private final Color EDGE_COLOR = Color.BLACK;
    private ItemController ic;

    public ItemView(Color colorRectangle, String info, ItemController ic){
        this.ic = ic;
        this.r = new Rectangle(Q_SIDE, Q_SIDE,colorRectangle);
        this.r.setStroke(EDGE_COLOR);
        this.r.setStrokeWidth(EDGE_WIDTH);
        this.info = new Label(info);
        this.getChildren().addAll(r,this.info);
        this.setOnMouseClicked(mouseEvent -> this.ic.click());
    }

    public void refresh(Color colorRectangle, String info){
        this.r.setFill(colorRectangle);
        this.info.setText(info);
    }

}
