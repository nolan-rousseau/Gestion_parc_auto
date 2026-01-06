package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.dal.fichier.ParcDAOFichierImpl;

public class DAOFactory {

    public DAOFactory() {    }

    public ParcDAOFichierImpl getFilmDAO(){
        return new ParcDAOFichierImpl();
    }
}
