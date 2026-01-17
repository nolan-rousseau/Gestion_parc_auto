package fr.il3.gestionparcauto.bo;

import java.time.LocalDate;

public class Vehicle {
    private int id;
    private String registration;
    private String mileage;
    private LocalDate registrationDate;
    private String comment;
    private int modelId;

    public Vehicle(String registration, String mileage, LocalDate registrationDate, String comment, int modelId) {
        this.registration = registration;
        this.mileage = mileage;
        this.registrationDate = registrationDate;
        this.comment = comment;
        this.modelId = modelId;
    };

    public Vehicle(int id, String registration, String mileage, LocalDate registrationDate, String comment, int modelId) {
        this.id = id;
        this.registration = registration;
        this.mileage = mileage;
        this.registrationDate = registrationDate;
        this.comment = comment;
        this.modelId = modelId;
    }

    @Override
    public String toString() {
        return id + ", " + registration + ", " + mileage + ", " + registrationDate + ", " + comment + ", " + modelId;
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

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

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

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }
}
