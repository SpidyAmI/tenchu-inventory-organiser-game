package Model.CodesAndWarehouse;

import Model.Exceptions.InventoryFull;
import Model.Items.FactoryItem;
import Model.Items.CommonItem;
import Model.Items.ItemInterface;
import Model.Items.RareItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private CommonItem kunai;
    private CommonItem smokeBomb;
    private RareItem armor;
    private RareItem katana;
    private Warehouse warehouse;
    private Inventory inventory;

    @BeforeEach
    void setUp() {
        kunai = FactoryItem.getCommonItem("Kunai", 7);
        smokeBomb = FactoryItem.getCommonItem("Smoke bomb", 0);
        armor = FactoryItem.getRareItem("Armor", false);
        katana = FactoryItem.getRareItem("Katana", true);

        warehouse = new Warehouse(
                new ArrayList<>(List.of(kunai, smokeBomb)),
                new ArrayList<>(List.of(armor, katana)));

        // Resetting singleton
        inventory = Inventory.getInstance();
        inventory.getEquippedItemsList().clear();
        inventory.setMaxDim(5);
    }

    // ---------- getItemInterfaceList ----------

    @Test
    void getItemInterfaceListContainsCommonsAndRares() {
        List<ItemInterface> list = warehouse.getItemInterfaceList();

        assertEquals(4, list.size());
        assertTrue(list.containsAll(List.of(kunai, smokeBomb, armor, katana)));
    }

    @Test
    void getItemInterfaceListReturnsAlwaysANewList() {
        List<ItemInterface> list = warehouse.getItemInterfaceList();
        list.clear();

        assertEquals(4, warehouse.getItemInterfaceList().size());
    }

    // ---------- equip ----------

    @Test
    void equipItemAndDecrementAvailableSlots() throws InventoryFull {
        warehouse.equip(kunai);

        assertEquals("Kunai\n    6", kunai.toString());
        assertTrue(inventory.getEquippedItemsList().contains(kunai));
        assertEquals(4, inventory.getAvailableSlots());
    }

    @Test
    void equipWithFullInventoryDontDecreaseNorThrowException() throws InventoryFull {
        for (int i = 0; i < 5; i++) {
            inventory.addItem(FactoryItem.getCommonItem("Try" + i, 1));
        }

        assertThrows(InventoryFull.class, () -> warehouse.equip(kunai));

        assertEquals("Kunai\n    7", kunai.toString());
        assertFalse(inventory.getEquippedItemsList().contains(kunai));
    }

    // ---------- package-private methods used by the codes ----------

    @Test
    void unlockAllRaresAndUniqueRares() {
        warehouse.unlockAllRares();

        assertTrue(armor.isUnlocked());
        assertTrue(katana.isUnlocked());
    }

    @Test
    void increaseUnlocked() {
        warehouse.increaseUnlocked();

        assertEquals("Kunai\n    8", kunai.toString());
        assertEquals("Smoke bomb\n    1", smokeBomb.toString());
        assertFalse(armor.isUnlocked());
        assertEquals("Armor\n    0", armor.toString());
    }

    @Test
    void increaseUnlockedWithUnlockedRaresIncrementAlsoRares() {
        warehouse.unlockAllRares();

        warehouse.increaseUnlocked();

        assertEquals("Armor\n    1", armor.toString());
        assertEquals("Katana\n    1", katana.toString());
    }
}