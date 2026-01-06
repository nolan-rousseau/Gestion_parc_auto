package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Film;
import fr.il3.gestionparcauto.utils.FilmException;

import java.util.ArrayList;

public interface FilmDAO {
    public void addFilm(Film film) throws FilmException;
    public ArrayList<Film> selectAll() throws FilmException;
}
