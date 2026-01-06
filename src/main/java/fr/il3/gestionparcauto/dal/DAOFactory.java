package fr.il3.gestionParcAuto.dal;

import fr.il3.gestionParcAuto.dal.jdbc.FilmDAOJdbcImpl;

public class DAOFactory {

    public DAOFactory() {    }

        public FilmDAOJdbcImpl getFilmDAO(){
        return new FilmDAOJdbcImpl();
    }
}
