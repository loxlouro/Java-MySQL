package com.example.coursework;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.example.coursework.dbhandling.DBConnection;
import com.example.coursework.dbhandling.DBConst;
import com.example.coursework.system.Processing;
import com.example.coursework.tables.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AuthorizeController {

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button AuthorizeButton;

        @FXML
        private TextArea ConnectionStatusText;

        @FXML
        private Text LoginToDBText;
        @FXML
        private TextField loginTextField;
        @FXML
        private TextField PasswordTextField;

        @FXML
        void initialize() {

           LoginToDBText.setText(LoginToDBText.getText()+" "+
                   Processing.getClientDialog().getClientSocket().getInetAddress().toString());


           AuthorizeButton.setOnAction(event -> {
                   onAuthorizeButton(event);
           });

        }

        @FXML
        void onAuthorizeButton(ActionEvent event){
                String loginText = loginTextField.getText().trim();
                String passText = PasswordTextField.getText().trim();

                Processing.getActiveStage().close();
                if (!loginText.equals("") && !passText.equals((""))){
                        try {
                                doLoginUser(loginText, passText);

                        } catch (SQLException e) {
                                throw new RuntimeException(e);
                        }
                } else {

                }
        }

        private void doLoginUser(String loginText, String passText) throws SQLException {
                User user = new User(0,loginText, passText);

                ResultSet result = getUserData(user);
                int count = 0;
                while(result.next()){
                        try {
                                count++;
                                User temp = new User(result.getInt(DBConst.FIELD_WORKER_ID),
                                        result.getString(DBConst.FIELD_WORKER_SECONDNAME),
                                        result.getString(DBConst.FIELD_USERS_PASSWORD));
                                if (temp.getWorker().getWorker_secondname().equals(user.getWorker().getWorker_secondname())
                                        && temp.getPassword().equals(user.getPassword())) {
                                        user.getWorker().setWorker_id(temp.getWorker().getWorker_id());
                                }

                        }catch (NullPointerException e){
                                e.printStackTrace();
                        }
                }

                if (count>=1){
                        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("main-show-tables-view.fxml"));
                        try {
                                Stage stage = new Stage();
                                Scene scene=new Scene(loader.load());
                                stage.setTitle("User "+user.getWorker().getWorker_id()+" "+user.getWorker().getWorker_secondname());
                                Processing.setActiveUser(user);
                                stage.setScene(scene);
                                stage.show();
                        } catch( IOException e){
                                e.printStackTrace();
                        }


                }
        }

        public ResultSet getUserData(User theUser){
                ResultSet resSet = null;
                DBConnection connection = new DBConnection();
                String select = "select "+ DBConst.TABLE_WORKERS + "."+DBConst.FIELD_WORKER_ID+", "+
                        DBConst.TABLE_WORKERS + "."+DBConst.FIELD_WORKER_FIRSTNAME+", "+
                        DBConst.TABLE_WORKERS + "."+DBConst.FIELD_WORKER_SECONDNAME+", "+
                        DBConst.TABLE_USERS + "."+DBConst.FIELD_USERS_GROUP+", "+
                        DBConst.TABLE_WORKERS + "."+DBConst.FIELD_WORKER_POST+", "+
                        DBConst.TABLE_USERS+"."+DBConst.FIELD_USERS_PASSWORD+
                        " from "+DBConst.TABLE_WORKERS+" inner join "+DBConst.TABLE_USERS +
                        " on "+ DBConst.TABLE_WORKERS+"."+DBConst.FIELD_WORKER_ID+" = "+
                        DBConst.TABLE_USERS+"."+ DBConst.FIELD_WORKER_ID+" where "+
                        DBConst.TABLE_USERS+"."+DBConst.FIELD_USERS_PASSWORD+" = '"+theUser.getPassword()+"'";

                try{
                        PreparedStatement PrSt = connection.getDbConnection().prepareStatement(select);
                        resSet = PrSt.executeQuery();
                } catch (SQLException e) {
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }

                return resSet;
        }
    }


