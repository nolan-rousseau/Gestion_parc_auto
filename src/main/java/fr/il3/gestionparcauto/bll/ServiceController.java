package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Service;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;
import java.util.ArrayList;

public class ServiceController {
    private static ServiceController serviceController;

    public static ServiceController getController(){
        if(serviceController ==null){
            serviceController = new ServiceController();
        }
        return serviceController;
    }

    public ArrayList<Service> selectService() throws DalException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getServiceDAO().selectAll();
    }

    public void addService(Service service) throws DalException {
        if(service.getName()==null){
            throw new DalException("Le nouveau nom du service ne peut pas être vide.");
        }
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getServiceDAO().addService(service);
    }

    public void updateService(Service service) throws DalException {
        if (service.getId() <= 0) {
            throw new DalException("Impossible de modifier un service sans un identifiant valide.");
        }
        if (service.getName() == null || service.getName().isEmpty()) {
            throw new DalException("Le nouveau nom du service ne peut pas être vide.");
        }

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getServiceDAO().updateService(service);
    }

    public void deleteService(int id) throws DalException {
        if (id <= 0) {
            throw new DalException("L'identifiant du service à supprimer est invalide.");
        }

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getServiceDAO().deleteService(id);
    }
}
