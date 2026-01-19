package fr.il3.gestionparcauto.dal.jdbc;

import fr.il3.gestionparcauto.bo.Assignment;
import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.bo.Service;
import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.dal.AssignmentDAO;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class AssignmentDAOJdbcImpl implements AssignmentDAO {

    private final String INSERT = "INSERT INTO Assignments(vehicle_id, employee_id, dateStart, dateEnd, comment) VALUES (?, ?, ?, ?, ?) ";
    private final String SELECT_ALL = "SELECT * FROM Assignments ";
    private final String UPDATE = "UPDATE Assignments SET vehicle_id = ?, employee_id = ?, dateStart = ?, dateEnd = ?, comment = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM Assignments WHERE id = ?";

    @Override
    public void addAssignment(Assignment assignment) throws DalException {
        try{
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, assignment.getVehicle().getId());
            stmt.setInt(2, assignment.getEmployee().getId());
            stmt.setObject(3, assignment.getDateStart());
            stmt.setObject(4, assignment.getDateEnd());
            stmt.setString(5, assignment.getComment());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Insertion affectation : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Assignment> selectAll() throws DalException {
        ArrayList<Assignment> assignments = new ArrayList<>();

        try {
            Connection con = DAOJdbcImpl.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            Assignment assignment = null;
            while (rs.next()) {
                assignment = new Assignment();
                assignment.setId(rs.getInt("id"));
                assignment.setDateStart(rs.getDate("dateStart").toLocalDate());
                assignment.setDateEnd(rs.getDate("dateEnd").toLocalDate());
                assignment.setComment(rs.getString("comment"));

                DAOFactory daoFactory = new DAOFactory();
                ArrayList<Vehicle> allVehicles = daoFactory.getVehicleDAO().selectAll();
                Vehicle specificVehicule = allVehicles.stream()
                        .filter(b -> {
                            try {
                                return b.getId() == rs.getInt("vehicle_id");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .findFirst()
                        .orElse(null);
                assignment.setVehicle(specificVehicule);
                ArrayList<Employee> allEmployees = daoFactory.getEmployeeDAO().selectAll();
                Employee specificEmployee = allEmployees.stream()
                        .filter(b -> {
                            try {
                                return b.getId() == rs.getInt("employee_id");
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .findFirst()
                        .orElse(null);
                assignment.setEmployee(specificEmployee);

                assignments.add(assignment);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Sélection des affectations " + e.getMessage());
        }
        return assignments;
    }

    @Override
    public void updateAssignment(Assignment assignment) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE)) {

            stmt.setInt(1, assignment.getVehicle().getId());
            stmt.setInt(2, assignment.getEmployee().getId());
            stmt.setObject(3, assignment.getDateStart());
            stmt.setObject(4, assignment.getDateEnd());
            stmt.setString(5, assignment.getComment());
            stmt.setInt(6, assignment.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la mise à jour : aucune affectation trouvée avec l'ID " + assignment.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Mise à jour affectation : " + e.getMessage());
        }
    }

    @Override
    public void deleteAssignment(int id) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la suppression : aucune affectation trouvée avec l'ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Suppression affectation : " + e.getMessage());
        }
    }
}
