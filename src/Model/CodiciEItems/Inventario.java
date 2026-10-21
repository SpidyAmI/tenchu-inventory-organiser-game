package Model.CodiciEItems;

import Model.Eccezioni.EquipaggiamentoPieno;
import Model.Oggetti.OggettoComune;
import Model.Oggetti.OggettoInterface;
import Model.Oggetti.OggettoRaro;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<OggettoComune> listaOggettiComuni;
    private List<OggettoRaro> listaOggettiRari;

    public Inventario(List<OggettoComune> listaOggettiComuni, List<OggettoRaro> listaOggettiRari) {
        this.listaOggettiComuni = listaOggettiComuni;
        this.listaOggettiRari = listaOggettiRari;
    }

    // Le due funzioni a seguire chiamabili solo dai codici poichè nello stesso package e package private

    void sbloccaTuttiIRari(){
        for(OggettoRaro or : listaOggettiRari){
            or.sblocca();
        }
    }

    void aumentaSbloccati(){
        List<OggettoInterface> listaOF = getOggettoInterfaceList();
        for(OggettoInterface oi : listaOF){
            if(oi.isUnlocked())
                oi.incrementa();
        }
    }

    public List<OggettoInterface> getOggettoInterfaceList(){
        List<OggettoInterface> listaOF = new ArrayList<>();
        listaOF.addAll(listaOggettiComuni);
        listaOF.addAll(listaOggettiRari);
        return listaOF;
    }

    public void equipaggia(OggettoInterface oi) throws EquipaggiamentoPieno {
        Equipaggiamento.getInstance().aggiungiOggetto(oi);
        oi.decrementa();
    }
}
