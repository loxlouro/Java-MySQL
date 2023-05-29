package com.example.coursework;

import com.example.coursework.system.Processing;
import com.example.coursework.system.TempVar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 400);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

        Processing mainStream = new Processing(stage);
        TempVar.mainProcess=mainStream;


    }

    public static void main(String[] args) {

        launch();
    }
}