package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public class ParcController {
    private static ParcController parcController;

    public static ParcController getController(){
        if(parcController ==null){
            parcController = new ParcController();
        }
        return parcController;
    }

    public ArrayList<Parc> selectFilm() throws DalException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getFilmDAO().selectAll();
    }

    public void addFilm(Parc parc) throws DalException {
        if(parc.getTitre()==null){
            throw new DalException("Le titre du film est obligatoire.");
        } else if (parc.getTitre()==null) {
            throw new DalException("Le réalisateur du film est obligatoire.");
        } else if (parc.getAnnee()<=1800) {
            throw new DalException("L'année doit être supérieur à 1800.");
        }else if (parc.getDuree()<=0) {
            throw new DalException("La durée du film doit être positive.");
        }
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getFilmDAO().addFilm(parc);
    }
}
