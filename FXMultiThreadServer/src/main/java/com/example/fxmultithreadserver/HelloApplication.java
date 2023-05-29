package com.example.fxmultithreadserver;

import com.example.fxmultithreadserver.system.MultiThreadServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    private static Thread hostThread;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Start Server");
        stage.setScene(scene);
        stage.show();
        MultiThreadServer.setActiveStage(stage);
        MultiThreadServer.setMainLoader(fxmlLoader);
    }

    public static void main(String[] args) {
        MultiThreadServer thread = new MultiThreadServer();
        HelloApplication.hostThread=new Thread(thread);
        launch();

        }

    public static Thread getHostThread() {
        return hostThread;
    }

    public static void setHostThread(Thread hostThread) {
        HelloApplication.hostThread = hostThread;
    }
}

