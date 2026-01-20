package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.bll.*;
import fr.il3.gestionparcauto.bo.*;
import fr.il3.gestionparcauto.ihm.javafx.utils.ihmWindowBox;
import fr.il3.gestionparcauto.utils.DalException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Add_EmployeController implements Initializable {

    @FXML
    private ComboBox<String> comboService;

    @FXML
    private TextField textfieldFirstname;

    @FXML
    private TextField textfieldLastname;

    @FXML
    private TextField textfieldMail;

    @FXML
    private TextField textfieldPhone;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadAll();
    }

    @FXML
    private void AddEmployee(ActionEvent event) {
        try {
            Employee employee = new Employee();

            employee.setFirstName(textfieldFirstname.getText());
            employee.setLastName(textfieldLastname.getText());
            employee.setEmail(textfieldMail.getText());
            employee.setPhone(textfieldPhone.getText());
            employee.setService(ServiceController.getController().getServiceFromName(comboService.getValue()));

            EmployeeController employeeController = new EmployeeController();
            employeeController.addEmployee(employee);
            ClearValues();
            ihmWindowBox.showInformation("L'employé à été créé avec succès.");
            LoadAll();
        } catch (Exception e){
            ihmWindowBox.showException(e.getMessage());
        }
    }

    @FXML
    private void AddService(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter un service");
        dialog.setHeaderText("Nouveau service");
        dialog.setContentText("Veuillez saisir le nom du nouveau service :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                Service service = new Service();
                service.setName(name);
                ServiceController serviceController = new ServiceController();
                try {
                    serviceController.addService(service);
                    ihmWindowBox.showInformation("Le service à été créé avec succès.");
                    comboService.getItems().clear();
                    ServiceController.getController().selectService().forEach(serviceAct -> {
                        comboService.getItems().add(serviceAct.getName());
                    });
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ihmWindowBox.showException("Le nom du service ne peut pas être vide.");
            }
        });
    }

    private void LoadAll() {
        try {
            ServiceController.getController().selectService().forEach(Model -> {
                comboService.getItems().add(Model.getName());
            });
        } catch (DalException e) {
            ihmWindowBox.showException("Impossible de charger les données : " + e.getMessage());
        }
    }

    private void ClearValues() {
        comboService.getItems().clear();
        textfieldFirstname.clear();
        textfieldLastname.clear();
        textfieldMail.clear();
        textfieldPhone.clear();
    }
}
