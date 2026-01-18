package fr.il3.gestionparcauto.dal;

import fr.il3.gestionparcauto.bo.Model;
import fr.il3.gestionparcauto.utils.DalException;

import java.util.ArrayList;

public interface ModelDAO {
    public void addModel(Model brand) throws DalException;
    public ArrayList<Model> selectAll() throws DalException;
    public void deleteModel(int id) throws DalException;
    public void updateModel(Model brand) throws DalException;
}
