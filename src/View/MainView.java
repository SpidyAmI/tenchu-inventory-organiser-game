package View;

import Controller.GameEngineController;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;



public class MainView extends BorderPane {
    private GameEngineController gec;
    private HBox tasti;
    private Button startButton;
    private Button wButton;
    private Button aButton;
    private Button sButton;
    private Button dButton;

    public MainView(GameEngineController gec){
        this.gec = gec;
        this.startButton = new Button("Start");
        this.wButton = new Button("W");
        this.aButton = new Button("A");
        this.sButton = new Button("S");
        this.dButton = new Button("D");
        this.wButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->  this.gec.clickW());
        this.aButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->  this.gec.clickA());
        this.sButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->  this.gec.clickS());
        this.dButton.addEventHandler(MouseEvent.MOUSE_CLICKED,mouseEvent ->  this.gec.clickD());
        this.wButton.setDisable(true);
        this.aButton.setDisable(true);
        this.sButton.setDisable(true);
        this.dButton.setDisable(true);
        this.startButton.setOnMouseClicked(mouseEvent -> this.setSelection(false));
        this.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent ->
        {
            if (keyEvent.getCode() == KeyCode.W )
                this.gec.clickW();
            else if (keyEvent.getCode() == KeyCode.A)
                this.gec.clickA();
            else if (keyEvent.getCode() == KeyCode.S)
                this.gec.clickS();
            else if (keyEvent.getCode() == KeyCode.D)
                this.gec.clickD();
        });
        this.tasti = new HBox(startButton,wButton,aButton,sButton,dButton);
        this.tasti.setSpacing(10);
        this.setBottom(tasti);
    }

    private void setSelection(boolean b){
        this.wButton.setDisable(b);
        this.aButton.setDisable(b);
        this.sButton.setDisable(b);
        this.dButton.setDisable(b);
        this.startButton.setDisable(!b);
    }

    public void resetStart(){this.setSelection(true);}

    public void setZonaCentro(Node n){this.setCenter(n);}

    public void setZonaTop(Node n){this.setTop(n);}
}
