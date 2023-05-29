package com.example.fxmultithreadserver.dbhandling;

import com.example.fxmultithreadserver.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TempRun {


}
//    public static void main(String[] args){
//        ResultSet resSet = null;
//
//        String select = "SELECT * FROM "+ DBConst.TABLE_WORKERS;
//
//        DBConnection connection=new DBConnection();
//        try{
//            PreparedStatement PrSt = connection.getDbConnection().prepareStatement(select);
//
//            resSet = PrSt.executeQuery();
//            while (resSet.next()) {
//                System.out.println(resSet.getString(DBConst.FIELD_WORKER_FIRSTNAME));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
//    }
//}
