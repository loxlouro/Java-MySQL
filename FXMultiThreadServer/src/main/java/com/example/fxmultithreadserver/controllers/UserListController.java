package com.example.fxmultithreadserver.controllers;

import com.example.fxmultithreadserver.HelloApplication;
import com.example.fxmultithreadserver.dbhandling.DBConnection;
import com.example.fxmultithreadserver.dbhandling.DBConst;
import com.example.fxmultithreadserver.system.MultiThreadServer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserListController implements Initializable {
        private static User selected;
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button AddUserButton;

        @FXML
        private Button ApplyChangesButton;

        @FXML
        private Button DeleteUserButton;

        @FXML
        private Button DiscardChangesButton;

        @FXML
        private Button EditUserButton;

        @FXML
        private TableView<User> UserTableView;

        @FXML
        private TableColumn<User, String> worker_firstname_column;

        @FXML
        private TableColumn<User, String> worker_group_column;

        @FXML
        private TableColumn<User, Integer> worker_id_column;

        @FXML
        private TableColumn<User, String> worker_post_column;

        @FXML
        private TableColumn<User, String> worker_secondname_column;

        static ObservableList<User> users = FXCollections.observableArrayList(
                getUserList(getUserData(new DBConnection()))
        );
        TableView.TableViewSelectionModel<User> userSelectionModel;
        public static ResultSet getUserData(DBConnection userlistconnection){
                ResultSet resSet = null;

                String select = "select "+ DBConst.TABLE_WORKERS + "."+DBConst.FIELD_WORKER_ID+", "+
                        DBConst.TABLE_WORKERS + "."+DBConst.FIELD_WORKER_FIRSTNAME+", "+
                        DBConst.TABLE_WORKERS + "."+DBConst.FIELD_WORKER_SECONDNAME+", "+
                        DBConst.TABLE_USERS + "."+DBConst.FIELD_USERS_GROUP+", "+
                        DBConst.TABLE_WORKERS + "."+DBConst.FIELD_WORKER_POST+
                        " from "+DBConst.TABLE_WORKERS+" inner join "+DBConst.TABLE_USERS +
                        " on "+ DBConst.TABLE_WORKERS+"."+DBConst.FIELD_WORKER_ID+" = "+
                        DBConst.TABLE_USERS+"."+ DBConst.FIELD_WORKER_ID;
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
        public static ArrayList<User> getUserList(ResultSet resultSet){
                ArrayList<User> userlist = new ArrayList<>();
                try {
                        while (resultSet.next()) {
                                userlist.add(new User(resultSet.getInt(DBConst.FIELD_WORKER_ID),
                                        resultSet.getString(DBConst.FIELD_WORKER_FIRSTNAME),
                                        resultSet.getString(DBConst.FIELD_WORKER_SECONDNAME),
                                        resultSet.getString(DBConst.FIELD_USERS_GROUP),
                                        resultSet.getString(DBConst.FIELD_WORKER_POST)));
                        }
                } catch (SQLException e){
                  e.printStackTrace();
                }
                return userlist;
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
                worker_id_column.setCellValueFactory(new PropertyValueFactory<User,Integer>("worker_id"));
                worker_firstname_column.setCellValueFactory(new PropertyValueFactory<User,String>("worker_firstname"));
                worker_secondname_column.setCellValueFactory(new PropertyValueFactory<User,String>("worker_secondname"));
                worker_group_column.setCellValueFactory(new PropertyValueFactory<User,String>("worker_group"));
                worker_post_column.setCellValueFactory(new PropertyValueFactory<User, String>("worker_post"));
                UserTableView.setItems(users);

                userSelectionModel=UserTableView.getSelectionModel();
                userSelectionModel.selectedItemProperty().addListener(new ChangeListener<User>() {
                        @Override
                        public void changed(ObservableValue<? extends User> observableValue, User user, User t1) {
                                if(t1!=null)selected=t1;
                        }
                });
        }
        public void onEditUserButtonAction(){
                try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("edit-user.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        stage.show();
                        MultiThreadServer.setAdditionalStage(stage);
                } catch (IOException e){
                        e.printStackTrace();
                }
        }
        public void onDeleteUserButtonAction(){
                try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("-user.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        stage.show();
                        MultiThreadServer.setAdditionalStage(stage);
                } catch (IOException e){
                        e.printStackTrace();
                }
        }
        public void onAddUserButtonAction(){
                try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("add-user.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        stage.show();
                        MultiThreadServer.setAdditionalStage(stage);
                } catch (IOException e){
                        e.printStackTrace();
                }
        }
        public void onApplyButtonAction() {
                try {
                        if (!MultiThreadServer.getAdditionalStage().isShowing()) {
                                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("admin-menu.fxml"));
                                Stage stage = new Stage();
                                Scene scene = new Scene(loader.load());
                                stage.setScene(scene);
                                stage.show();
                                MultiThreadServer.getActiveStage().close();
                                MultiThreadServer.setActiveStage(stage);
                                MultiThreadServer.getActiveStage().show();
                        }
                } catch (IOException e){

                }
        }

        public void onDiscardButtonAction() {
                try {
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("admin-menu.fxml"));
                        Stage stage = new Stage();
                        Scene scene = new Scene(loader.load());
                        stage.setScene(scene);
                        stage.show();
                        MultiThreadServer.getActiveStage().close();
                        MultiThreadServer.setActiveStage(stage);
                        MultiThreadServer.getActiveStage().show();
                }catch (IOException e){

                }
        }

        public static User getSelected() {
                return selected;
        }

        public void setSelected(User selected) {
                this.selected = selected;
        }
        public static ArrayList<Integer> getNonUserCount(){
                ArrayList<Integer> temp = new ArrayList<>();
                for (User i: WorkerListController.workers){
                        if (i.getWorker_group().equals("")) temp.add(i.getWorker_id());
                }
                return temp;
        }
}

