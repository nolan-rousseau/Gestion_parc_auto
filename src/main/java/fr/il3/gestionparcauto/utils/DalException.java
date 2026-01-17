package fr.il3.gestionparcauto.utils;

public class DalException extends Exception{
    public DalException(String message) {
        super(message);
    }

    public DalException(String message, Throwable cause) {
        super(message, cause);
    }
}
