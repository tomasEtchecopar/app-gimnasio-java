module com.gimnasio.appgimnasio {
    requires javafx.controls;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.desktop;

    opens com.gimnasio.appgimnasio to javafx.fxml;
    exports com.gimnasio.appgimnasio;
}