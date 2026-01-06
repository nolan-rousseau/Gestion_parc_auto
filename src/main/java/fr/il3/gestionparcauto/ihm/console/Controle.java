package fr.il3.gestionParcAuto.ihm.console;

import fr.il3.gestionParcAuto.bll.FilmController;
import fr.il3.gestionParcAuto.utils.FilmException;

public class Controle {

    private FilmController controller;
    public Controle(){};

    public static void main(String[] args) throws FilmException {
        launch();
    }

    public static void launch() throws FilmException {
        Affichage affichage = new Affichage();
        affichage.afficherAccueil();
        while(true){
            affichage.afficherMenu();
            int choix = affichage.saisirChoix();
            switch (choix){
                case 1:
                    try {
                        affichage.afficherListeFilms(FilmController.getController().selectFilm());
                    } catch (FilmException e) {
                        affichage.afficherException(e.getMessage());
                    }finally {
                        break;
                    }
                case 2:
                    try {
                        FilmController.getController().addFilm(affichage.ajouterFilm());
                    } catch (FilmException e) {
                        affichage.afficherException(e.getMessage());
                    }finally {
                        break;
                    }
                case 3:
                    affichage.afficherAuRevoir();
                    return;
            }
        }
    }
}
