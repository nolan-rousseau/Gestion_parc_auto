package fr.il3.gestionparcauto.dal.jdbc.utils;

import fr.il3.gestionparcauto.dal.StatsDAO;
import fr.il3.gestionparcauto.dal.jdbc.DAOJdbcImpl;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatsDAOJdbcImpl implements StatsDAO {
    private void fillList(Connection conn, String query, ListView<String> list, String format) throws SQLException {
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            int rank = 1;
            while (rs.next()) {
                list.getItems().add(String.format("[" + rank + "] - " + format,
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3)));
                rank++;
            }
        }
    }

    public void mileage(ListView<String> listView) throws SQLException {
        fillList(DAOJdbcImpl.getConnection(),
                "SELECT b.name, m.name, v.mileage FROM Vehicles v " +
                        "JOIN Models m ON v.model_id = m.id JOIN Brands b ON m.brand_id = b.id " +
                        "ORDER BY v.mileage DESC LIMIT 5",
                listView, "%s %s (%d km)");
    }

    public void used(ListView<String> listView) throws SQLException {
        fillList(DAOJdbcImpl.getConnection(),
                "SELECT b.name, m.name, COUNT(a.id) FROM Assignments a " +
                        "JOIN Vehicles v ON a.vehicle_id = v.id JOIN Models m ON v.model_id = m.id " +
                        "JOIN Brands b ON m.brand_id = b.id GROUP BY v.id ORDER BY 3 DESC LIMIT 5",
                listView, "%s %s (%d fois)");
    }

    public void user(ListView<String> listView) throws SQLException {
        fillList(DAOJdbcImpl.getConnection(),
                "SELECT e.firstname, e.lastname, COUNT(a.id) FROM Assignments a " +
                        "JOIN Employees e ON a.employee_id = e.id GROUP BY e.id ORDER BY 3 DESC LIMIT 5",
                listView, "%s %s (%d réservations)");
    }
}
