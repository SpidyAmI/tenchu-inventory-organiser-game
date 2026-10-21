package Controller;

import Controller.Alerts.Alerts;
import Model.CodiciEItems.Inventario;
import Model.Eccezioni.NinjaException;
import Model.Oggetti.OggettoInterface;
import View.InventarioView;
import View.MainView;
import View.OggettoView;

import java.util.ArrayList;
import java.util.List;

public class InventarioController {
    private Inventario i;
    private List<OggettoController> listaOggettiController;
    private InventarioView iv;
    private MainView mainView;
    private EquipaggiamentoController ec;

    public InventarioController(Inventario i, EquipaggiamentoController ec){
        this.i = i;
        this.ec = ec;
        this.listaOggettiController = new ArrayList<>();
        List<OggettoInterface> listaOggetti = i.getOggettoInterfaceList();
        for(OggettoInterface of : listaOggetti) {this.listaOggettiController.add(new OggettoController(of,this));}
        this.createInventarioView();
    }

    public void setMv(MainView mainView) {
        this.mainView = mainView;
        this.mainView.setZonaTop(this.iv);
    }

    private void createInventarioView(){
        List<OggettoView> listaOggettiView = new ArrayList<>();
        for(OggettoController oc : this.listaOggettiController) listaOggettiView.add(oc.getOv());
        this.iv = new InventarioView(listaOggettiView);
    }

    public void handle(OggettoInterface oi){
        try{
            oi.readyToEquip();
            i.equipaggia(oi);
        } catch (NinjaException ne){
            Alerts.launchError(ne.getMessage());
        } finally {
            this.ec.refresh();
            this.refresh();
        }
    }

    public void refresh() {
        for (OggettoController oc : listaOggettiController) {oc.refresh();}
        this.ec.refresh();
    }
}
