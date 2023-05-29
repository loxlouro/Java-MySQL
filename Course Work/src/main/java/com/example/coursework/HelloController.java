package com.example.coursework;

import com.example.coursework.dbhandling.DBConfig;
import com.example.coursework.dbhandling.DBConst;
import com.example.coursework.system.Processing;
import com.example.coursework.system.TempVar;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HelloController {
    private Processing mainProcess;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField DBadressTextField;

    @FXML
    private Text connectStatusText;

    @FXML
    private Button connectToDBButton;

    @FXML
    void initialize() {
        connectToDBButton.setOnAction(event -> onActionConnectToDB());
    }

    public void onActionConnectToDB(){
        this.mainProcess= TempVar.mainProcess;
        mainProcess.toProcess(DBadressTextField.getText(), 140);
        DBConfig consts = new DBConfig();
        consts.setDBAdress(DBadressTextField.getText());
        Processing.setConsts(consts);
        mainProcess.getActiveStage().close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("AuthorizeView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 500, 400);
            Stage stage= new Stage();
            stage.setTitle("Authorize");
            stage.setScene(scene);
            stage.show();
            mainProcess.setActiveStage(stage);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
