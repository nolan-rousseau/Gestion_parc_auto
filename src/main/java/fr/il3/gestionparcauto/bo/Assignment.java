package fr.il3.gestionparcauto.bo;

import java.time.LocalDate;

public class Assignment {
    private int id;
    private int vehicleId;
    private int employeeId;
    private String comment;
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public Assignment(){};

    public Assignment(int id, int vehicleId, int employeeId, String comment, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.employeeId = employeeId;
        this.comment = comment;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    @Override
    public String toString() {
        return id + ", " + vehicleId + ", " + employeeId + ", " + comment + ", " + dateStart + ", " + dateEnd;
    }

    public int getId() {
        return id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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
}
