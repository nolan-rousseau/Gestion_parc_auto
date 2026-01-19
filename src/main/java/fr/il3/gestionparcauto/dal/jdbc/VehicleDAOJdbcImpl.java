package fr.il3.gestionparcauto.dal.jdbc;

import fr.il3.gestionparcauto.bo.Brand;
import fr.il3.gestionparcauto.bo.Model;
import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.dal.VehicleDAO;
import fr.il3.gestionparcauto.utils.DalException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class VehicleDAOJdbcImpl implements VehicleDAO {

    private final String INSERT = "INSERT INTO Vehicles (registration, model_id, mileage, registrationDate, comment) VALUES (?, ?, ?, ?, ?) ";
    private final String SELECT_ALL = "SELECT * FROM Vehicles ";
    private final String UPDATE = "UPDATE Vehicles SET registration = ?, model_id = ?, mileage = ?, registrationDate = ?, comment = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM Vehicles WHERE id = ?";

    @Override
    public void addVehicle(Vehicle vehicle) throws DalException {
        try{
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, vehicle.getRegistration());
            stmt.setInt(2, vehicle.getModel().getId());
            stmt.setLong(3, vehicle.getMileage());
            stmt.setObject(4, vehicle.getRegistrationDate());
            stmt.setString(5, vehicle.getComment());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Insertion véhicule : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Vehicle> selectAll() throws DalException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        try {
            Connection con = DAOJdbcImpl.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            Vehicle vehicle = null;
            while (rs.next()) {
                vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setRegistration(rs.getString("registration"));
                vehicle.setMileage(rs.getLong("mileage"));
                vehicle.setRegistrationDate((LocalDate) rs.getObject("registrationDate"));
                vehicle.setComment(rs.getString("comment"));

                DAOFactory daoFactory = new DAOFactory();
                ArrayList<Model> allModels = daoFactory.getModelDAO().selectAll();
                Model specificModel = allModels.stream()
                        .filter(b -> {
                            try {
                                return b.getId() == rs.getInt("model_id");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .findFirst()
                        .orElse(null);
                vehicle.setModel(specificModel);

                vehicles.add(vehicle);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Sélection des marques " + e.getMessage());
        }
        return vehicles;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE)) {

            stmt.setString(1, vehicle.getRegistration());
            stmt.setInt(2, vehicle.getModel().getId());
            stmt.setLong(3, vehicle.getMileage());
            stmt.setObject(4, vehicle.getRegistrationDate());
            stmt.setString(5, vehicle.getComment());
            stmt.setInt(6, vehicle.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la mise à jour : aucune marque trouvée avec l'ID " + vehicle.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Mise à jour marque : " + e.getMessage());
        }
    }

    @Override
    public void deleteVehicle(int id) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la suppression : aucune marque trouvée avec l'ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Suppression marque : " + e.getMessage());
        }
    }
}
