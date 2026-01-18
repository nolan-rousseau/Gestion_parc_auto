package fr.il3.gestionparcauto.bll;

import fr.il3.gestionparcauto.bo.Brand;
import fr.il3.gestionparcauto.dal.DAOFactory;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

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
        if(brand.getName()==null){
            throw new DalException("Le nouveau nom de la marque ne peut pas être vide.");
        }
        DAOFactory daoFactory = new DAOFactory();
        daoFactory.getBrandDAO().addBrand(brand);
    }

    public void updateBrand(Brand brand) throws DalException {
        if (brand.getId() <= 0) {
            throw new DalException("Impossible de modifier une marque sans un identifiant valide.");
        }
        if (brand.getName() == null || brand.getName().isEmpty()) {
            throw new DalException("Le nouveau nom de la marque ne peut pas être vide.");
        }

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
}
