package Model.CodesAndWarehouse;

import Model.Exceptions.InventoryFull;
import Model.Items.FactoryItem;
import Model.Items.CommonItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    private Inventory equip;

    // Inventory is Singleton: state persists between tests.
    // Being in the same package, we can use setMaxDim (package-private) to reset it.
    @BeforeEach
    void setUp() {
        equip = Inventory.getInstance();
        equip.getEquippedItemsList().clear();
        equip.setMaxDim(5);
    }

    private CommonItem newItem(String name) {
        return FactoryItem.getCommonItem(name, 1);
    }

    @Test
    void getInstanceReturnsAlwaysSameInstance() {
        assertSame(Inventory.getInstance(), Inventory.getInstance());
    }

    @Test
    void startsEmptyWithFiveFreeSlots() {
        assertTrue(equip.getEquippedItemsList().isEmpty());
        assertEquals(5, equip.getAvailableSlots());
    }

    @Test
    void addItemInsertsItAndDecreaseFreeSlots() throws InventoryFull {
        CommonItem kunai = newItem("Kunai");

        equip.addItem(kunai);

        assertEquals(1, equip.getEquippedItemsList().size());
        assertTrue(equip.getEquippedItemsList().contains(kunai));
        assertEquals(4, equip.getAvailableSlots());
    }


    @Test
    void normalSituationSixthItemThrowsException() throws InventoryFull {
        for (int i = 0; i < 5; i++) {
            equip.addItem(newItem("Item" + i));
        }

        assertThrows(InventoryFull.class, () -> equip.addItem(newItem("Extra")));
        assertEquals(5, equip.getEquippedItemsList().size());
    }

    @Test
    void addingNullDoesntEffectivelyAdd() throws InventoryFull {
        equip.addItem(null);

        assertTrue(equip.getEquippedItemsList().isEmpty());
        assertEquals(5, equip.getAvailableSlots());
    }
}