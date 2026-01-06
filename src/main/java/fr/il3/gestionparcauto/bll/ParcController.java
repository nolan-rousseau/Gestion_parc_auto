package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Film;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.FilmException;

import java.util.ArrayList;

public class FilmController {
    private static FilmController filmController;

    public static FilmController getController(){
        if(filmController==null){
            filmController = new FilmController();
        }
        return filmController;
    }

    public ArrayList<Film> selectFilm() throws FilmException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getFilmDAO().selectAll();
    }

    public void addFilm(Film film) throws FilmException {
        if(film.getTitre()==null){
            throw new FilmException("Le titre du film est obligatoire.");
        } else if (film.getTitre()==null) {
            throw new FilmException("Le réalisateur du film est obligatoire.");
        } else if (film.getAnnee()<=1800) {
            throw new FilmException("L'année doit être supérieur à 1800.");
        }else if (film.getDuree()<=0) {
            throw new FilmException("La durée du film doit être positive.");
        }
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getFilmDAO().addFilm(film);
    }
}
