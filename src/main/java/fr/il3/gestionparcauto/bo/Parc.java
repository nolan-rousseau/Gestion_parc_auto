package fr.il3.gestionparcauto.bo;

public class Parc {
    private String titre;
    private int annee;
    private String realisateur;
    private int duree;

    public Parc(){};

    public Parc(String titre, int annee, String realisateur, int duree) {
        this.titre = titre;
        this.annee = annee;
        this.duree = duree;
        this.realisateur = realisateur;
    }

    @Override
    public String toString() {
        return titre + ", " + realisateur + ", " + annee + ", (" + duree + " minutes)";
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
}
