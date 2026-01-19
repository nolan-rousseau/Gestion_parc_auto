package fr.il3.gestionparcauto.bo;

public class Model {
    private int id;
    private int brandId;
    private String name;

    public Model(){};

    public Model(int id, String name, int brandId) {
        this.id = id;
        this.name = name;
        this.brandId = brandId;
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

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setId(int id) {
        this.id = id;
    }
}
