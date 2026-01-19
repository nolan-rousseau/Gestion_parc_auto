package fr.il3.gestionparcauto.ihm.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class StatsController {
    @FXML private ListView<String> listViewMileage;
    @FXML private ListView<String> listViewMostUsed;
    @FXML private ListView<String> listViewBiggestUsers;

    @FXML
    public void initialize() {
        loadStatistics();
    }

    private void loadStatistics() {
        // Stats mileage
        fillList(conn,
                "SELECT b.name, m.name, v.mileage FROM Vehicles v " +
                        "JOIN Models m ON v.model_id = m.id JOIN Brands b ON m.brand_id = b.id " +
                        "ORDER BY v.mileage DESC LIMIT 5",
                listViewMileage, "%s %s (%d km)");

        // Stats most used
        fillList(conn,
                "SELECT b.name, m.name, COUNT(a.id) FROM Assignments a " +
                        "JOIN Vehicles v ON a.vehicle_id = v.id JOIN Models m ON v.model_id = m.id " +
                        "JOIN Brands b ON m.brand_id = b.id GROUP BY v.id ORDER BY 3 DESC LIMIT 5",
                listViewMostUsed, "%s %s (%d fois)");

        // Stats biggest user
        fillList(conn,
                "SELECT e.firstname, e.lastname, COUNT(a.id) FROM Assignments a " +
                        "JOIN Employees e ON a.employee_id = e.id GROUP BY e.id ORDER BY 3 DESC LIMIT 5",
                listViewBiggestUsers, "%s %s (%d réservations)");
    }

    private void fillList(Connection conn, String query, ListView<String> list, String format) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.getItems().add(String.format(format, rs.getString(1), rs.getString(2), rs.getInt(3)));
            }
        }
    }
}