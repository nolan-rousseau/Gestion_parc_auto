package fr.il3.gestionparcauto.dal.jdbc;

import java.io.IOException;
import java.util.Properties;

public class JdbcTools {

    private static Properties p;

    static {
        p = new Properties();
        try (var is = JdbcTools.class.getResourceAsStream("/database/settings.properties")) {
            if (is == null) {
                throw new IOException("Fichier settings.properties introuvable.");
            }
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return p.getProperty(key);
    }
}