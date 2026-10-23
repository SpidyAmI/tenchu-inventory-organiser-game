package Model.Oggetti;

import Model.Eccezioni.OggettoFinito;
import Model.Eccezioni.OggettoNonSbloccato;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OggettoTest {
    private OggettoComune kunai;      // comune, sbloccato, 7 pezzi
    private OggettoComune fumo;       // comune, sbloccato, 0 pezzi
    private OggettoRaro camicia;      // raro, bloccato, 0 pezzi (max 3)
    private OggettoRaro armatura;     // raro unico, bloccato, 0 pezzi (max 1)

    // Eseguito prima di OGNI test: ogni test parte da oggetti nuovi e indipendenti
    @BeforeEach
    void setUp() {
        kunai = FactoryOggetti.getOggettoComune("Kunai", 7);
        fumo = FactoryOggetti.getOggettoComune("Fumo", 0);
        camicia = FactoryOggetti.getOggettoRaro("Camicia", false);
        armatura = FactoryOggetti.getOggettoRaro("Armatura", true);
    }

    @Nested
    @DisplayName("Stato iniziale")
    class StatoIniziale {

        @Test
        void oggettoComuneNasceSbloccato() {
            assertTrue(kunai.isUnlocked());
        }

        @Test
        void oggettoRaroNasceBloccato() {
            assertFalse(camicia.isUnlocked());
        }
    }

    @Nested
    @DisplayName("incrementa()")
    class Incrementa {

        @Test
        void aumentaLAmmontareDiUno() {
            kunai.incrementa();

            assertEquals("Kunai\n    8", kunai.toString());
        }

        @Test
        void nonSuperaIlMassimoPerIComuni() {
            kunai.incrementa();   // 8 = massimo
            kunai.incrementa();   // deve restare 8

            assertEquals("Kunai\n    8", kunai.toString());
        }

        @Test
        void nonSuperaIlMassimoPerUnRaroUnico() {
            armatura.incrementa();
            armatura.incrementa();

            assertEquals("Armatura\n    1", armatura.toString());
        }

        @Test
        void sbloccaUnRaroBloccato() {
            camicia.incrementa();

            assertTrue(camicia.isUnlocked());
        }
    }

    @Nested
    @DisplayName("readyToEquip()")
    class ReadyToEquip {

        @Test
        void nonLanciaNullaSeSbloccatoEConAmmontare() {
            assertDoesNotThrow(kunai::readyToEquip);
        }

        @Test
        void lanciaOggettoFinitoSeAmmontareZero() {
            assertThrows(OggettoFinito.class, fumo::readyToEquip);
        }

        @Test
        void lanciaOggettoNonSbloccatoSeBloccato() {
            assertThrows(OggettoNonSbloccato.class, camicia::readyToEquip);
        }

        @Test
        void raroSbloccatoMaSenzaQuantitaLanciaOggettoFinito() {
            camicia.sblocca();   // sbloccato, ma l'ammontare resta 0

            assertThrows(OggettoFinito.class, camicia::readyToEquip);
        }
    }

    @Nested
    @DisplayName("Ordinamento e uguaglianza")
    class OrdinamentoEUguaglianza {

        @Test
        void compareToOrdinaPerNome() {
            assertTrue(armatura.compareTo(camicia) < 0);
            assertTrue(camicia.compareTo(armatura) > 0);
        }

        @Test
        void oggettiConStessiCampiSonoUguali() {
            OggettoComune altroKunai = FactoryOggetti.getOggettoComune("Kunai", 7);

            assertEquals(kunai, altroKunai);
            assertEquals(kunai.hashCode(), altroKunai.hashCode());
        }

        @Test
        void oggettiConAmmontareDiversoNonSonoUguali() {
            OggettoComune altroKunai = FactoryOggetti.getOggettoComune("Kunai", 6);

            assertNotEquals(kunai, altroKunai);
        }
    }
}