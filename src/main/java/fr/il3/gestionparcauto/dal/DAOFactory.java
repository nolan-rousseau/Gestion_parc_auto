package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bll.AssignmentController;
import fr.il3.gestionparcauto.dal.jdbc.*;
import fr.il3.gestionparcauto.dal.jdbc.utils.StatsDAOJdbcImpl;

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
    public StatsDAOJdbcImpl  getStatsDAO(){ return new StatsDAOJdbcImpl(); }
}
