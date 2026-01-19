package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public class VehicleController {
    private static VehicleController vehicleController;

    public static VehicleController getController(){
        if(vehicleController ==null){
            vehicleController = new VehicleController();
        }
        return vehicleController;
    }

    public ArrayList<Vehicle> selectVehicle() throws DalException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getVehicleDAO().selectAll();
    }

    public void addVehicle(Vehicle vehicle) throws DalException {
        verifObjectVehicle(vehicle);
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getVehicleDAO().addVehicle(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) throws DalException {
        if (vehicle.getId() <= 0) {
            throw new DalException("Impossible de modifier un vehicle sans un identifiant valide.");
        }
        verifObjectVehicle(vehicle);

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getVehicleDAO().updateVehicle(vehicle);
    }

    public void deleteVehicle(int id) throws DalException {
        if (id <= 0) {
            throw new DalException("L'identifiant du vehicle à supprimer est invalide.");
        }

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getVehicleDAO().deleteVehicle(id);
    }

    private void verifObjectVehicle(Vehicle vehicle) throws DalException {
        if(vehicle.getRegistration().isEmpty()){
            throw new DalException("L'immatriculation du véhicule ne peut pas être vide.");
        }
        if(vehicle.getModel() == null){
            throw new DalException("Le modèle du véhicule ne peut pas être vide.");
        }
        if(vehicle.getMileage() == null && vehicle.getMileage() < 0){
            throw new DalException("Le kilométrage du véhicule ne peut pas être vide en négative.");
        }
        if(vehicle.getRegistrationDate() == null){
            throw new DalException("La date d'immatriculation du véhicule ne peut pas être vide.");
        }
    }
}
