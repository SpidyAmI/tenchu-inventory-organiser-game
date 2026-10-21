package Controller;

import Controller.Alerts.Alerts;
import Model.Eccezioni.NinjaException;
import Model.GameEngine;
import View.MainView;

public class GameEngineController {
    private GameEngine ge;
    private String codiceComposto;
    private MainView mainView;
    private InventarioController ic;

    public GameEngineController(GameEngine ge, InventarioController ic) {
        this.ge = ge;
        this.ic = ic;
        this.codiceComposto = "";
    }

    public void setMv(MainView mainView) {this.mainView = mainView;}

    public void clickW() {this.aggiungiLetteraAlCodice("W");}

    public void clickA() {this.aggiungiLetteraAlCodice("A");}

    public void clickS() {this.aggiungiLetteraAlCodice("S");}

    public void clickD() {this.aggiungiLetteraAlCodice("D");}

    private void aggiungiLetteraAlCodice(String l) {
        this.codiceComposto += l;
        if (this.codiceComposto.length() == 3) {
            try {
                this.ge.tentaCodice(this.codiceComposto);
                this.mainView.resetStart();
            } catch (NinjaException ne){
                Alerts.launchError(ne.getMessage());
            } finally {
                this.codiceComposto = "";
                this.ic.refresh();
            }
        }
    }

}