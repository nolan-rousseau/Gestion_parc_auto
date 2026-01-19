package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Assignment;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public class AssignmentController {
    private static AssignmentController assignmentController;

    public static AssignmentController getController(){
        if(assignmentController ==null){
            assignmentController = new AssignmentController();
        }
        return assignmentController;
    }

    public ArrayList<Assignment> selectAssignment() throws DalException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getAssignmentDAO().selectAll();
    }

    public void addAssignment(Assignment assignment) throws DalException {
        verifObjectAssignment(assignment);
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getAssignmentDAO().addAssignment(assignment);
    }

    public void updateAssignment(Assignment assignment) throws DalException {
        if (assignment.getId() <= 0) {
            throw new DalException("Impossible de modifier une affectation sans un identifiant valide.");
        }
        verifObjectAssignment(assignment);

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getAssignmentDAO().updateAssignment(assignment);
    }

    public void deleteAssignment(int id) throws DalException {
        if (id <= 0) {
            throw new DalException("L'identifiant de l'affectation à supprimer est invalide.");
        }

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getAssignmentDAO().deleteAssignment(id);
    }

    private void verifObjectAssignment(Assignment assignment) throws DalException {
        if(assignment.getVehicleId() <= 0){
            throw new DalException("Le véhicule de l'affectation ne peut pas être vide.");
        }
        if(assignment.getEmployeeId() <= 0){
            throw new DalException("L'employé de l'affectation ne peut pas être vide.");
        }
        if(assignment.getDateStart() == null){
            throw new DalException("La date de début de l'affectation ne peut pas être vide.");
        }
        if(assignment.getDateEnd() == null){
            throw new DalException("La date de fin de l'affectation ne peut pas être vide.");
        }
    }

    private ArrayList getAssignment(int id) throws DalException {
        ArrayList<Assignment> assignment = new ArrayList<>();
        DAOFactory daoFactory = new DAOFactory();

        return assignment;
    }
}
