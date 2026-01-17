package fr.il3.gestionparcauto.ihm.console;

import fr.il3.gestionparcauto.bll.ParcController;
import fr.il3.gestionparcauto.utils.DalException;

public class Controle {

    private ParcController controller;
    public Controle(){};

    public static void main(String[] args) throws DalException {
        launch();
    }

    public static void launch() throws DalException {
        Affichage affichage = new Affichage();
        affichage.afficherAccueil();
        while(true){
            affichage.afficherMenu();
            int choix = affichage.saisirChoix();
            switch (choix){
                case 1:
                    try {
                        affichage.afficherListeFilms(ParcController.getController().selectFilm());
                    } catch (DalException e) {
                        affichage.afficherException(e.getMessage());
                    }finally {
                        break;
                    }
                case 2:
                    try {
                        ParcController.getController().addFilm(affichage.ajouterFilm());
                    } catch (DalException e) {
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
