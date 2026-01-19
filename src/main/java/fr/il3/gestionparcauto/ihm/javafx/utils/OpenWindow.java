package fr.il3.gestionparcauto.ihm.javafx.utils;

import fr.il3.gestionparcauto.ihm.javafx.EcranController;
import fr.il3.gestionparcauto.utils.DalException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpenWindow {
    public static void OpenWindow(String fxmlFile, EcranController ecranController) {
        try {
            FXMLLoader loader = new FXMLLoader(OpenWindow.class.getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);

            stage.setOnHidden(e -> {
                try {
                    ecranController.initialize();
                } catch (Exception ex) {
                    ihmWindowBox.showException("Erreur actualisation des données : " + ex.getMessage());
                }
            });

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            ihmWindowBox.showException(e.getMessage());
        }
    }
}
