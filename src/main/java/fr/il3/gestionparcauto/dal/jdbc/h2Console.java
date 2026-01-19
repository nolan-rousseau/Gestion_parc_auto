package fr.il3.gestionparcauto.dal.jdbc;

import org.h2.tools.Server;

public class h2Console {
    private static Server webServer;

    public static void start() {
        try {
            if (webServer == null) {
                webServer = Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082").start();
                System.out.println("Console H2 démarrée : http://localhost:8082");
            }
        } catch (Exception e) {
            System.err.println("Erreur au démarrage de la console H2 : " + e.getMessage());
        }
    }

    public static void stop() {
        try {
            if (webServer != null) {
                webServer.stop();
                webServer = null;
                System.out.println("Console H2 arrêtée");
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'arrêt de la console H2 : " + e.getMessage());
        }
    }
}