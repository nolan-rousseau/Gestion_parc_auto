package fr.il3.gestionparcauto.ihm.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ecran extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Ecran.class.getResource("/fr/il3/gestionparcauto/fxml/Ecran.fxml"));
            Pane root = loader.load();


//            EcranController controller = loader.getController();
//            controller.afficherFilm(controller.getFilm(controller.indexFilm));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

