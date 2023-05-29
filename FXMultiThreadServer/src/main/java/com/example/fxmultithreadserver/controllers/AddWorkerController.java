package com.example.fxmultithreadserver.controllers;

import com.example.fxmultithreadserver.dbhandling.DBConnection;
import com.example.fxmultithreadserver.dbhandling.DBConst;
import com.example.fxmultithreadserver.system.MultiThreadServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddWorkerController implements Initializable {

    @FXML
    private Button applyButton;

    @FXML
    private Button discardButton;

    @FXML
    private TextField worker_firstname_field;

    @FXML
    private Text worker_id_text;

    @FXML
    private TextField worker_post_field;

    @FXML
    private TextField worker_secondname_field;

    private User target;
    @FXML
    void onApplyButtonAction(ActionEvent event) {
        DBConnection connection = new DBConnection();

        target.setWorker_firstname(worker_firstname_field.getText());
        target.setWorker_secondname(worker_secondname_field.getText());
        target.setWorker_post(worker_post_field.getText());
        addWorker(connection);
        MultiThreadServer.getAdditionalStage().close();
    }

    @FXML
    void onDiscardButtonAction(ActionEvent event) {
        MultiThreadServer.getAdditionalStage().close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        target=new User(WorkerListController.workersNum()+1);
        this.worker_id_text.setText("Worker id: "+target.getWorker_id().toString());
        this.worker_firstname_field.setText(target.getWorker_firstname());
        this.worker_secondname_field.setText(target.getWorker_secondname());
        this.worker_post_field.setText(target.getWorker_post());

        applyButton.setOnAction(event->{
            onApplyButtonAction(event);
        });
        discardButton.setOnAction(actionEvent -> {
            onDiscardButtonAction(actionEvent);
        });
    }
    private void addWorker(DBConnection userlistconnection){

        String insert = "insert into "+ DBConst.TABLE_WORKERS+" ("
                +DBConst.FIELD_WORKER_FIRSTNAME+", "
                +DBConst.FIELD_WORKER_SECONDNAME+", "
                +DBConst.FIELD_WORKER_POST+") values ('"+
                this.target.getWorker_firstname()+"', '"+
                this.target.getWorker_secondname()+"', '"+
                this.target.getWorker_post()+"');";
        try{
            PreparedStatement PrSt = userlistconnection.getDbConnection().prepareStatement(insert);

            PrSt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
