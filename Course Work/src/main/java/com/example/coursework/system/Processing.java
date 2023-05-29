package com.example.coursework.system;

import com.example.coursework.dbhandling.DBConfig;
import com.example.coursework.tables.User;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Processing {
    private static User activeUser;
    private static DBConfig consts;
    private static Stage activeStage;
    private static Connector clientDialog;
    public Processing(Stage activeStage){
        this.activeStage=activeStage;
    }
    public Processing(){}
    public void toProcess(String ip,int port){
        try {
            Connector clientSocket = new Connector(new Socket(ip,port));

            clientDialog=clientSocket;

            clientSocket.closeAll();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Connector getClientDialog() {
        return clientDialog;
    }

    public static void setClientDialog(Connector client) {
        clientDialog = client;
    }

    public static Stage getActiveStage() {
        return activeStage;
    }

    public static void setActiveStage(Stage stage) {
        activeStage = stage;
    }

    public static void setConsts(DBConfig consts) {
        Processing.consts = consts;
    }

    public static User getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(User activeUser) {
        Processing.activeUser = activeUser;
    }
}
