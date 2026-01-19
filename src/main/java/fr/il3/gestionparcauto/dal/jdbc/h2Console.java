package fr.il3.gestionparcauto.dal.jdbc;

import org.h2.tools.Server;

public class h2Console {
    public static void main() {
        try {
            Server server = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
            System.out.println("Console H2 démarrée : http://localhost:8082");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
