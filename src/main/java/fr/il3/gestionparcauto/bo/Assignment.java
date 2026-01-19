package fr.il3.gestionparcauto.bo;

import java.time.LocalDate;

public class Assignment {
    private int id;
    private Vehicle vehicle;
    private Employee employee;
    private String comment;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public Assignment(){};

    public Assignment(int id, Vehicle vehicle, Employee employee, String comment, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.vehicle = vehicle;
        this.employee = employee;
        this.comment = comment;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return id + ", " + vehicle + ", " + employee + ", " + comment + ", " + dateStart + ", " + dateEnd;
    }

    public int getId() {
        return id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isActive() {
        if (dateStart != null && dateEnd != null) {
            LocalDate today = LocalDate.now();
            return (!today.isBefore(dateStart) && !today.isAfter(dateEnd));
        }
        return false;
    }

    public void setId(int id) {
        this.id = id;
    }
}
