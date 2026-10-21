import Controller.EquipaggiamentoController;
import Controller.GameEngineController;
import Controller.InventarioController;
import Model.CodiciEItems.Inventario;
import Model.GameEngine;
import Model.Oggetti.FactoryOggetti;
import Model.Oggetti.OggettoComune;
import Model.Oggetti.OggettoRaro;
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

        List<OggettoComune> listaOggettiComuni = new ArrayList<>();
        listaOggettiComuni.add(FactoryOggetti.getOggettoComune("Kunai", 7));
        listaOggettiComuni.add(FactoryOggetti.getOggettoComune("Riso", 2));
        listaOggettiComuni.add(FactoryOggetti.getOggettoComune("Fumo", 0));
        List<OggettoRaro> listaOggettiRari = new ArrayList<>();
        listaOggettiRari.add(FactoryOggetti.getOggettoRaro("Camicia", false));
        listaOggettiRari.add(FactoryOggetti.getOggettoRaro("Armatura", true));
        listaOggettiRari.add(FactoryOggetti.getOggettoRaro("Fuoco", false));
        // Ordino come richiesto per nome sfruttando l'ordinamento naturale dato che ho fatto implementare ad Oggetto abstract Comparable<Oggetto>
        Collections.sort(listaOggettiRari);
        Collections.sort(listaOggettiComuni);
        EquipaggiamentoController ec = new EquipaggiamentoController();
        Inventario inventario = new Inventario(listaOggettiComuni, listaOggettiRari);
        InventarioController ic = new InventarioController(inventario, ec);
        GameEngine ge = new GameEngine(inventario);
        GameEngineController gec = new GameEngineController(ge, ic);
        MainView rootView = new MainView(gec);
        gec.setMv(rootView);
        ic.setMv(rootView);
        ec.setMv(rootView);

        primaryStage.setTitle("Tenchu 1");
        primaryStage.setScene(new Scene(rootView, 200,250));
        primaryStage.show();
    }
}
