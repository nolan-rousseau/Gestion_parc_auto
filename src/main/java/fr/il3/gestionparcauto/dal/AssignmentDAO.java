package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Assignment;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public interface AssignmentDAO {
    public void addAssignment(Assignment assignment) throws DalException;
    public ArrayList<Assignment> selectAll() throws DalException;
    public void deleteAssignment(int id) throws DalException;
    public void updateAssignment(Assignment assignment) throws DalException;
}
