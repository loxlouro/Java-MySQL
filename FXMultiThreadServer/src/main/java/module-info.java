module com.example.fxmultithreadserver {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.fxmultithreadserver to javafx.fxml, javafx.base;
    opens com.example.fxmultithreadserver.controllers to javafx.fxml, javafx.base;
    exports com.example.fxmultithreadserver;
}