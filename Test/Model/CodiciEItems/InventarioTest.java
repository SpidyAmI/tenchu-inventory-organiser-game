package Model.CodiciEItems;

import Model.Eccezioni.EquipaggiamentoPieno;
import Model.Oggetti.FactoryOggetti;
import Model.Oggetti.OggettoComune;
import Model.Oggetti.OggettoInterface;
import Model.Oggetti.OggettoRaro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventarioTest {

    private OggettoComune kunai;      // sbloccato, 7 pezzi
    private OggettoComune fumo;       // sbloccato, 0 pezzi
    private OggettoRaro camicia;      // bloccato, 0 pezzi (max 3)
    private OggettoRaro armatura;     // bloccato, 0 pezzi (max 1)
    private Inventario inventario;
    private Equipaggiamento equip;

    @BeforeEach
    void setUp() {
        kunai = FactoryOggetti.getOggettoComune("Kunai", 7);
        fumo = FactoryOggetti.getOggettoComune("Fumo", 0);
        camicia = FactoryOggetti.getOggettoRaro("Camicia", false);
        armatura = FactoryOggetti.getOggettoRaro("Armatura", true);

        inventario = new Inventario(
                new ArrayList<>(List.of(kunai, fumo)),
                new ArrayList<>(List.of(camicia, armatura)));

        // Ripristino il singleton (stesso package, quindi setMaxDim è accessibile)
        equip = Equipaggiamento.getInstance();
        equip.getListaOggettiEquipped().clear();
        equip.setMaxDim(5);
    }

    // ---------- getOggettoInterfaceList ----------

    @Test
    void getOggettoInterfaceListContieneComuniERari() {
        List<OggettoInterface> lista = inventario.getOggettoInterfaceList();

        assertEquals(4, lista.size());
        assertTrue(lista.containsAll(List.of(kunai, fumo, camicia, armatura)));
    }

    @Test
    void getOggettoInterfaceListRestituisceUnaNuovaListaOgniVolta() {
        List<OggettoInterface> lista = inventario.getOggettoInterfaceList();
        lista.clear();

        assertEquals(4, inventario.getOggettoInterfaceList().size());
    }

    // ---------- equipaggia ----------

    @Test
    void equipaggiaDecrementaLAmmontareEAggiungeLOggetto() throws EquipaggiamentoPieno {
        inventario.equipaggia(kunai);

        assertEquals("Kunai\n    6", kunai.toString());
        assertTrue(equip.getListaOggettiEquipped().contains(kunai));
        assertEquals(4, equip.getSlotLiberi());
    }

    @Test
    void equipaggiaConEquipaggiamentoPienoLanciaEccezioneENonDecrementa() throws EquipaggiamentoPieno {
        for (int i = 0; i < 5; i++) {
            equip.aggiungiOggetto(FactoryOggetti.getOggettoComune("Riempitivo" + i, 1));
        }

        assertThrows(EquipaggiamentoPieno.class, () -> inventario.equipaggia(kunai));

        assertEquals("Kunai\n    7", kunai.toString());
        assertFalse(equip.getListaOggettiEquipped().contains(kunai));
    }

    // ---------- metodi package-private usati dai codici ----------

    @Test
    void sbloccaTuttiIRariSbloccaSoloIRari() {
        inventario.sbloccaTuttiIRari();

        assertTrue(camicia.isUnlocked());
        assertTrue(armatura.isUnlocked());
    }

    @Test
    void aumentaSbloccatiIncrementaSoloGliOggettiSbloccati() {
        inventario.aumentaSbloccati();

        assertEquals("Kunai\n    8", kunai.toString());
        assertEquals("Fumo\n    1", fumo.toString());
        assertFalse(camicia.isUnlocked());
        assertEquals("Camicia\n    0", camicia.toString());
    }

    @Test
    void aumentaSbloccatiDopoSbloccoDeiRariIncrementaAncheQuelli() {
        inventario.sbloccaTuttiIRari();

        inventario.aumentaSbloccati();

        assertEquals("Camicia\n    1", camicia.toString());
        assertEquals("Armatura\n    1", armatura.toString());
    }
}