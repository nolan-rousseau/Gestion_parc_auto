module fr.il3.gestionparcauto {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens fr.il3.gestionparcauto to javafx.fxml;
    exports fr.il3.gestionparcauto;
}