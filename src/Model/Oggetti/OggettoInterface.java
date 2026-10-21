package Model.Oggetti;

import Model.Eccezioni.OggettoFinito;
import Model.Eccezioni.OggettoNonSbloccato;
import javafx.scene.paint.Color;

public interface OggettoInterface {
    void sblocca();
    void incrementa();
    Color getColore();
    String getNome();
    void readyToEquip() throws OggettoFinito, OggettoNonSbloccato;
    void decrementa();
    boolean isUnlocked();
}
