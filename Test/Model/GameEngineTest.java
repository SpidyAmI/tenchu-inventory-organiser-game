package Model;

import Model.CodiciEItems.Equipaggiamento;
import Model.CodiciEItems.Inventario;
import Model.Eccezioni.CodiceNonEsistente;
import Model.Eccezioni.NinjaException;
import Model.Oggetti.FactoryOggetti;
import Model.Oggetti.OggettoComune;
import Model.Oggetti.OggettoRaro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private OggettoComune kunai;
    private OggettoRaro camicia;
    private GameEngine ge;

    @BeforeEach
    void setUp() {
        kunai = FactoryOggetti.getOggettoComune("Kunai", 7);
        camicia = FactoryOggetti.getOggettoRaro("Camicia", false);

        List<OggettoComune> comuni = new ArrayList<>(List.of(kunai));
        List<OggettoRaro> rari = new ArrayList<>(List.of(camicia));

        ge = new GameEngine(new Inventario(comuni, rari));

        // Il singleton mantiene lo stato tra i test: lo svuoto
        Equipaggiamento.getInstance().getListaOggettiEquipped().clear();
    }

    @Test
    void dwaSbloccaTuttiIRari() throws NinjaException {
        assertFalse(camicia.isUnlocked());

        ge.tentaCodice("DWA");

        assertTrue(camicia.isUnlocked());
    }

    @Test
    void ddsIncrementaGliOggettiSbloccati() throws NinjaException {
        ge.tentaCodice("DDS");

        assertEquals("Kunai\n    8", kunai.toString());
    }

    @Test
    void ddsNonSbloccaGliOggettiBloccati() throws NinjaException {
        ge.tentaCodice("DDS");

        assertFalse(camicia.isUnlocked());
        assertEquals("Camicia\n    0", camicia.toString());
    }

    @Test
    void ddaAumentaLaCapienzaDellEquipaggiamento() throws NinjaException {
        ge.tentaCodice("DDA");

        assertEquals(99, Equipaggiamento.getInstance().getSlotLiberi());
    }

    @Test
    void codiceInesistenteLanciaCodiceNonEsistente() {
        assertThrows(CodiceNonEsistente.class, () -> ge.tentaCodice("XYZ"));
    }

    @Test
    void ilConfrontoDelCodiceEDistingueMaiuscoleEMinuscole() {
        assertThrows(CodiceNonEsistente.class, () -> ge.tentaCodice("dda"));
    }
}