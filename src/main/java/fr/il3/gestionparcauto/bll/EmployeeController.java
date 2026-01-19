package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public class EmployeeController {
    private static EmployeeController employeeController;

    public static EmployeeController getController(){
        if(employeeController  == null){
            employeeController = new EmployeeController();
        }
        return employeeController;
    }

    public ArrayList<Employee> selectEmployee() throws DalException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getEmployeeDAO().selectAll();
    }

    public void addEmployee(Employee employee) throws DalException {
        verifObjectEmployee(employee);
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getEmployeeDAO().addEmployee(employee);
    }

    public void updateEmployee(Employee employee) throws DalException {
        if (employee.getId() <= 0) {
            throw new DalException("Impossible de modifier un employé sans un identifiant valide.");
        }
        verifObjectEmployee(employee);
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getEmployeeDAO().updateEmployee(employee);
    }

    public void deleteEmployee(int id) throws DalException {
        if (id <= 0) {
            throw new DalException("L'identifiant de l'employé à supprimer est invalide.");
        }

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getEmployeeDAO().deleteEmployee(id);
    }

    private void verifObjectEmployee(Employee employee) throws DalException {
        if(employee.getFirstName() == null){
            throw new DalException("Le prénom de l'employé ne peut pas être vide.");
        }
        if(employee.getLastName() == null){
            throw new DalException("Le nom de l'employé ne peut pas être vide.");
        }
        if(employee.getService() == null){
            throw new DalException("Le service de l'employé ne peut pas être vide.");
        }
        if(employee.getEmail() == null){
            throw new DalException("Le mail de l'employé ne peut pas être vide.");
        }
        if(employee.getPhone() == null){
            throw new DalException("Le numéro de télephone de l'employé ne peut pas être vide.");
        }
    }
}
