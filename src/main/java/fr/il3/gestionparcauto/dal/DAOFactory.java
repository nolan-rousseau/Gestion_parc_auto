package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.dal.jdbc.ServiceDAOJdbcImpl;

public class DAOFactory {

    public DAOFactory() {    }

    public ServiceDAOJdbcImpl getServiceDAO(){
        return new ServiceDAOJdbcImpl();
    }
}
