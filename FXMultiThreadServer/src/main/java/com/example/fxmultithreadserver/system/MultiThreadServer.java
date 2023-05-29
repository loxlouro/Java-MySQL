package com.example.fxmultithreadserver.system;

import com.example.fxmultithreadserver.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadServer implements Runnable{
    private static Stage additionalStage=null;
    private static Stage activeStage;
    private static Stage adminMenuStage;
    private static FXMLLoader mainLoader;
    static ExecutorService executeIt = Executors.newFixedThreadPool(10);
    public static void startServer() {
        try (
                ServerSocket serSocket = new ServerSocket(140)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Server socket created" + "\n");

            while (!serSocket.isClosed()) {
                if (br.ready()) {
                    System.out.println("Server found messages in channel");
                    String serverCommand = br.readLine();
                    if (serverCommand.equalsIgnoreCase("quit")) {
                        System.out.println("Server initiate exiting");
                        serSocket.close();
                        break;
                    }
                }
                Socket client = serSocket.accept();

                MonoThreadClientHandler clientDialog = new MonoThreadClientHandler(client);
                executeIt.execute(clientDialog);
                System.out.println("Connection accepted");

                //clientDialog.closeChannel();

            }
            executeIt.shutdown();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public static void adminMenu(){

    }

    public static Stage getActiveStage() {
        return activeStage;
    }

    public static void setActiveStage(Stage activeStage) {
        MultiThreadServer.activeStage = activeStage;
    }

    public static Stage getAdminMenuStage() {
        return adminMenuStage;
    }

    public static void setAdminMenuStage(Stage adminMenuStage) {
        MultiThreadServer.adminMenuStage = adminMenuStage;
    }

    public static FXMLLoader getMainLoader() {
        return mainLoader;
    }

    public static void setMainLoader(FXMLLoader mainLoader) {
        MultiThreadServer.mainLoader = mainLoader;
    }

    public static Stage getAdditionalStage() {
        return additionalStage;
    }

    public static void setAdditionalStage(Stage additionalStage) {
        MultiThreadServer.additionalStage = additionalStage;
    }

    @Override
    public void run() {
        startServer();
    }
}
