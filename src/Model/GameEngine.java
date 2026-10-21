package Model;

import Model.CodiciEItems.*;
import Model.Eccezioni.CodiceNonEsistente;
import Model.Eccezioni.NinjaException;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private Equipaggiamento e;
    private Inventario i;
    private List<CodiceInterface> codici;

    public GameEngine(Inventario i){
        this.i = i;
        codici = new ArrayList<>();
        codici.add(new DDA());
        codici.add(new DDS());
        codici.add(new DWA());
        this.e = Equipaggiamento.getInstance();
    }

    public void tentaCodice(String code) throws NinjaException{

        boolean find = false;

        for(int i = 0; i < codici.size(); i++){
            if (code.equals(codici.get(i).getCodice())){
                codici.get(i).esegui(this.e,this.i);
                find = true;
            }
        }

        if (!find) throw new CodiceNonEsistente();
    }
}
