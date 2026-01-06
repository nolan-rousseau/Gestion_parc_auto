package fr.il3.gestionparcauto.dal.jdbc;

import fr.il3.gestionparcauto.bo.Parc;
import fr.il3.gestionparcauto.dal.ParcDAO;
import fr.il3.gestionparcauto.utils.ParcException;

import java.sql.*;
import java.util.ArrayList;

public class ParcDAOJdbcImpl implements ParcDAO {

    private final String URL = JdbcTools.getProperty("url");
    private final String USERNAME = JdbcTools.getProperty("username");
    private final String PASSWORD = JdbcTools.getProperty("password");

    private final String INSERT = "INSERT INTO Films(titre, duree, annee, realisateur) VALUES (?, ?, ?, ?) ";
    private final String SELECT = "SELECT * FROM Films";

    @Override
    public void addFilm(Parc parc) throws ParcException {
        try{
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, parc.getTitre());
            stmt.setInt(2, parc.getDuree());
            stmt.setInt(3, parc.getAnnee());
            stmt.setString(4, parc.getRealisateur());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new ParcException("Erreur BDD : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Parc> selectAll() throws ParcException {
        ArrayList<Parc> parcs = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT);
            Parc parc = null;
            while (rs.next()) {
                parc = new Parc();
                parc.setTitre(rs.getString("titre"));
                parc.setDuree(rs.getInt("duree"));
                parc.setAnnee(rs.getInt("annee"));
                parc.setRealisateur(rs.getString("realisateur"));

                parcs.add(parc);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new ParcException("Erreur BDD : " + e.getMessage());
        }
        return parcs;
    }
}
