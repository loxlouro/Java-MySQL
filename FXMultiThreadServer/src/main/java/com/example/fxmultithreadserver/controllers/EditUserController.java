package com.example.fxmultithreadserver.controllers;

import com.example.fxmultithreadserver.system.MultiThreadServer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {
    @FXML
    private Button ApplyChangesButton;

    @FXML
    private Button DiscardChangesButton;

    @FXML
    private TextField password_textfield;

    @FXML
    private TextField password_textfield1;

    @FXML
    private Text workerAtributesText;

    @FXML
    private TextField worker_group_textfield;

    User target = UserListController.getSelected();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.worker_group_textfield.setText(target.getWorker_group());
        this.workerAtributesText.setText(target.workerAtributes());
        try {
            this.password_textfield.setText(target.getPassword());
            this.password_textfield1.setText(target.getPassword());
        } catch (NullPointerException e){
            this.password_textfield1.setText("No pass!");
            this.password_textfield.setText("No pass!");
        }

    }
    @FXML
    void onApplyButtonAction(){
        if (confirmPassword(this.password_textfield.getText(),this.password_textfield1.getText())){
            target.setWorker_group(this.worker_group_textfield.getText());
            target.setPassword(this.password_textfield.getText());
        }
        //to do
    }
    @FXML
    void onDiscardButtonAction(){
        MultiThreadServer.getAdditionalStage().close();
    }
    private boolean confirmPassword(String pass, String confirm){
        return pass.equals(confirm);
    }
}
