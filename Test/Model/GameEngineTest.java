package Model;

import Model.CodesAndWarehouse.Inventory;
import Model.CodesAndWarehouse.Warehouse;
import Model.Exceptions.NotExistingCode;
import Model.Exceptions.NinjaException;
import Model.Items.FactoryItem;
import Model.Items.CommonItem;
import Model.Items.RareItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private CommonItem kunai;
    private RareItem armor;
    private GameEngine ge;

    @BeforeEach
    void setUp() {
        kunai = FactoryItem.getCommonItem("Kunai", 7);
        armor = FactoryItem.getRareItem("Armor", false);

        List<CommonItem> commons = new ArrayList<>(List.of(kunai));
        List<RareItem> rares = new ArrayList<>(List.of(armor));

        ge = new GameEngine(new Warehouse(commons, rares));

        // Singleton mantains state between tests, here is emptied.
        Inventory.getInstance().getEquippedItemsList().clear();
    }

    @Test
    void dwaUnlockAllRares() throws NinjaException {
        assertFalse(armor.isUnlocked());

        ge.tryCode("DWA");

        assertTrue(armor.isUnlocked());
    }

    @Test
    void ddsIncrementUnlockedItems() throws NinjaException {
        ge.tryCode("DDS");

        assertEquals("Kunai\n    8", kunai.toString());
    }

    @Test
    void ddsDontUnlock() throws NinjaException {
        ge.tryCode("DDS");

        assertFalse(armor.isUnlocked());
        assertEquals("Armor\n    0", armor.toString());
    }

    @Test
    void ddaIncreaseInventorySlots() throws NinjaException {
        ge.tryCode("DDA");

        assertEquals(99, Inventory.getInstance().getAvailableSlots());
    }

    @Test
    void notExistingCodeTriggerNotExistingCode() {
        assertThrows(NotExistingCode.class, () -> ge.tryCode("xyz"));
    }

}