module fr.il3.gestionparcauto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    exports fr.il3.gestionparcauto.ihm.javafx;
    opens fr.il3.gestionparcauto.ihm.javafx to javafx.fxml;
}