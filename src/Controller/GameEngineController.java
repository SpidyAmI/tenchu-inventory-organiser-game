package Controller;

import Controller.Alerts.Alerts;
import Model.Exceptions.NinjaException;
import Model.GameEngine;
import View.MainView;

/**
 * Collects the letters pressed by the player (W, A, S, D). When three letters
 * have been typed, it tries the resulting code and clears the buffer, whether
 * the code is valid or not.
 */
public class GameEngineController {
    private GameEngine ge;
    private String codebuffer;
    private MainView mainView;
    private WarehouseController wc;

    public GameEngineController(GameEngine ge, WarehouseController wc) {
        this.ge = ge;
        this.wc = wc;
        this.codebuffer = "";
    }

    public void setMv(MainView mainView) {this.mainView = mainView;}

    public void clickW() {this.addCharToCodeBuffer("W");}

    public void clickA() {this.addCharToCodeBuffer("A");}

    public void clickS() {this.addCharToCodeBuffer("S");}

    public void clickD() {this.addCharToCodeBuffer("D");}

    private void addCharToCodeBuffer(String l) {
        this.codebuffer += l;
        if (this.codebuffer.length() == 3) {
            try {
                this.ge.tryCode(this.codebuffer);
                this.mainView.resetStart();
            } catch (NinjaException ne){
                Alerts.launchError(ne.getMessage());
            } finally {
                this.codebuffer = "";
                this.wc.refresh();
            }
        }
    }

}