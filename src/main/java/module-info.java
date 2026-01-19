module fr.il3.gestionparcauto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires com.h2database;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;

    exports fr.il3.gestionparcauto.ihm.javafx;
    opens fr.il3.gestionparcauto.ihm.javafx to javafx.fxml;
    exports fr.il3.gestionparcauto.ihm.javafx.utils;
    opens fr.il3.gestionparcauto.ihm.javafx.utils to javafx.fxml;
}