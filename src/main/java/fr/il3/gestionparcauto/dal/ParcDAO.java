package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Parc;
import fr.il3.gestionparcauto.utils.ParcException;

import java.util.ArrayList;

public interface ParcDAO {
    public void addFilm(Parc parc) throws ParcException;
    public ArrayList<Parc> selectAll() throws ParcException;
}
