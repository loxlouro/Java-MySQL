module com.example.coursework {
    //requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    //requires org.controlsfx.controls;
    //requires com.dlsc.formsfx;
    //requires org.kordamp.ikonli.javafx;
    //requires eu.hansolo.tilesfx;

    opens com.example.coursework to javafx.fxml;
    exports com.example.coursework;
}