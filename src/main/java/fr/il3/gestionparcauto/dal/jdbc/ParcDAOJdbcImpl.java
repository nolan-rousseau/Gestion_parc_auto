package fr.il3.gestionparcauto.dal.jdbc;

import fr.il3.gestionparcauto.bo.Film;
import fr.il3.gestionparcauto.dal.FilmDAO;
import fr.il3.gestionparcauto.utils.FilmException;

import java.sql.*;
import java.util.ArrayList;

public class FilmDAOJdbcImpl implements FilmDAO {

    private final String URL = JdbcTools.getProperty("url");
    private final String USERNAME = JdbcTools.getProperty("username");
    private final String PASSWORD = JdbcTools.getProperty("password");

    private final String INSERT = "INSERT INTO Films(titre, duree, annee, realisateur) VALUES (?, ?, ?, ?) ";
    private final String SELECT = "SELECT * FROM Films";

    @Override
    public void addFilm(Film film) throws FilmException {
        try{
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, film.getTitre());
            stmt.setInt(2, film.getDuree());
            stmt.setInt(3, film.getAnnee());
            stmt.setString(4, film.getRealisateur());

            stmt.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
            throw new FilmException("Erreur BDD : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Film> selectAll() throws FilmException {
        ArrayList<Film> films = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT);
            Film film = null;
            while (rs.next()) {
                film = new Film();
                film.setTitre(rs.getString("titre"));
                film.setDuree(rs.getInt("duree"));
                film.setAnnee(rs.getInt("annee"));
                film.setRealisateur(rs.getString("realisateur"));

                films.add(film);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            throw new FilmException("Erreur BDD : " + e.getMessage());
        }
        return films;
    }
}
