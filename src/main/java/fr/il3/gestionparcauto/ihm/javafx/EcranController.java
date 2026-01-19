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
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

import static fr.il3.gestionparcauto.ihm.javafx.utils.OpenWindow.OpenWindow;

public class EcranController {

    @FXML private ListView<Vehicle> listViewVehicles;

    @FXML private ListView<Employee> listViewEmployees;

    @FXML private TableView<Assignment> tableViewAssignments;
    @FXML private TableColumn<Assignment, String> vehicleCol;
    @FXML private TableColumn<Assignment, String> employeeCol;
    @FXML private TableColumn<Assignment, LocalDate> startCol;
    @FXML private TableColumn<Assignment, LocalDate> endCol;

    @FXML private TextArea textAreaInfoVehicle;
    @FXML private TextArea textAreaInfoAssignment;
    @FXML private TextArea textAreaInfoEmployee;

    @FXML private ChoiceBox<Object> choiceBoxBrand;
    @FXML private ChoiceBox<String> choiceBoxStatus;
    @FXML private ChoiceBox<Object> choiceBoxService;

    private FilteredList<Vehicle> filteredVehicles;
    private FilteredList<Assignment> filteredAssignments;
    private FilteredList<Employee> filteredEmployees;

    @FXML
    public void initialize() throws DalException {
        ObservableList<Vehicle> masterVehicleList = FXCollections.observableArrayList(VehicleController.getController().selectVehicle());
        ObservableList<Employee> masterEmployeeList = FXCollections.observableArrayList(EmployeeController.getController().selectEmployee());
        ObservableList<Assignment> masterAssignmentList = FXCollections.observableArrayList(AssignmentController.getController().selectAssignment());

        filteredVehicles = new FilteredList<>(masterVehicleList, p -> true);
        filteredEmployees = new FilteredList<>(masterEmployeeList, p -> true);
        filteredAssignments = new FilteredList<>(masterAssignmentList, p -> true);

        listViewVehicles.setItems(filteredVehicles);
        listViewEmployees.setItems(filteredEmployees);
        tableViewAssignments.setItems(filteredAssignments);

        choiceBoxBrand.getItems().add("Toutes les marques");
        masterVehicleList.stream().map(v -> v.getModel().getBrand().getName()).distinct().forEach(choiceBoxBrand.getItems()::add);
        choiceBoxBrand.getSelectionModel().selectFirst();

        choiceBoxStatus.setItems(FXCollections.observableArrayList("Toutes", "Actives", "Terminées"));
        choiceBoxStatus.getSelectionModel().selectFirst();

        choiceBoxService.getItems().add("Tous les services");
        masterEmployeeList.stream().map(e -> e.getService().getName()).distinct().forEach(choiceBoxService.getItems()::add);
        choiceBoxService.getSelectionModel().selectFirst();

        // initialize table columns
        vehicleCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getVehicle().toString()));
        employeeCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleStringProperty(cell.getValue().getEmployee().toString()));
        startCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getDateStart()));
        endCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getDateEnd()));

        // update text fields
        tableViewAssignments
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldAssignment, newAssignment) -> {
                    if (newAssignment != null) {
                        textAreaInfoAssignment.setText(newAssignment.getComment());
                    } else {
                        textAreaInfoAssignment.clear();
                    }
                });
        listViewVehicles
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldAssignment, newAssignment) -> {
                    if (newAssignment != null) {
                        textAreaInfoVehicle.setText(newAssignment.getComment());
                    } else {
                        textAreaInfoVehicle.clear();
                    }
                });
        listViewEmployees
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldAssignment, newAssignment) -> {
                    if (newAssignment != null) {
                        textAreaInfoEmployee.setText(newAssignment.getInformations());
                    } else {
                        textAreaInfoEmployee.clear();
                    }
                });
    }

    @FXML
    private void filterVehicles() {
        Object selected = choiceBoxBrand.getValue();
        filteredVehicles.setPredicate(vehicle -> {
            if (selected == null || selected.equals("Toutes les marques")) return true;
            return vehicle.getModel().getBrand().getName().equals(selected.toString());
        });
    }

    @FXML
    private void filterEmployees() {
        Object selected = choiceBoxService.getValue();
        filteredEmployees.setPredicate(employee -> {
            if (selected == null || selected.equals("Tous les services")) return true;
            return employee.getService().getName().equals(selected.toString());
        });
    }

    @FXML
    private void filterAssignments() {
        String selected = choiceBoxStatus.getValue();
        LocalDate today = LocalDate.now();
        filteredAssignments.setPredicate(a -> {
            if (selected == null || selected.equals("Toutes")) return true;
            boolean isActive = !a.getDateStart().isAfter(today) && !a.getDateEnd().isBefore(today);
            return selected.equals("Actives") ? isActive : !isActive;
        });
    }

    @FXML
    private void CreateAssignment(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Add_Assignment.fxml", this);
    }

    private String escapeCsv(String text) {
        if (text == null) return "";
        return text.replace(";", ",")
                .replace("\n", " ")
                .replace("\r", "");
    }

    @FXML
    private void ExportAssignments(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter les affectations");

        fileChooser.setInitialFileName("export_assignments_" + LocalDate.now() + ".csv");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV (*.csv)", "*.csv")
        );

        File file = fileChooser.showSaveDialog(null);
        if (file == null) return;

        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            writer.write('\ufeff');
            writer.write("Véhicule;Employé;Début;Fin;Commentaire");
            writer.newLine();

            for (Assignment a : tableViewAssignments.getItems()) {
                writer.write(
                        a.getVehicle().toString() + ";" +
                                a.getEmployee().toString() + ";" +
                                a.getDateStart() + ";" +
                                a.getDateEnd() + ";" +
                                a.getComment()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ShowStats(ActionEvent event) {
    }

    @FXML
    private void AddEmployee(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Add_Employee.fxml", this);
    }
    @FXML
    private void ModifyEmployee(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Edit_Employee.fxml", this);
    }

    @FXML
    private void DeleteEmployee(ActionEvent event) {
    }

    @FXML
    private void ExportEmployees(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter les agents");

        fileChooser.setInitialFileName("export_employees_" + LocalDate.now() + ".csv");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV (*.csv)", "*.csv")
        );

        File file = fileChooser.showSaveDialog(null);
        if (file == null) return;

        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            writer.write('\ufeff');
            writer.write("Prénom;Nom;Email;Téléphone;Service");
            writer.newLine();

            for (Employee a : listViewEmployees.getItems()) {
                writer.write(
                        a.getFirstName() + ";" +
                                a.getLastName() + ";" +
                                a.getEmail() + ";" +
                                a.getPhone() + ";" +
                                a.getService()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddVehicle(ActionEvent event) throws DalException {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Add_Vehicle.fxml", this);
        initialize();
    }

    @FXML
    private void ModifyVehicle(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Edit_Vehicle.fxml", this);
    }

    @FXML
    private void DeleteVehicle(ActionEvent event) {
    }

    @FXML
    private void ExportVehicles(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Exporter les véhicules");

        fileChooser.setInitialFileName("export_vehicles_" + LocalDate.now() + ".csv");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV (*.csv)", "*.csv")
        );

        File file = fileChooser.showSaveDialog(null);
        if (file == null) return;

        try (FileOutputStream fos = new FileOutputStream(file);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
             BufferedWriter writer = new BufferedWriter(osw)) {

            writer.write('\ufeff');
            writer.write("Marque;Modèle;Immatriculation;Date d'immatriculation;Kilométrage");
            writer.newLine();

            for (Vehicle a : listViewVehicles.getItems()) {
                writer.write(
                        a.getModel().getBrand() + ";" +
                                a.getModel() + ";" +
                                a.getRegistration() + ";" +
                                a.getRegistrationDate() + ";" +
                                a.getMileage()
                );
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
