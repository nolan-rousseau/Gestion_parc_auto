package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public interface VehicleDAO {
    public void addVehicle(Vehicle vehicle) throws DalException;
    public ArrayList<Vehicle> selectAll() throws DalException;
    public void deleteVehicle(int id) throws DalException;
    public void updateVehicle(Vehicle vehicle) throws DalException;
}
