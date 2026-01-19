package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.ihm.javafx.utils.ihmException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static fr.il3.gestionparcauto.ihm.javafx.utils.OpenWindow.OpenWindow;

public class EcranController {

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
