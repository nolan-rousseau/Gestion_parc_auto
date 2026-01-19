package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.bll.BrandController;
import fr.il3.gestionparcauto.bll.ModelController;
import fr.il3.gestionparcauto.bll.VehicleController;
import fr.il3.gestionparcauto.bo.Brand;
import fr.il3.gestionparcauto.bo.Model;
import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.ihm.javafx.utils.ihmWindowBox;
import fr.il3.gestionparcauto.utils.DalException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Add_VehicleController implements Initializable {

    @FXML
    private ComboBox<String> comboBrand;

    @FXML
    private ComboBox<String> comboModel;

    @FXML
    private TextField textfieldRegistration;

    @FXML
    private TextField textfieldComment;

    @FXML
    private DatePicker datepickerRegistrationDate;

    @FXML
    private Spinner<Integer> spinnerMileage;

    @FXML
    private TextField textfieldNewModel;

    @FXML
    private CheckBox checkboxAddModel;

    @FXML
    private Label labelBrand;

    @FXML
    private Label labelNewModel;

    @FXML
    private Button btnAddBrand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000, 0);
        spinnerMileage.setValueFactory(valueFactory);

        comboBrand.visibleProperty().bind(checkboxAddModel.selectedProperty());
        comboBrand.managedProperty().bind(comboBrand.visibleProperty());

        labelNewModel.visibleProperty().bind(checkboxAddModel.selectedProperty());
        labelNewModel.managedProperty().bind(labelNewModel.visibleProperty());

        labelBrand.visibleProperty().bind(checkboxAddModel.selectedProperty());
        labelBrand.managedProperty().bind(labelBrand.visibleProperty());

        btnAddBrand.visibleProperty().bind(checkboxAddModel.selectedProperty());
        btnAddBrand.managedProperty().bind(btnAddBrand.visibleProperty());

        textfieldNewModel.visibleProperty().bind(checkboxAddModel.selectedProperty());
        textfieldNewModel.managedProperty().bind(textfieldNewModel.visibleProperty());

        comboModel.disableProperty().bind(checkboxAddModel.selectedProperty());

        LoadAll();
    }

    @FXML
    private void AddVehicle(ActionEvent event) {
        try {
            Vehicle vehicle = new Vehicle();

            vehicle.setModel(ModelController.getController().getModelFromName(comboModel.getValue()));
            vehicle.setRegistration(textfieldRegistration.getText());
            vehicle.setComment(textfieldComment.getText());
            vehicle.setRegistrationDate(datepickerRegistrationDate.getValue());

            if (spinnerMileage.getValue() != null) {
                vehicle.setMileage(spinnerMileage.getValue().longValue());
            }

            VehicleController vehicleController = new VehicleController();
            vehicleController.addVehicle(vehicle);
            ClearValues();
            ihmWindowBox.showInformation("Le véhicule à été créé avec succès.");
            LoadAll();
        } catch (Exception e){
            ihmWindowBox.showException(e.getMessage());
        }
    }

    @FXML
    private void AddBrand(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter une marque");
        dialog.setHeaderText("Nouvelle marque de véhicule");
        dialog.setContentText("Veuillez saisir le nom de la marque :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                Brand brand = new Brand();
                brand.setName(name);
                BrandController brandController = new BrandController();
                try {
                    brandController.addBrand(brand);
                    ihmWindowBox.showInformation("La marque à été créé avec succès.");
                    comboBrand.getItems().clear();
                    BrandController.getController().selectBrand().forEach(Brand -> {
                        comboBrand.getItems().add(Brand.getName());
                    });
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ihmWindowBox.showException("Le nom de la marque ne peut pas être vide.");
            }
        });
    }

    private void LoadAll() {
        try {
            ModelController.getController().selectModel().forEach(Model -> {
                comboModel.getItems().add(Model.getName());
            });
            BrandController.getController().selectBrand().forEach(Brand -> {
                comboBrand.getItems().add(Brand.getName());
            });
        } catch (DalException e) {
            ihmWindowBox.showException("Impossible de charger les données : " + e.getMessage());
        }
    }

    private void ClearValues() {
        comboBrand.getItems().clear();
        comboModel.getItems().clear();
        textfieldRegistration.clear();
        textfieldComment.clear();
        datepickerRegistrationDate.setValue(null);
        spinnerMileage.getValueFactory().setValue(0);
    }
}
