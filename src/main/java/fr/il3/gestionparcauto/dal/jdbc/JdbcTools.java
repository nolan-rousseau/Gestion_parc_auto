package fr.il3.gestionparcauto.dal.jdbc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTools {
    private static final String URL = "jdbc:h2:file:./src/main/resources/database/mydb"; // sans extension
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) {
        try {
            // Vérifier si la base existe
            if (!Files.exists(Paths.get("./src/main/resources/database/mydb.mv.db"))) {
                System.out.println("Base inexistante, création à partir de init.sql...");
                Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();

                // Lire le fichier init.sql
                String sql = new String(Files.readAllBytes(Paths.get("./src/main/resources/database/init.sql")));

                // Diviser les instructions SQL par ';'
                String[] queries = sql.split(";");
                for (String q : queries) {
                    if (!q.trim().isEmpty()) {
                        stmt.execute(q);
                    }
                }
                conn.close();
            }

            // Connexion et lecture test
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Personne;");

            System.out.println("Données dans la table Personne :");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("nom"));
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
