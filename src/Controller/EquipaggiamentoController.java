package Controller;

import Model.CodiciEItems.Equipaggiamento;
import Model.Oggetti.OggettoInterface;
import View.EquipaggiamentoView;
import View.MainView;

import java.util.ArrayList;
import java.util.List;

public class EquipaggiamentoController {
    private Equipaggiamento e;
    private EquipaggiamentoView ev;
    private MainView mainView;

    public EquipaggiamentoController(){
        this.e = Equipaggiamento.getInstance();
        List<String> equipInfo = new ArrayList<>();
        for(OggettoInterface oi : e.getListaOggettiEquipped()){equipInfo.add(oi.getNome());}
        this.ev = new EquipaggiamentoView(e.getSlotLiberi() + "", equipInfo);
    }

    public void setMv(MainView mainView){
        this.mainView = mainView;
        this.mainView.setZonaCentro(this.ev);
    }

    public void refresh(){
        List<String> equipInfo = new ArrayList<>();
        for(OggettoInterface oi : e.getListaOggettiEquipped()){equipInfo.add(oi.getNome());}
        this.ev.refresh(e.getSlotLiberi() + "", equipInfo);
    }
}
