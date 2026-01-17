package fr.il3.gestionparcauto.bo;

public class Brand {
    private int id;
    private String name;

    public Brand(){};

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return id + ", " + name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
