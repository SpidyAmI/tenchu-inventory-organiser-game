import Controller.InventoryController;
import Controller.GameEngineController;
import Controller.WarehouseController;
import Model.CodesAndWarehouse.Warehouse;
import Model.GameEngine;
import Model.Items.FactoryItem;
import Model.Items.CommonItem;
import Model.Items.RareItem;
import View.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        List<CommonItem> commonItemList = new ArrayList<>();
        commonItemList.add(FactoryItem.getCommonItem("Kunai", 7));
        commonItemList.add(FactoryItem.getCommonItem("Rice", 2));
        commonItemList.add(FactoryItem.getCommonItem("Smoke", 0));
        List<RareItem> rareItemList = new ArrayList<>();
        rareItemList.add(FactoryItem.getRareItem("Armor", false));
        rareItemList.add(FactoryItem.getRareItem("Katana", true));
        rareItemList.add(FactoryItem.getRareItem("Fire", false));
        Collections.sort(rareItemList);
        Collections.sort(commonItemList);
        InventoryController ic = new InventoryController();
        Warehouse warehouse = new Warehouse(commonItemList, rareItemList);
        WarehouseController wc = new WarehouseController(warehouse, ic);
        GameEngine ge = new GameEngine(warehouse);
        GameEngineController gec = new GameEngineController(ge, wc);
        MainView rootView = new MainView(gec);
        gec.setMv(rootView);
        wc.setMv(rootView);
        ic.setMv(rootView);

        primaryStage.setTitle("Tenchu 1");
        primaryStage.setScene(new Scene(rootView, 200,250));
        primaryStage.show();
    }
}
