package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Assignment;
import fr.il3.gestionparcauto.bo.Brand;
import fr.il3.gestionparcauto.bo.Model;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;
import java.util.Objects;

public class BrandController {
    private static BrandController brandController;

    public static BrandController getController(){
        if(brandController ==null){
            brandController = new BrandController();
        }
        return brandController;
    }

    public ArrayList<Brand> selectBrand() throws DalException {
        DAOFactory daoFactory = new DAOFactory();
        return daoFactory.getBrandDAO().selectAll();
    }

    public void addBrand(Brand brand) throws DalException {
        verifObjectAssignment(brand);
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getBrandDAO().addBrand(brand);
    }

    public void updateBrand(Brand brand) throws DalException {
        if (brand.getId() <= 0) {
            throw new DalException("Impossible de modifier une marque sans un identifiant valide.");
        }
        verifObjectAssignment(brand);

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getBrandDAO().updateBrand(brand);
    }

    public void deleteBrand(int id) throws DalException {
        if (id <= 0) {
            throw new DalException("L'identifiant de la marque à supprimer est invalide.");
        }

        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getBrandDAO().deleteBrand(id);
    }

    private void verifObjectAssignment(Brand brand) throws DalException {
        if(brand.getName().isEmpty()){
            throw new DalException("Le nom de la marque ne peut pas être vide.");
        }
    }

    public Brand getBrandFromName(String name) throws DalException {
        Brand specificBrand = selectBrand().stream()
                .filter(b -> {
                    try {
                        return Objects.equals(b.getName(), name);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .findFirst()
                .orElse(null);
        return specificBrand;
    }
}
