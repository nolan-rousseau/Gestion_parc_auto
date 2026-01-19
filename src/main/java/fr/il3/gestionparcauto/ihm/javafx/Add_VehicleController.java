package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.bll.BrandController;
import fr.il3.gestionparcauto.bll.ModelController;
import fr.il3.gestionparcauto.bll.VehicleController;
import fr.il3.gestionparcauto.bo.Model;
import fr.il3.gestionparcauto.bo.Vehicle;
import fr.il3.gestionparcauto.ihm.javafx.utils.ihmWindowBox;
import fr.il3.gestionparcauto.utils.DalException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Add_VehicleController implements Initializable {

    private ArrayList<Model> models;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000000, 0);
        spinnerMileage.setValueFactory(valueFactory);

        LoadAll();
    }

    @FXML
    private void AddVehicle(ActionEvent event) {
        try {
            Vehicle vehicle = new Vehicle();

            vehicle.setModel(ModelController.getController().selectModel().getFirst());
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

    private void LoadAll() {
        try {
            ModelController.getController().selectModel().forEach(Model -> {
                comboModel.getItems().add(Model.getName() + Model.getId());
            });
            BrandController.getController().selectBrand().forEach(Brand -> {
                comboBrand.getItems().add(Brand.getName());
            });
        } catch (DalException e) {
            ihmWindowBox.showException("Impossible de charger les données : " + e.getMessage());
        }
    }

    private void ClearValues() {
        comboBrand.getSelectionModel().clearSelection();
        comboModel.getSelectionModel().clearSelection();
        textfieldRegistration.clear();
        textfieldComment.clear();
        datepickerRegistrationDate.setValue(null);
        spinnerMileage.getValueFactory().setValue(0);
    }
}
