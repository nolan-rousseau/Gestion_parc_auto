package fr.il3.gestionparcauto.ihm.javafx.utils;

import javafx.scene.control.Alert;

public class ihmException {

    public static void showException(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue");
        alert.setContentText(txt);
        alert.showAndWait();
    }

}
