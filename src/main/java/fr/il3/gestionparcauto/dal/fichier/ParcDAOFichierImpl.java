package fr.il3.gestionparcauto.dal.fichier;

import fr.il3.gestionparcauto.bo.Film;
import fr.il3.gestionparcauto.dal.FilmDAO;
import fr.il3.gestionparcauto.utils.FilmException;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class FilmDAOFichierImpl implements FilmDAO {

    @Override
    public void addFilm(Film film) throws FilmException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("film.txt", true));
            writer.write(film.getTitre() + "," + film.getRealisateur() + "," + film.getAnnee() + "," + film.getDuree() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new FilmException("Il est impossible d'ajouter un nouveau film.");
        }
    }

    @Override
    public ArrayList<Film> selectAll() throws FilmException {
        ArrayList<Film> films = new ArrayList<>();
        try (FileReader fileReader = new FileReader("film.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split(",");
                films.add(new Film(lineSplit[0],
                        parseInt(lineSplit[2]),
                        lineSplit[1],
                        parseInt(lineSplit[3])));
            }
        } catch (IOException e) {
            throw new FilmException("Il est impossible d'acceder aux films.");
        }
        return films;
    }
}
