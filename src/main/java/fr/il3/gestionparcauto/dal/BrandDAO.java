package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Brand;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public interface BrandDAO {
    public void addBrand(Brand brand) throws DalException;
    public ArrayList<Brand> selectAll() throws DalException;
    public void deleteBrand(int id) throws DalException;
    public void updateBrand(Brand brand) throws DalException;
}
