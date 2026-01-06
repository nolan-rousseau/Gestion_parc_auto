package fr.il3.gestionparcauto.ihm.console;

import fr.il3.gestionparcauto.bll.ParcController;
import fr.il3.gestionparcauto.utils.ParcException;

public class Controle {

    private ParcController controller;
    public Controle(){};

    public static void main(String[] args) throws ParcException {
        launch();
    }

    public static void launch() throws ParcException {
        Affichage affichage = new Affichage();
        affichage.afficherAccueil();
        while(true){
            affichage.afficherMenu();
            int choix = affichage.saisirChoix();
            switch (choix){
                case 1:
                    try {
                        affichage.afficherListeFilms(ParcController.getController().selectFilm());
                    } catch (ParcException e) {
                        affichage.afficherException(e.getMessage());
                    }finally {
                        break;
                    }
                case 2:
                    try {
                        ParcController.getController().addFilm(affichage.ajouterFilm());
                    } catch (ParcException e) {
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
