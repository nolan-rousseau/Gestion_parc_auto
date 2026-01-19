package fr.il3.gestionparcauto.bo;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Service service;

    public Employee() {

    }

    public Employee(int id, String firstName, String lastName, String email, String phone, Service service) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.service = service;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + service + ")";
    }

    public String getInformations(){
        return "e-mail : " + email + "\ntél : " + phone;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setId(int id) {
        this.id = id;
    }
}
