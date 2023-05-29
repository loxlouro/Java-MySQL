package com.example.fxmultithreadserver.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AddUserController implements Initializable {

    @FXML
    private Button applyButton;

    @FXML
    private Button discardButton;

    @FXML
    private TextField worker_group_field;

    @FXML
    private Text worker_id_text;

    @FXML
    private TextField worker_password_field;

    @FXML
    private TextField worker_password_field1;

    @FXML
    private ListView<Integer> workers_listview;
    ObservableList<Integer> workersList = FXCollections.observableArrayList(UserListController.getNonUserCount());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        workers_listview.setItems(workersList);
    }
    @FXML
    void onApplyButtonAction(ActionEvent event){

    }
    @FXML
    void onDiscardButtonAction(ActionEvent event){

    }

}
