package Model.CodiciEItems;

import Model.Eccezioni.EquipaggiamentoPieno;
import Model.Oggetti.FactoryOggetti;
import Model.Oggetti.OggettoComune;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipaggiamentoTest {

    private Equipaggiamento equip;

    // Equipaggiamento è un singleton: lo stato persiste tra i test.
    // Essendo nello stesso package, posso usare setMaxDim (package-private) per ripristinarlo.
    @BeforeEach
    void setUp() {
        equip = Equipaggiamento.getInstance();
        equip.getListaOggettiEquipped().clear();
        equip.setMaxDim(5);
    }

    private OggettoComune nuovoOggetto(String nome) {
        return FactoryOggetti.getOggettoComune(nome, 1);
    }

    @Test
    void getInstanceRestituisceSempreLaStessaIstanza() {
        assertSame(Equipaggiamento.getInstance(), Equipaggiamento.getInstance());
    }

    @Test
    void all_inizioEVuotoEHaCinqueSlotLiberi() {
        assertTrue(equip.getListaOggettiEquipped().isEmpty());
        assertEquals(5, equip.getSlotLiberi());
    }

    @Test
    void aggiungiOggettoLoInserisceEDiminuisceGliSlotLiberi() throws EquipaggiamentoPieno {
        OggettoComune kunai = nuovoOggetto("Kunai");

        equip.aggiungiOggetto(kunai);

        assertEquals(1, equip.getListaOggettiEquipped().size());
        assertTrue(equip.getListaOggettiEquipped().contains(kunai));
        assertEquals(4, equip.getSlotLiberi());
    }

    @Test
    void siPossonoAggiungereEsattamenteCinqueOggetti() throws EquipaggiamentoPieno {
        for (int i = 0; i < 5; i++) {
            equip.aggiungiOggetto(nuovoOggetto("Oggetto" + i));
        }

        assertEquals(5, equip.getListaOggettiEquipped().size());
        assertEquals(0, equip.getSlotLiberi());
    }

    @Test
    void ilSestoOggettoLanciaEquipaggiamentoPieno() throws EquipaggiamentoPieno {
        for (int i = 0; i < 5; i++) {
            equip.aggiungiOggetto(nuovoOggetto("Oggetto" + i));
        }

        assertThrows(EquipaggiamentoPieno.class, () -> equip.aggiungiOggetto(nuovoOggetto("Extra")));
        assertEquals(5, equip.getListaOggettiEquipped().size());
    }

    @Test
    void aggiungereNullNonInserisceNulla() throws EquipaggiamentoPieno {
        equip.aggiungiOggetto(null);

        assertTrue(equip.getListaOggettiEquipped().isEmpty());
        assertEquals(5, equip.getSlotLiberi());
    }

    @Test
    void conCapienzaAumentataSiPossonoAggiungerePiuDiCinqueOggetti() throws EquipaggiamentoPieno {
        equip.setMaxDim(99);

        for (int i = 0; i < 6; i++) {
            equip.aggiungiOggetto(nuovoOggetto("Oggetto" + i));
        }

        assertEquals(6, equip.getListaOggettiEquipped().size());
        assertEquals(93, equip.getSlotLiberi());
    }
}