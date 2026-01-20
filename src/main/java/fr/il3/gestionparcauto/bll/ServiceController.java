package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Service;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

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
        verifObjectService(service);

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getServiceDAO().addService(service);
    }

    public void updateService(Service service) throws DalException {
        if (service.getId() <= 0) {
            throw new DalException("Impossible de modifier un service sans un identifiant valide.");
        }
        verifObjectService(service);

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

    private void verifObjectService(Service service) throws DalException {
        if(service.getName().isEmpty()){
            throw new DalException("Le nom du service ne peut pas être vide.");
        }
    }

    public Service getServiceFromName(String name) throws DalException {
        Service specificService = selectService().stream()
                .filter(b -> {
                    try {
                        return Objects.equals(b.getName(), name);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .findFirst()
                .orElse(null);
        return specificService;
    }

    public boolean serviceAttributed(Service service) throws DalException {
        AtomicBoolean result = new AtomicBoolean(false);
        EmployeeController.getController().selectEmployee().forEach(employee -> {
            if (employee.getService().getId() == service.getId()) {
                result.set(true);
            }
        });
        return result.get();
    }
}
