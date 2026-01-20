package fr.il3.gestionparcauto.bo;

import java.time.LocalDate;

public class Vehicle {
    private int id;
    private String registration;
    private Long mileage;
    private LocalDate lastUpdateMileage;
    private LocalDate registrationDate;
    private String comment;
    private Model model;

    public Vehicle() {

    }

    public Vehicle(int id, String registration, Long mileage, LocalDate lastUpdateMileage, LocalDate registrationDate, String comment, Model model) {
        this.id = id;
        this.registration = registration;
        this.mileage = mileage;
        this.lastUpdateMileage = lastUpdateMileage;
        this.registrationDate = registrationDate;
        this.comment = comment;
        this.model = model;
    }

    @Override
    public String toString() {
        return model.getBrand().toString() + " " + model + " (" + registration + ")";
    }

    public int getId() {
        return id;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public LocalDate getLastUpdateMileage() { return lastUpdateMileage; }

    public void setLastUpdateMileage(LocalDate lastUpdateMileage) { this.lastUpdateMileage = lastUpdateMileage; }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        String info = "Date d'immatriculation : " + registrationDate.toString() +
                "\nKilométrage : " + mileage.toString();

        if (comment != null && !comment.trim().isEmpty()) {
            info += "\nRemarques : " + comment;
        }

        info += "\n\nDernière mise à jour : " + lastUpdateMileage.toString();

        return info;
    }
}
