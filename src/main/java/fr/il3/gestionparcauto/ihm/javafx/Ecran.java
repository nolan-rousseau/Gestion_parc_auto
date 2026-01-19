package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.dal.jdbc.DAOJdbcImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Ecran extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Ecran.class.getResource("/fr/il3/gestionparcauto/fxml/Ecran.fxml"));
            Pane root = loader.load();


            EcranController controller = loader.getController();
            try {
                DAOJdbcImpl.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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

