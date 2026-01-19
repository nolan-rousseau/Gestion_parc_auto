package fr.il3.gestionparcauto.ihm.javafx.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpenWindow {
    public static void OpenWindow(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(OpenWindow.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            ihmWindowBox.showException(e.getMessage());
        }
    }
}
