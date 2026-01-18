package fr.il3.gestionparcauto.dal.jdbc;

import fr.il3.gestionparcauto.bo.Model;
import fr.il3.gestionparcauto.dal.ModelDAO;
import fr.il3.gestionparcauto.utils.DalException;

import java.sql.*;
import java.util.ArrayList;

public class ModelDAOJdbcImpl implements ModelDAO {

    private final String INSERT = "INSERT INTO Models(brand_id, name) VALUES (?, ?) ";
    private final String SELECT_ALL = "SELECT * FROM Models ";
    private final String UPDATE = "UPDATE Models SET brand_id = ?, name = ? WHERE id = ?";
    private final String DELETE = "DELETE FROM Models WHERE id = ?";

    @Override
    public void addModel(Model model) throws DalException {
        try{
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, model.getBrandId());
            stmt.setString(2, model.getName());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Insertion modèle : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Model> selectAll() throws DalException {
        ArrayList<Model> models = new ArrayList<>();

        try {
            Connection con = DAOJdbcImpl.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL);
            Model model = null;
            while (rs.next()) {
                model = new Model();
                model.setId(rs.getInt("id"));
                model.setBrandId(rs.getInt("brand_id"));
                model.setName(rs.getString("name"));

                models.add(model);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Sélection des modèles " + e.getMessage());
        }
        return models;
    }

    @Override
    public void updateModel(Model model) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(UPDATE)) {

            stmt.setInt(1, model.getBrandId());
            stmt.setString(2, model.getName());
            stmt.setInt(3, model.getId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la mise à jour : aucun modèle trouvé avec l'ID " + model.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Mise à jour modèle : " + e.getMessage());
        }
    }

    @Override
    public void deleteModel(int id) throws DalException {
        try (
            Connection con = DAOJdbcImpl.getConnection();
            PreparedStatement stmt = con.prepareStatement(DELETE)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DalException("Échec de la suppression : aucun modèle trouvé avec l'ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DalException("Erreur BDD - Suppression modèle : " + e.getMessage());
        }
    }
}
