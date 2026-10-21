package Model.CodiciEItems;

import Model.Eccezioni.EquipaggiamentoPieno;
import Model.Oggetti.OggettoInterface;

import java.util.ArrayList;
import java.util.List;

// Singleton
public class Equipaggiamento {
    private int capienza = 5;
    private List<OggettoInterface> listaOggetti = new ArrayList<>();
    private static final Equipaggiamento instance = new Equipaggiamento();

    private Equipaggiamento(){};

    public static Equipaggiamento getInstance(){return instance;}

    public void aggiungiOggetto(OggettoInterface o) throws EquipaggiamentoPieno {
        if (this.listaOggetti.size() >= this.capienza)
            throw new EquipaggiamentoPieno();
        else if (o != null)
            this.listaOggetti.add(o);
    }

    // Chiamabile solo dai codici poichè package private e nello stesso package
    void setMaxDim(int massimo){this.capienza = massimo;}

    public List<OggettoInterface> getListaOggettiEquipped(){ return listaOggetti; }

    public int getSlotLiberi(){return this.capienza - this.listaOggetti.size();}
}
