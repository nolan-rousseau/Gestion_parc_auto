package fr.il3.gestionparcauto.ihm.javafx.utils;

import javafx.scene.control.Alert;

public class ihmWindowBox {

    public static void showException(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue");
        alert.setContentText(txt);
        alert.showAndWait();
    }

    public static void showInformation(String txt) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        //alert.setHeaderText("Une erreur est survenue");
        alert.setContentText(txt);
        alert.showAndWait();
    }

}
