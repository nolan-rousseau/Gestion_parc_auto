package fr.il3.gestionParcAuto.ihm.console;

import fr.il3.gestionParcAuto.bo.Film;

import java.util.ArrayList;
import java.util.Scanner;

public class Affichage {

    public final int SELECT = 1;
    public final int ADD = 2;
    public final int EXIT = 3;
    private Scanner scan = new Scanner(System.in);

    public Affichage() {}

    public void afficherAccueil(){
        System.out.println("***********************************\n" +
                "***** APPLICATION FILMOTHEQUE *****\n" +
                "***********************************");
    }

    public void afficherListeFilms(ArrayList<Film> films){
        System.out.println("*** Liste des films disponibles ***");
        for(Film film : films){
            System.out.println("    - " + film);
        }
    }

    public int saisirChoix(){
        return scan.nextInt();
    }

    public void afficherException(String string){
        System.err.println("/!\\ " + string);
    }

    public void afficherAuRevoir(){
        System.out.println("***** A bientôt !!! *****");
    }

    public Film ajouterFilm(){
        System.out.println("*** Ajouter un film ***");
        System.out.println("Saisir le titre :");
        String titre = scan.next();
        scan.nextLine();
        System.out.println("Saisir  la durée :");
        int duree = scan.nextInt();
        scan.nextLine();
        System.out.println("Saisir l'annee :");
        int annee = scan.nextInt();
        scan.nextLine();
        System.out.println("Saisir le réalisateur :");
        String realisateur = scan.next();
        scan.nextLine();
        return new Film(titre, annee, realisateur, duree);
    }

    public void afficherMenu(){
        System.out.println("Tapez votre choix, parmi les propositions suivantes :\n" +
                SELECT + " - Afficher tous les films\n" +
                ADD + " - Ajouter un nouveau film\n" +
                EXIT + " - Quitter l'application");
    }
}
