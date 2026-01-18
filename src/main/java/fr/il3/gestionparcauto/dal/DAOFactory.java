package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bll.AssignmentController;
import fr.il3.gestionparcauto.dal.jdbc.*;

public class DAOFactory {

    public DAOFactory() {    }

    public ServiceDAOJdbcImpl getServiceDAO(){
        return new ServiceDAOJdbcImpl();
    }
    public BrandDAOJdbcImpl getBrandDAO(){
        return new BrandDAOJdbcImpl();
    }
    public ModelDAOJdbcImpl getModelDAO(){
        return new ModelDAOJdbcImpl();
    }
    public EmployeeDAOJdbcImpl getEmployeeDAO(){ return new EmployeeDAOJdbcImpl(); }
    public VehicleDAOJdbcImpl getVehicleDAO(){ return new VehicleDAOJdbcImpl(); }
    public AssignmentDAOJdbcImpl getAssignmentDAO() { return new AssignmentDAOJdbcImpl(); }
}
