package Model.Items;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private CommonItem kunai;
    private RareItem armor;
    private RareItem katana;

    @BeforeEach
    void setUp() {
        kunai = FactoryItem.getCommonItem("Kunai", 7);
        armor = FactoryItem.getRareItem("Armor", false);
        katana = FactoryItem.getRareItem("Katana", true);
    }

    @Nested
    @DisplayName("Initial State")
    class InitialState {

        @Test
        void commonItemStartUnlocked() {
            assertTrue(kunai.isUnlocked());
        }

        @Test
        void rareItemStartLocked() {
            assertFalse(armor.isUnlocked());
        }
    }

    @Nested
    @DisplayName("increment()")
    class Increment {

        @Test
        void incrementByOne() {
            kunai.increment();

            assertEquals("Kunai\n    8", kunai.toString());
        }

        @Test
        void dontExceedCommonMax() {
            kunai.increment();   // 8 = max
            kunai.increment();   // must be 8 still

            assertEquals("Kunai\n    8", kunai.toString());
        }

        @Test
        void dontExceedRareUniqueness() {
            katana.increment(); // 1 = max
            katana.increment(); // must be 1 still

            assertEquals("Katana\n    1", katana.toString());
        }

        @Test
        void unlockRareLocked() {
            armor.increment();

            assertTrue(armor.isUnlocked());
        }
    }

    @Nested
    @DisplayName("Ordering and Equalities")
    class OrderAndEqual {

        @Test
        void compareToOrderByName() {
            assertTrue(katana.compareTo(armor) < 0);
            assertTrue(armor.compareTo(katana) > 0);
        }

        @Test
        void sameFieldAreEqualItems() {
            CommonItem otherKunai = FactoryItem.getCommonItem("Kunai", 7);

            assertEquals(kunai, otherKunai);
            assertEquals(kunai.hashCode(), otherKunai.hashCode());
        }

        @Test
        void differentFieldsAreNotEqualItems() {
            CommonItem otherKunai = FactoryItem.getCommonItem("Kunai", 6);

            assertNotEquals(kunai, otherKunai);
        }
    }
}