package fr.il3.gestionparcauto.dal.jdbc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOJdbcImpl {

    private static boolean databaseGenerated = false;

    private static final String URL = JdbcTools.getProperty("url"); // sans extension
    private static final String USER = JdbcTools.getProperty("username");
    private static final String PASSWORD = ""; //JdbcTools.getProperty("password");

    private static void main() {
        try {
            Files.deleteIfExists(Paths.get("./src/main/resources/database/mydb.mv.db"));

            Connection conn = getConnection();
            Statement stmt = conn.createStatement();

            String sqlInit = new String(Files.readAllBytes(Paths.get("./src/main/resources/database/1_init.sql")));
            String[] queriesInit = sqlInit.split(";");
            for (String q : queriesInit) {
                if (!q.trim().isEmpty()) {
                    stmt.execute(q);
                }
            }
            System.out.println("Base de données : Tables créées");

            String sqlData = new String(Files.readAllBytes(Paths.get("./src/main/resources/database/2_data.sql")));
            String[] queriesData = sqlData.split(";");
            for (String q : queriesData) {
                if (!q.trim().isEmpty()) {
                    stmt.execute(q);
                }
            }
            System.out.println("Base de données : Enregistrements créées");

//            ResultSet rs = stmt.executeQuery("SELECT * FROM Employees;");
//            System.out.println("Données dans la table Employees :");
//            while (rs.next()) {
//                System.out.println(rs.getInt("id") + " - " + rs.getString("firstname"));
//            }
            conn.close();
            h2Console.main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (!databaseGenerated){
            databaseGenerated = !databaseGenerated;
            main();
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
