package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public interface EmployeeDAO {
    public void addEmployee(Employee employee) throws DalException;
    public ArrayList<Employee> selectAll() throws DalException;
    public void deleteEmployee(int id) throws DalException;
    public void updateEmployee(Employee employee) throws DalException;
}
