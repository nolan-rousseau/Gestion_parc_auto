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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

public class Manage_ListsController implements Initializable {

    @FXML
    private ComboBox<String> comboBrand;

    @FXML
    private ComboBox<String> comboModel;

    @FXML
    private ComboBox<String> comboService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadAll();
    }

    @FXML
    private void AddBrand(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter une marque");
        dialog.setHeaderText("Nouvelle marque");
        dialog.setContentText("Veuillez saisir le nom de la nouvelle marque :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                Brand brand = new Brand();
                brand.setName(name);
                BrandController brandController = new BrandController();
                try {
                    brandController.addBrand(brand);
                    ihmWindowBox.showInformation("La marque à été créée avec succès.");
                    LoadAll();
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ihmWindowBox.showException("Le nom de la marque ne peut pas être vide.");
            }
        });
    }

    @FXML
    private void ModifyBrand(ActionEvent event) throws DalException {
        Brand brand = BrandController.getController().getBrandFromName(comboBrand.getSelectionModel().getSelectedItem());

        if (brand == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner une marque à renommer.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Renommer une marque");
        dialog.setHeaderText("Renommer la marque");
        dialog.setContentText("Veuillez saisir le nouveau nom de la marque :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                brand.setName(name);
                BrandController brandController = new BrandController();
                try {
                    brandController.updateBrand(brand);
                    ihmWindowBox.showInformation("La marque à été renommée avec succès.");
                    LoadAll();
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ihmWindowBox.showException("Le nom du service ne peut pas être vide.");
            }
        });
    }

    @FXML
    private void DeleteBrand(ActionEvent event) throws DalException {
        Brand brand = BrandController.getController().getBrandFromName(comboBrand.getSelectionModel().getSelectedItem());

        if (brand == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner une marque à supprimer.");
            return;
        }

        if (BrandController.getController().brandAttributed(brand)) {
            ihmWindowBox.showInformation("Cette marque ne peux pas être supprimée car elle est affectée à un modèle.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression de la marque : " + brand.toString());
        alert.setContentText("Êtes-vous sûr de vouloir supprimer cette marque ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                BrandController.getController().deleteBrand(brand.getId());
                ihmWindowBox.showInformation("La marque a été supprimée avec succès.");
                LoadAll();
            } catch (Exception e) {
                ihmWindowBox.showException(e.getMessage());
            }
        }
    }

    @FXML
    private void AddModel(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Ajouter un modèle");
        dialog.setHeaderText("Nouveau modèle");
        dialog.setContentText("Veuillez saisir le nom du nouveau modèle :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                Model model = new Model();
                model.setName(name);
                try {
                    model.setBrand(BrandController.getController().getBrandFromName(showComboInputDialog(BrandController.getController().selectBrandToArrayString(), "Ajouter un modèle", "Nouveau modèle", "Veuillez saisir la marque du nouveau modèle :")));
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
                ModelController modelController = new ModelController();
                try {
                    modelController.getController().addModel(model);
                    ihmWindowBox.showInformation("Le modèle à été créé avec succès.");
                    LoadAll();
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ihmWindowBox.showException("Le nom du modèle ne peut pas être vide.");
            }
        });
    }

    public String showComboInputDialog(ArrayList<String> choices, String title, String header, String body) {

        ChoiceDialog<String> dialog = new ChoiceDialog<>("", choices);

        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(body);

        Optional<String> result = dialog.showAndWait();

        return result.orElse(null);
    }

    @FXML
    private void ModifyModel(ActionEvent event) throws DalException {
        Model model = ModelController.getController().getModelFromName(comboModel.getSelectionModel().getSelectedItem());

        if (model == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un modèle à modifier.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Modifier un modèle");
        dialog.setHeaderText("Modifier le modèle");
        dialog.setContentText("Veuillez saisir le nouveau nom du modèle :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                model.setName(name);
                try {
                    model.setBrand(BrandController.getController().getBrandFromName(showComboInputDialog(BrandController.getController().selectBrandToArrayString(), "Modifier un modèle", "Modifier modèle", "Veuillez saisir la marque du modèle :")));
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }

                ModelController modelController = new ModelController();
                try {
                    modelController.updateModel(model);
                    ihmWindowBox.showInformation("Le modèle à été modifié avec succès.");
                    LoadAll();
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ihmWindowBox.showException("Le nom du modèle ne peut pas être vide.");
            }
        });
    }

    @FXML
    private void DeleteModel(ActionEvent event) throws DalException {
        Model model = ModelController.getController().getModelFromName(comboModel.getSelectionModel().getSelectedItem());

        if (model == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un modèle à supprimer.");
            return;
        }

        if (ModelController.getController().modelAttributed(model)) {
            ihmWindowBox.showInformation("Ce modèle ne peux pas être supprimé car il est affecté à un véhicule.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression de du modèle : " + model.toString());
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce modèle ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ModelController.getController().deleteModel(model.getId());
                ihmWindowBox.showInformation("Le modèle a été supprimé avec succès.");
                LoadAll();
            } catch (Exception e) {
                ihmWindowBox.showException(e.getMessage());
            }
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
                    LoadAll();
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ihmWindowBox.showException("Le nom du service ne peut pas être vide.");
            }
        });
    }

    @FXML
    private void ModifyService(ActionEvent event) throws DalException {

        Service service = ServiceController.getController().getServiceFromName(comboService.getSelectionModel().getSelectedItem());

        if (service == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un service à renommer.");
            return;
        }

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Renommer un service");
        dialog.setHeaderText("Renommer le service");
        dialog.setContentText("Veuillez saisir le nouveau nom du service :");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            if (!name.trim().isEmpty()) {
                service.setName(name);
                ServiceController serviceController = new ServiceController();
                try {
                    serviceController.updateService(service);
                    ihmWindowBox.showInformation("Le service à été renommé avec succès.");
                    LoadAll();
                } catch (DalException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ihmWindowBox.showException("Le nom du service ne peut pas être vide.");
            }
        });
    }

    @FXML
    private void DeleteService(ActionEvent event) throws DalException {
        Service service = ServiceController.getController().getServiceFromName(comboService.getSelectionModel().getSelectedItem());

        if (service == null) {
            ihmWindowBox.showInformation("Veuillez sélectionner un véhicule à supprimer.");
            return;
        }

        if (ServiceController.getController().serviceAttributed(service)) {
            ihmWindowBox.showInformation("Ce service ne peux pas être supprimé car il est affecté à un employé.");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Suppression du service : " + service.toString());
        alert.setContentText("Êtes-vous sûr de vouloir supprimer ce service ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ServiceController.getController().deleteService(service.getId());
                ihmWindowBox.showInformation("Le service a été supprimé avec succès.");
                LoadAll();
            } catch (Exception e) {
                ihmWindowBox.showException(e.getMessage());
            }
        }
    }

    private void LoadAll() {
        try {
            ClearValues();
            BrandController.getController().selectBrand().forEach(Brand -> {
                comboBrand.getItems().add(Brand.getName());
            });

            ModelController.getController().selectModel().forEach(Model -> {
                comboModel.getItems().add(Model.getName());
            });

            ServiceController.getController().selectService().forEach(Service -> {
                comboService.getItems().add(Service.getName());
            });
        } catch (DalException e) {
            ihmWindowBox.showException("Impossible de charger les données : " + e.getMessage());
        }
    }

    private void ClearValues() {
        comboBrand.getItems().clear();
        comboModel.getItems().clear();
        comboService.getItems().clear();
    }
}
