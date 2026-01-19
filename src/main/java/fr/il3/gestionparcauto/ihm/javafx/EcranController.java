package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.bll.AssignmentController;
import fr.il3.gestionparcauto.bll.EmployeeController;
import fr.il3.gestionparcauto.bo.Assignment;
import fr.il3.gestionparcauto.bll.VehicleController;
import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.utils.DalException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.List;

import static fr.il3.gestionparcauto.ihm.javafx.utils.OpenWindow.OpenWindow;

public class EcranController {

    @FXML private ListView<Vehicle> listViewVehicles;

    @FXML private ListView<Employee> listViewAgents;

    @FXML private TableView<Assignment> tableViewAssignments;
    @FXML private TableColumn<Assignment, String> vehicleCol;
    @FXML private TableColumn<Assignment, String> employeeCol;
    @FXML private TableColumn<Assignment, LocalDate> startCol;
    @FXML private TableColumn<Assignment, LocalDate> endCol;


    @FXML
    private void initialize() throws DalException {
        // get data lists from database
        List<Vehicle> vehicles = VehicleController.getController().selectVehicle();
        ObservableList<Vehicle> vehicleList = FXCollections.observableArrayList(vehicles);

        List<Employee> employees = EmployeeController.getController().selectEmployee();
        ObservableList<Employee> EmployeeList = FXCollections.observableArrayList(employees);

        List<Assignment> assignments = AssignmentController.getController().selectAssignment();
        ObservableList<Assignment> AssignmentList = FXCollections.observableArrayList(assignments);

        vehicleCol.setCellValueFactory(
                cell -> new javafx.beans.property.SimpleStringProperty(
                        cell.getValue().getVehicleId().toString()
                )
        );

        // fill listViews
        listViewVehicles.setItems(vehicleList);
        listViewAgents.setItems(EmployeeList);
        tableViewAssignments.setItems(AssignmentList);
    }

    @FXML
    private void CreateAssignment(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Add_Assignment.fxml");
    }

    @FXML
    private void ExportAssignments(ActionEvent event) {
    }

    @FXML
    private void ShowStats(ActionEvent event) {
    }

    @FXML
    private void AddEmployee(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Add_Employee.fxml");
    }
    @FXML
    private void ModifyEmployee(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Edit_Employee.fxml");
    }

    @FXML
    private void DeleteEmployee(ActionEvent event) {
    }

    @FXML
    private void ExportEmployees(ActionEvent event) {
    }

    @FXML
    private void AddVehicle(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Add_Vehicle.fxml");
    }

    @FXML
    private void ModifyVehicle(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Edit_Vehicle.fxml");
    }

    @FXML
    private void DeleteVehicle(ActionEvent event) {
    }

    @FXML
    private void ExportVehicles(ActionEvent event) {
    }
}
