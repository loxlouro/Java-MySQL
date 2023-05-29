package com.example.fxmultithreadserver.controllers;

import com.example.fxmultithreadserver.HelloApplication;
import com.example.fxmultithreadserver.dbhandling.DBConnection;
import com.example.fxmultithreadserver.dbhandling.DBConst;
import com.example.fxmultithreadserver.system.MultiThreadServer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkerListController implements Initializable {
    private static User selected;
    @FXML
    private Button AddWorkerButton;

    @FXML
    private Button ApplyChangesButton;

    @FXML
    private Button DeleteWorkerButton;

    @FXML
    private Button DiscardChangesButton;

    @FXML
    private Button EditWorkerButton;

    @FXML
    private TableView<User> WorkerTableView;

    @FXML
    private TableColumn<User, String> worker_firstname_column;

    @FXML
    private TableColumn<User, Integer> worker_id_column;

    @FXML
    private TableColumn<User, String> worker_post_column;

    @FXML
    private TableColumn<User, String> worker_secondname_column;

    static ObservableList<User> workers = FXCollections.observableArrayList(
            getUserList(getUserData(new DBConnection()))
    );
    public static int workersNum(){
        return workers.size();
    }
    TableView.TableViewSelectionModel<User> userSelectionModel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        worker_id_column.setCellValueFactory(new PropertyValueFactory<User,Integer>("worker_id"));
        worker_firstname_column.setCellValueFactory(new PropertyValueFactory<User,String>("worker_firstname"));
        worker_secondname_column.setCellValueFactory(new PropertyValueFactory<User,String>("worker_secondname"));
        worker_post_column.setCellValueFactory(new PropertyValueFactory<User, String>("worker_post"));
        WorkerTableView.setItems(workers);

        EditWorkerButton.setOnAction(actionEvent -> {
            onEditWorkerButtonAction(actionEvent);
        });
        DeleteWorkerButton.setOnAction(event -> {
            onDeleteWorkerButtonAction(event);
        });
        AddWorkerButton.setOnAction(action -> {
            onAddWorkerButtonAction(action);
        });

        userSelectionModel=WorkerTableView.getSelectionModel();
        userSelectionModel.selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observableValue, User user, User t1) {
                if(t1!=null)selected=t1;
            }
        });
    }
    private static ResultSet getUserData(DBConnection userlistconnection){
        ResultSet resSet = null;

        String select = "select "+DBConst.FIELD_WORKER_ID+", "
                +DBConst.FIELD_WORKER_FIRSTNAME+", "
                +DBConst.FIELD_WORKER_SECONDNAME+", "
                +DBConst.FIELD_WORKER_POST+
                " from "+DBConst.TABLE_WORKERS;
        try{
            PreparedStatement PrSt = userlistconnection.getDbConnection().prepareStatement(select);

            resSet = PrSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }
    private static ArrayList<User> getUserList(ResultSet resultSet){
        ArrayList<User> userlist = new ArrayList<>();
        try {
            while (resultSet.next()) {
                userlist.add(new User(resultSet.getInt(DBConst.FIELD_WORKER_ID),
                        resultSet.getString(DBConst.FIELD_WORKER_FIRSTNAME),
                        resultSet.getString(DBConst.FIELD_WORKER_SECONDNAME),
                        "",
                        resultSet.getString(DBConst.FIELD_WORKER_POST)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userlist;
    }

    @FXML
    void onAddWorkerButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add-worker.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
            MultiThreadServer.setActiveStage(stage);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void onApplyButtonAction(ActionEvent event) {

    }

    @FXML
    void onDeleteWorkerButtonAction(ActionEvent event) {

    }

    @FXML
    void onDiscardButtonAction(ActionEvent event) {

    }

    @FXML
    void onEditWorkerButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("edit-worker.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
            MultiThreadServer.setAdditionalStage(stage);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static User getSelected() {
        return selected;
    }

    public void setSelected(User selected) {
        WorkerListController.selected = selected;
    }
}
