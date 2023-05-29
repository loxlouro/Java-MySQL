package com.example.fxmultithreadserver.controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.fxmultithreadserver.HelloApplication;
import com.example.fxmultithreadserver.dbhandling.DBConnection;
import com.example.fxmultithreadserver.system.MultiThreadServer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class AdminMenuController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button OpenUserRedactorButton;
    @FXML
    private Button OpenTableRedactorButton;
    @FXML
    private Button OpenWorkersRedactorButton;
    @FXML
    void initialize() {
        OpenUserRedactorButton.setOnAction(actionEvent -> {
            onUserRedactorAction();
        });
        OpenTableRedactorButton.setOnAction(actionEvent -> {

        });
        OpenWorkersRedactorButton.setOnAction(actionEvent -> {
            onOpenWorkerRedactorButtonAction();
        });

    }
    public void onUserRedactorAction(){
        FXMLLoader loader=new FXMLLoader(HelloApplication.class.getResource("user-list-redactor.fxml"));
        MultiThreadServer.getActiveStage().close();
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("User redactor");
            stage.setScene(scene);

            stage.show();

            MultiThreadServer.setActiveStage(stage);


        } catch (Exception e){
            e.printStackTrace();
        }

    }
    public void onOpenWorkerRedactorButtonAction(){
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("worker-list-redactor.fxml"));
        MultiThreadServer.getActiveStage().close();
        try {
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Worker redactor");
            stage.setScene(scene);

            stage.show();

            MultiThreadServer.setActiveStage(stage);


        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
