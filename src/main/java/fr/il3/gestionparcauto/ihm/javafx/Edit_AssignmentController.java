package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.bll.AssignmentController;
import fr.il3.gestionparcauto.bll.EmployeeController;
import fr.il3.gestionparcauto.bll.VehicleController;
import fr.il3.gestionparcauto.bo.Assignment;
import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.ihm.javafx.utils.ihmWindowBox;
import fr.il3.gestionparcauto.utils.DalException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Edit_AssignmentController implements Initializable {

    private Assignment assignmentToEdit;

    @FXML
    private ComboBox<Employee> comboEmployee;

    @FXML
    private ComboBox<Vehicle> comboVehicle;

    @FXML
    private TextField textfieldComment;

    @FXML
    private DatePicker datepickerStart;

    @FXML
    private DatePicker datepickerEnd;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadAll();
    }

    @FXML
    private void AddAssignment(ActionEvent event) {
        try {
            Assignment assignment = new Assignment();

            assignment.setEmployee(comboEmployee.getValue());
            assignment.setVehicle(comboVehicle.getValue());
            assignment.setComment(textfieldComment.getText());
            assignment.setDateStart(datepickerStart.getValue());
            assignment.setDateEnd(datepickerEnd.getValue());

            AssignmentController assignmentController = new AssignmentController();
            assignmentController.addAssignment(assignment);
            ClearValues();
            ihmWindowBox.showInformation("L'affectation à été créé avec succès.");
            LoadAll();
        } catch (Exception e){
            ihmWindowBox.showException(e.getMessage());
        }
    }


    private void LoadAll() {
        try {
            comboEmployee.getItems().clear();
            comboVehicle.getItems().clear();

            ArrayList<Employee> employees = EmployeeController.getController().selectEmployee();
            ArrayList<Vehicle> vehicles = VehicleController.getController().selectVehicle();

            comboEmployee.getItems().addAll(employees);
            comboVehicle.getItems().addAll(vehicles);
        } catch (DalException e) {
            ihmWindowBox.showException("Impossible de charger les données : " + e.getMessage());
        }
    }

    private void ClearValues() {
        comboEmployee.getItems().clear();
        comboVehicle.getItems().clear();
        textfieldComment.clear();
        datepickerStart.setValue(null);
        datepickerEnd.setValue(null);
    }

    protected void UpdateControlsWithAssignment(Assignment assignment) {
//        this.assignmentToEdit = assignment;
//        if (assignment != null) {
//            if (assignment.getService() != null) {
//                comboService.setValue(employee.getService().getName());
//            }
//
//            textfieldFirstname.setText(employee.getFirstName());
//            textfieldLastname.setText(employee.getLastName());
//            textfieldMail.setText(employee.getEmail());
//            textfieldPhone.setText(employee.getPhone());
//
//        }
    }
}
