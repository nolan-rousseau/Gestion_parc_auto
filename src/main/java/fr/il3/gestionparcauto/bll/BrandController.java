package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Assignment;
import fr.il3.gestionparcauto.bo.Brand;
import fr.il3.gestionparcauto.bo.Model;
import fr.il3.gestionparcauto.bo.Service;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public ArrayList<String> selectBrandToArrayString() throws DalException {
        ArrayList<String> brandsNames = new ArrayList<>();
        selectBrand().forEach(Brand -> {
            brandsNames.add(Brand.getName());
        });
        return brandsNames;
    }

    public boolean brandAttributed(Brand brand) throws DalException {
        AtomicBoolean result = new AtomicBoolean(false);
        ModelController.getController().selectModel().forEach(model -> {
            if (model.getBrand().getId() == brand.getId()) {
                result.set(true);
            }
        });
        return result.get();
    }
}
