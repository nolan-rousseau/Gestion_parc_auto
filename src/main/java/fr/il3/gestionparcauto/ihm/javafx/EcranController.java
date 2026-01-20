package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.bll.AssignmentController;
import fr.il3.gestionparcauto.bll.EmployeeController;
import fr.il3.gestionparcauto.bo.Assignment;
import fr.il3.gestionparcauto.bll.VehicleController;
import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.bo.Employee;
import fr.il3.gestionparcauto.ihm.javafx.utils.OpenWindow;
import fr.il3.gestionparcauto.ihm.javafx.utils.ihmWindowBox;
import fr.il3.gestionparcauto.utils.DalException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static fr.il3.gestionparcauto.ihm.javafx.utils.OpenWindow.OpenWindow;

public class EcranController {

    @FXML private ListView<Vehicle> listViewVehicles;

    @FXML private ListView<Employee> listViewEmployees;

    @FXML private TableView<Assignment> tableViewAssignments;
    @FXML private TableColumn<Assignment, String> vehicleCol;
    @FXML private TableColumn<Assignment, String> employeeCol;
    @FXML private TableColumn<Assignment, LocalDate> startCol;
    @FXML private TableColumn<Assignment, LocalDate> endCol;
    @FXML private TableColumn<Assignment, Assignment> statusCol;

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

        choiceBoxStatus.setItems(FXCollections.observableArrayList("Toutes", "Actives", "En cours", "Terminées"));
        choiceBoxStatus.getSelectionModel().selectFirst();

        choiceBoxService.getItems().add("Tous les services");
        masterEmployeeList.stream().map(e -> e.getService().getName()).distinct().forEach(choiceBoxService.getItems()::add);
        choiceBoxService.getSelectionModel().selectFirst();

        vehicleCol.setCellValueFactory(cell -> {
            Assignment a = cell.getValue();
            if (a.getVehicle() != null) {
                return new SimpleStringProperty(a.getVehicle().toString());
            } else {
                return new SimpleStringProperty("[Véhicule supprimé]");
            }
        });
        employeeCol.setCellValueFactory(cell -> {
            Assignment a = cell.getValue();
            if (a.getEmployee() != null) {
                return new SimpleStringProperty(a.getEmployee().toString());
            } else {
                return new SimpleStringProperty("[Employé supprimé]");
            }
        });
        startCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getDateStart()));
        endCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue().getDateEnd()));

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
                        textAreaInfoVehicle.setText(newAssignment.getInfo());
                    } else {
                        textAreaInfoVehicle.clear();
                    }
                });
        listViewEmployees
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldAssignment, newAssignment) -> {
                    if (newAssignment != null) {
                        textAreaInfoEmployee.setText(newAssignment.getInfo());
                    } else {
                        textAreaInfoEmployee.clear();
                    }
                });

        statusCol.setCellValueFactory(cell -> new javafx.beans.property.SimpleObjectProperty<>(cell.getValue()));

        statusCol.setCellFactory(column -> new TableCell<Assignment, Assignment>() {
            @Override
            protected void updateItem(Assignment item, boolean empty) {
                super.updateItem(item, empty);

                if (item == null || empty) {
                    setGraphic(null);
                } else {
                    Color color;
                    switch (item.isActive()){
                        case 1: color = Color.ORANGE; break;
                        case 2: color = Color.GREEN; break;
                        case 3: color = Color.RED; break;
                        default: color = Color.BLACK;
                    }

                    javafx.scene.shape.Circle indicator = new javafx.scene.shape.Circle(8);
                    indicator.setFill(color);
                    setGraphic(indicator);
                    setAlignment(javafx.geometry.Pos.CENTER);
                }
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

    @FXML
    private void ModifyAssignment(ActionEvent event) {
        Assignment assignmentSelected = tableViewAssignments.getSelectionModel().getSelectedItem();

        if (assignmentSelected == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner une affectation à modifier.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/il3/gestionparcauto/fxml/Edit_Assignment.fxml"));
            Parent root = loader.load();

            Edit_AssignmentController controller = loader.getController();
            controller.UpdateControlsWithAssignment(assignmentSelected);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnHidden(e -> {
                try {
                    this.initialize();
                } catch (DalException ex) {
                    ex.printStackTrace();
                }
            });

            stage.show();
        } catch (Exception e) {
            ihmWindowBox.showException("Erreur lors de l'ouverture de la fenêtre : " + e.getMessage());
        }
    }

    @FXML
    private void DeleteAssignment(ActionEvent event) {
        Assignment assignmentSelected = tableViewAssignments.getSelectionModel().getSelectedItem();

        if (assignmentSelected == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un employé à supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression de l'affectation : " + assignmentSelected.toString());
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet affectation ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                AssignmentController.getController().deleteAssignment(assignmentSelected.getId());
                ihmWindowBox.showInformation("L'affectation a été supprimée avec succès.");
                initialize();
            } catch (Exception e) {
                ihmWindowBox.showException(e.getMessage());
            }
        }
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
        OpenWindow("/fr/il3/gestionparcauto/fxml/Stats.fxml", this);
    }

    @FXML
    private void AddEmployee(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Add_Employee.fxml", this);
    }

    @FXML
    private void ModifyEmployee(ActionEvent event) {
        Employee employeeSelected = listViewEmployees.getSelectionModel().getSelectedItem();

        if (employeeSelected == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un employé à modifier.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/il3/gestionparcauto/fxml/Edit_Employee.fxml"));
            Parent root = loader.load();

            Edit_EmployeeController controller = loader.getController();
            controller.UpdateControlsWithEmployee(employeeSelected);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnHidden(e -> {
                try {
                    this.initialize();
                } catch (DalException ex) {
                    ex.printStackTrace();
                }
            });

            stage.show();
        } catch (Exception e) {
            ihmWindowBox.showException("Erreur lors de l'ouverture de la fenêtre : " + e.getMessage());
        }
    }

    @FXML
    private void DeleteEmployee(ActionEvent event) {
        Employee employeeSelected = listViewEmployees.getSelectionModel().getSelectedItem();

        if (employeeSelected == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un employé à supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression de l'employé : " + employeeSelected.toString());
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cet employé ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                EmployeeController.getController().deleteEmployee(employeeSelected.getId());
                ihmWindowBox.showInformation("L'employé a été supprimé avec succès.");
                initialize();
            } catch (Exception e) {
                ihmWindowBox.showException(e.getMessage());
            }
        }
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
    }

    @FXML
    private void ModifyVehicle(ActionEvent event) {
        Vehicle vehicleSelected = listViewVehicles.getSelectionModel().getSelectedItem();

        if (vehicleSelected == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un véhicule à modifier.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/il3/gestionparcauto/fxml/Edit_Vehicle.fxml"));
            Parent root = loader.load();

            Edit_VehicleController controller = loader.getController();
            controller.UpdateControlsWithVehicle(vehicleSelected);

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            stage.setOnHidden(e -> {
                try {
                    this.initialize();
                } catch (DalException ex) {
                    ex.printStackTrace();
                }
            });

            stage.show();
        } catch (Exception e) {
            ihmWindowBox.showException("Erreur lors de l'ouverture de la fenêtre : " + e.getMessage());
        }
    }

    @FXML
    private void DeleteVehicle(ActionEvent event) {
        Vehicle vehicleSelected = listViewVehicles.getSelectionModel().getSelectedItem();

        if (vehicleSelected == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un véhicule à supprimer.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression du véhicule : " + vehicleSelected.toString());
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce véhicule ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                VehicleController.getController().deleteVehicle(vehicleSelected.getId());
                ihmWindowBox.showInformation("Le véhicule a été supprimé avec succès.");
                initialize();
            } catch (Exception e) {
                ihmWindowBox.showException(e.getMessage());
            }
        }
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

    @FXML
    private void ManageLists(ActionEvent event) {
        OpenWindow("/fr/il3/gestionparcauto/fxml/Manage_Lists.fxml", this);
    }
}
