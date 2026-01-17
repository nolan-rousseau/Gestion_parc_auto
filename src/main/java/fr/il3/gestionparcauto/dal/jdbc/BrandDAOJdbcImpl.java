package fr.il3.gestionparcauto.dal.jdbc;

import fr.il3.gestionparcauto.bo.Brand;
import fr.il3.gestionparcauto.dal.BrandDAO;
import fr.il3.gestionparcauto.utils.DalException;

import java.sql.*;
import java.util.ArrayList;

public class BrandDAOJdbcImpl implements BrandDAO {

    private final String INSERT = "INSERT INTO Brands(name) VALUES (?) ";
    private final String SELECT_ALL = "SELECT * FROM Brands ";
    private final String UPDATE = "UPDATE Brands SET name = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM Brands WHERE id = ?";

    @Override
    public void addBrand(Brand brand) throws DalException {
        try{
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, brand.getName());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Insertion brand : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Brand> selectAll() throws DalException {
        ArrayList<Brand> brands = new ArrayList<>();

        try {
            Connection con = DAOJdbcImpl.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            Brand brand = null;
            while (rs.next()) {
                brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));

                brands.add(brand);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Sélection des brands " + e.getMessage());
        }
        return brands;
    }

    @Override
    public void updateBrand(Brand brand) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE)) {

            stmt.setString(1, brand.getName());
            stmt.setInt(2, brand.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la mise à jour : aucun brand trouvé avec l'ID " + brand.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Mise à jour brand : " + e.getMessage());
        }
    }

    @Override
    public void deleteBrand(int id) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la suppression : aucun brand trouvé avec l'ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Suppression brand : " + e.getMessage());
        }
    }
}
