package fr.il3.gestionparcauto.dal.jdbc;

import fr.il3.gestionparcauto.bo.Service;
import fr.il3.gestionparcauto.dal.ServiceDAO;
import fr.il3.gestionparcauto.utils.DalException;

import java.sql.*;
import java.util.ArrayList;

public class ServiceDAOJdbcImpl implements ServiceDAO {

    private final String INSERT = "INSERT INTO Services(name) VALUES (?) ";
    private final String SELECT_ALL = "SELECT * FROM Services ";
    private final String UPDATE = "UPDATE Services SET name = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM Services WHERE id = ?";

    @Override
    public void addService(Service service) throws DalException {
        try{
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, service.getName());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Insertion service : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Service> selectAll() throws DalException {
        ArrayList<Service> services = new ArrayList<>();

        try {
            Connection con = DAOJdbcImpl.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            Service service = null;
            while (rs.next()) {
                service = new Service();
                service.setId(rs.getInt("id"));
                service.setName(rs.getString("name"));

                services.add(service);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Sélection des services " + e.getMessage());
        }
        return services;
    }

    @Override
    public void updateService(Service service) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE)) {

            stmt.setString(1, service.getName());
            stmt.setInt(2, service.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la mise à jour : aucun service trouvé avec l'ID " + service.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Mise à jour service : " + e.getMessage());
        }
    }

    @Override
    public void deleteService(int id) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la suppression : aucun service trouvé avec l'ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Suppression service : " + e.getMessage());
        }
    }
}
