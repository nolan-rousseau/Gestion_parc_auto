package fr.il3.gestionparcauto.dal.fichier;

import fr.il3.gestionparcauto.bo.Parc;
import fr.il3.gestionparcauto.dal.ParcDAO;
import fr.il3.gestionparcauto.utils.ParcException;

import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class ParcDAOFichierImpl implements ParcDAO {

    @Override
    public void addFilm(Parc parc) throws ParcException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("film.txt", true));
            writer.write(parc.getTitre() + "," + parc.getRealisateur() + "," + parc.getAnnee() + "," + parc.getDuree() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new ParcException("Il est impossible d'ajouter un nouveau film.");
        }
    }

    @Override
    public ArrayList<Parc> selectAll() throws ParcException {
        ArrayList<Parc> parcs = new ArrayList<>();
        try (FileReader fileReader = new FileReader("film.txt");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] lineSplit = line.split(",");
                parcs.add(new Parc(lineSplit[0],
                        parseInt(lineSplit[2]),
                        lineSplit[1],
                        parseInt(lineSplit[3])));
            }
        } catch (IOException e) {
            throw new ParcException("Il est impossible d'acceder aux films.");
        }
        return parcs;
    }
}
