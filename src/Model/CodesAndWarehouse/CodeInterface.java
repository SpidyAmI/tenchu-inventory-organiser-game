package Model.CodesAndWarehouse;

public interface CodeInterface {

    /**
     * Each code receives the inventory and the warehouse and applies its effect when executed.
     */
    void execute(Inventory i, Warehouse w);
    String getCode();
}
