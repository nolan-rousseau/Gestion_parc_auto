package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.bo.Model;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public class ModelController {
    private static ModelController modelController;

    public static ModelController getController(){
        if(modelController ==null){
            modelController = new ModelController();
        }
        return modelController;
    }

    public ArrayList<Model> selectModel() throws DalException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getModelDAO().selectAll();
    }

    public void addModel(Model model) throws DalException {
        verifObjectModel(model);
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getModelDAO().addModel(model);
    }

    public void updateModel(Model model) throws DalException {
        if (model.getId() <= 0) {
            throw new DalException("Impossible de modifier un modèle sans un identifiant valide.");
        }
        verifObjectModel(model);

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getModelDAO().updateModel(model);
    }

    public void deleteModel(int id) throws DalException {
        if (id <= 0) {
            throw new DalException("L'identifiant du modèle à supprimer est invalide.");
        }

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getModelDAO().deleteModel(id);
    }

    private void verifObjectModel(Model model) throws DalException {
        if (model.getName() == null) {
            throw new DalException("Le nom du modèle ne peut pas être vide.");
        }
        if (model.getBrand().getId() <= 0) {
            throw new DalException("La marque du modèle ne peut pas être vide.");
        }
    }
}
