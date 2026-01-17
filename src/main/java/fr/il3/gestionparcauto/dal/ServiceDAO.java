package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Service;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public interface ServiceDAO {
    public void addService(Service service) throws DalException;
    public ArrayList<Service> selectAll() throws DalException;
    public void deleteService(int id) throws DalException;
    public void updateService(Service service) throws DalException;
}
