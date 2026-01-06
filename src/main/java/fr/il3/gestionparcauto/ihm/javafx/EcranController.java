package fr.il3.gestionParcAuto.ihm.javafx;

import fr.il3.gestionParcAuto.bll.FilmController;
import fr.il3.gestionParcAuto.bo.Film;
import fr.il3.gestionParcAuto.utils.FilmException;
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

    public Film getFilm(int index) {
        try {
            return FilmController.getController().selectFilm().get(index);
        } catch (FilmException e) {
            afficherException(e.getMessage());
            return null;
        }
    }

    @FXML
    public void filmNext(javafx.event.ActionEvent event) {
        indexFilm++;
        afficherFilm(getFilm(indexFilm));
    }

    public void afficherFilm(Film film) {
        if (film != null) {
            tb_filmTitle.setText(film.getTitre());
            tb_filmDuree.setText(Integer.toString(film.getDuree()));
            tb_filmRealisateur.setText(film.getRealisateur());
            tb_filmAnnee.setText(Integer.toString(film.getAnnee()));
        } else  {
            afficherException("Le film n'existe pas");
        }
    }

    private void afficherException(String txt) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Une erreur est survenue");
        alert.setContentText(txt);
        alert.showAndWait();
    }

}
