package Controller.Alerts;

import javafx.scene.control.Alert;

public class Alerts {

    public static void launchError(String info){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText(info);
        alert.showAndWait();
    }
}
