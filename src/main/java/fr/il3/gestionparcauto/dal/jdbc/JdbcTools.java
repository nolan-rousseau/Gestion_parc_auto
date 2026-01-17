package fr.il3.gestionparcauto.dal.jdbc;

import java.io.IOException;
import java.util.Properties;

public class JdbcTools {

    private static Properties p;

    static {
        p = new Properties();
        try {
            p.load(JdbcTools.class.getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return p.getProperty(key);
    }
}