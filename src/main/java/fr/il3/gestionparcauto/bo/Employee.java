package fr.il3.gestionparcauto.bo;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private int serviceId;

    public Employee(String firstName, String lastName, String email, String phone, int serviceId){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.serviceId = serviceId;
    };

    public Employee(int id, String firstName, String lastName, String email, String phone, int serviceId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
        return id + ", " + firstName + ", " + lastName + ", " + email + ", " + phone + ", " + serviceId;
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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
