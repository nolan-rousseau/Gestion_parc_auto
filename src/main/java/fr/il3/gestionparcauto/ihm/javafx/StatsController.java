package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.dal.jdbc.DAOJdbcImpl;
import fr.il3.gestionparcauto.dal.jdbc.JdbcTools;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;
import java.sql.Connection;
import java.sql.Statement;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.*;



public class StatsController {
    @FXML private ListView<String> listViewMileage;
    @FXML private ListView<String> listViewMostUsed;
    @FXML private ListView<String> listViewBiggestUsers;

    @FXML
    public void initialize() throws SQLException {
        loadStatistics();
    }

    private void loadStatistics() throws SQLException {
        // Stats mileage
        DAOFactory factory = new DAOFactory();
        factory.getStatsDAO().mileage(listViewMileage);
        factory.getStatsDAO().used(listViewMostUsed);
        factory.getStatsDAO().user(listViewBiggestUsers);
    }


}