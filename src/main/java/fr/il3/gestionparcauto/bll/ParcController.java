package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Parc;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.ParcException;

import java.util.ArrayList;

public class ParcController {
    private static ParcController parcController;

    public static ParcController getController(){
        if(parcController ==null){
            parcController = new ParcController();
        }
        return parcController;
    }

    public ArrayList<Parc> selectFilm() throws ParcException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getFilmDAO().selectAll();
    }

    public void addFilm(Parc parc) throws ParcException {
        if(parc.getTitre()==null){
            throw new ParcException("Le titre du film est obligatoire.");
        } else if (parc.getTitre()==null) {
            throw new ParcException("Le réalisateur du film est obligatoire.");
        } else if (parc.getAnnee()<=1800) {
            throw new ParcException("L'année doit être supérieur à 1800.");
        }else if (parc.getDuree()<=0) {
            throw new ParcException("La durée du film doit être positive.");
        }
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getFilmDAO().addFilm(parc);
    }
}
