package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Service;
import fr.il3.gestionparcauto.utils.DalException;
import javafx.scene.control.ListView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface StatsDAO {
    private void fillList(Connection conn, String query, ListView<String> list, String format) throws SQLException {}
    public void mileage(ListView<String> listView) throws SQLException;
    public void used(ListView<String> listView) throws SQLException;
    public void user(ListView<String> listView) throws SQLException;
}
