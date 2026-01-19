package fr.il3.gestionparcauto.dal.jdbc;

import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.dal.EmployeeDAO;
import fr.il3.gestionparcauto.utils.DalException;

import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAOJdbcImpl implements EmployeeDAO {

    private final String INSERT = "INSERT INTO Employees (firstname, lastname, service_id, phone, mail) VALUES (?, ?, ?, ?, ?) ";
    private final String SELECT_ALL = "SELECT * FROM Employees ";
    private final String UPDATE = "UPDATE Employees SET firstname = ?, lastname = ?, service_id = ?, phone = ?, mail = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM Employees WHERE id = ?";

    @Override
    public void addEmployee(Employee employee) throws DalException {
        try{
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setInt(3, employee.getServiceId());
            stmt.setString(4, employee.getPhone());
            stmt.setString(5, employee.getEmail());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Insertion employé : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Employee> selectAll() throws DalException {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            Connection con = DAOJdbcImpl.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            Employee employee = null;
            while (rs.next()) {
                employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("firstname"));
                employee.setLastName(rs.getString("lastname"));
                employee.setEmail(rs.getString("mail"));
                employee.setPhone(rs.getString("phone"));
                employee.setServiceId(rs.getInt("service_id"));


                employees.add(employee);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Sélection des employé " + e.getMessage());
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE)) {

            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setInt(3, employee.getServiceId());
            stmt.setString(4, employee.getPhone());
            stmt.setString(5, employee.getEmail());

            stmt.setInt(6, employee.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la mise à jour : aucun employé trouvé avec l'ID " + employee.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Mise à jour employé : " + e.getMessage());
        }
    }

    @Override
    public void deleteEmployee(int id) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la suppression : aucun employé trouvé avec l'ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Suppression employé : " + e.getMessage());
        }
    }
}
