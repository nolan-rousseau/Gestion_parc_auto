package fr.il3.gestionparcauto.ihm.javafx;

import fr.il3.gestionparcauto.utils.DalException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EcranController {

    @FXML
    private TextField tb_filmTitle;
    @FXML
    private TextField tb_filmDuree;
    @FXML
    private TextField tb_filmAnnee;
    @FXML
    private TextField tb_filmRealisateur;

    @FXML
    private Button btt_filmPrevious;
    @FXML
    private Button btt_filmNext;

    int indexFilm = 0;

//    public Parc getFilm(int index) {
//        try {
//            return ParcController.getController().selectFilm().get(index);
//        } catch (DalException e) {
//            afficherException(e.getMessage());
//            return null;
//        }
//    }

//    @FXML
//    public void filmNext(javafx.event.ActionEvent event) {
//        indexFilm++;
//        afficherFilm(getFilm(indexFilm));
//    }

//    public void afficherFilm(Parc parc) {
//        if (parc != null) {
//            tb_filmTitle.setText(parc.getTitre());
//            tb_filmDuree.setText(Integer.toString(parc.getDuree()));
//            tb_filmRealisateur.setText(parc.getRealisateur());
//            tb_filmAnnee.setText(Integer.toString(parc.getAnnee()));
//        } else  {
//            afficherException("Le film n'existe pas");
//        }
//    }

    private void afficherException(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue");
        alert.setContentText(txt);
        alert.showAndWait();
    }

}
