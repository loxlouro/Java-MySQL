package com.example.fxmultithreadserver;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.fxmultithreadserver.system.MultiThreadServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField ServerPassField;

    @FXML
    private Button StartDBButton;

    @FXML
    void initialize() {

        StartDBButton.setOnAction(event -> onActionStartDB());
    }

    public void onActionStartDB(){
        MultiThreadServer.getActiveStage().close();
        MultiThreadServer.adminMenu();

        HelloApplication.getHostThread().start();
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("admin-menu.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Admin menu");
            stage.setScene(scene);
            stage.show();
            MultiThreadServer.setActiveStage(stage);
            MultiThreadServer.setAdminMenuStage(stage);
        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalStateException e){
            e.printStackTrace();
        }




    }
}
