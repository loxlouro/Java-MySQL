package com.example.coursework;

import com.example.coursework.dbhandling.DBConnection;
import com.example.coursework.dbhandling.DBConst;
import com.example.coursework.tables.ObjectTable;
import com.example.coursework.tables.ObjectTableNode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainShowTablesController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Menu TablesMenu;
    @FXML
    private TableView<ObjectTableNode> objectTableTableView = new TableView<>();
    private static TableColumn<ObjectTableNode,String>[] columns;
    @FXML
    private static AnchorPane mainViewRoot;
    @FXML
    private static BorderPane tablePane = new BorderPane();
    private static TableView<ObjectTableNode> activeTable;
    ObservableList<ObjectTable> tableMenu = FXCollections.observableArrayList(tableList(getTables()));

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        for (MenuItem i:objectTableToMenuItemList(tableMenu)) {
            this.TablesMenu.getItems().add(i);
        }
        EventHandler<ActionEvent>[] list = new EventHandler[TablesMenu.getItems().size()];
        for (int i=0;i<list.length;i++) {
            list[i]=this.TablesMenu.getItems().get(i).getOnAction();
        }
        for (MenuItem i:this.TablesMenu.getItems()
             ) {
            i.setOnAction(event -> {
                onTablesMenuAction(Integer.valueOf(i.getId())-1);

            });
        }
    }
    public void onTablesMenuAction(Integer bound){
        String[] tempSplit = this.TablesMenu.getItems().get(bound).getText().split("\t");
        objectTableTableView.setItems(createObjectTable(objectTableTableView,
                getTable(tempSplit[1])));
//        for (ObjectTableNode i:objectTableTableView.getItems()){
//            System.out.println(i);
//        }
//        for (TableColumn<ObjectTableNode,?> i:objectTableTableView.getColumns()
//             ) {
//            System.out.println(i.getText());
//        }
        //activeTable=theTable;
    }

    private static ObservableList<MenuItem> objectTableToMenuItemList (ObservableList<ObjectTable> tableMenu){
        ObservableList<MenuItem> temp = FXCollections.observableArrayList();
        for (ObjectTable i: tableMenu
             ) {
            MenuItem t =new MenuItem(i.getTable_number() +"\t"+i.getTable_name());
            t.setId(String.valueOf(i.getTable_number()));
            temp.add(t);
        }
        return temp;

    }
    public static ArrayList<ObjectTable> tableList(ResultSet tables){
        ArrayList<ObjectTable> temp = new ArrayList<>();
        try {
            int n=1;
            while (tables.next()) {
                temp.add(new ObjectTable(tables.getString(DBConst.TABLES_IN_COURSEWORK),n++));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return temp;
    }
    private static ResultSet getTables(){
        ResultSet temp = null;
        DBConnection connection = new DBConnection();
        String show = "show tables";
        try {
            PreparedStatement PrSt = connection.getDbConnection().prepareStatement(show);
            temp=PrSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return temp;
    }
    public static ObservableList<ObjectTableNode> createObjectTable(TableView<ObjectTableNode> tableView, ResultSet table){
        ArrayList<ObjectTableNode> tempArray = new ArrayList<>();
        int columnCount=1;
        try {
            table.next();
            try {
                while (table.getString(columnCount) != null) {
                    columnCount++;
                }
            } catch (SQLException e){
                columnCount=3;
            }
            setColumns(tableView,table,columnCount);

            ObjectTableNode tempNode = new ObjectTableNode(table,columnCount);
            tempArray.add(tempNode);
            while (table.next()) {
                tempNode = new ObjectTableNode(table,columnCount);
                tempArray.add(tempNode);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(tempArray);
    }
    private static void setColumns(TableView<ObjectTableNode> objectTableTableView,ResultSet row, int columnCount){
        try {

            columns=new TableColumn[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columns[i-1]=new TableColumn<>(row.getString(i));
                columns[i-1].setCellValueFactory(new PropertyValueFactory<ObjectTableNode, String>(row.getString(i)));
                objectTableTableView.getColumns().add(columns[i-1]);
                //System.out.println(row.getString(i));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static ResultSet getTable(String tableName){
        ResultSet temp = null;
        DBConnection connection = new DBConnection();
        String select = "select * from "+tableName;
        try {
            PreparedStatement PrSt = connection.getDbConnection().prepareStatement(select);
            temp=PrSt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return temp;
    }
    public static AnchorPane getMainViewRoot() {
        return mainViewRoot;
    }

    public static void setMainViewRoot(AnchorPane mainViewRoot) {
        MainShowTablesController.mainViewRoot = mainViewRoot;
    }
}
