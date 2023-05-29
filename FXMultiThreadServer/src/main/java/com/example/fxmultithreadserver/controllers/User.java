package com.example.fxmultithreadserver.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class User {
    private String worker_firstname;
    private String worker_group;
    private Integer worker_id;
    private String worker_post;
    private String worker_secondname;
    private String password=null;

    public User(Integer worker_id, String worker_firstname, String worker_secondname
            , String worker_group, String worker_post) {
        this.worker_firstname = worker_firstname;
        this.worker_group = worker_group;
        this.worker_id = worker_id;
        this.worker_post = worker_post;
        this.worker_secondname = worker_secondname;
    }
    public User(Integer worker_id){
        this.worker_id=worker_id;
        this.worker_firstname="user"+worker_id;
        this.worker_secondname="user"+worker_id;
        this.worker_group="group of user"+worker_id;
        this.worker_post="post of user"+worker_id;
    }
    public void createUser(String password){
        this.password=password;
    }
    public String toString(){
        return this.worker_id+"\t"+this.worker_firstname+"\t"+
                this.worker_secondname+"\t"+this.worker_group+"\t"+this.worker_post;
    }
    public String workerAtributes(){
        return "worker_id: " + this.worker_id+
                "\nworker_firstname: "+ this.worker_firstname+
                "\nworker_secondname: "+ this.worker_secondname;
    }

    public String getWorker_firstname() {
        return worker_firstname;
    }

    public void setWorker_firstname(String worker_firstname) {
        this.worker_firstname = worker_firstname;
    }

    public String getWorker_group() {
        return worker_group;
    }

    public void setWorker_group(String worker_group) {
        this.worker_group = worker_group;
    }

    public Integer getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(Integer worker_id) {
        this.worker_id = worker_id;
    }

    public String getWorker_post() {
        return worker_post;
    }

    public void setWorker_post(String worker_post) {
        this.worker_post = worker_post;
    }

    public String getWorker_secondname() {
        return worker_secondname;
    }

    public void setWorker_secondname(String worker_secondname) {
        this.worker_secondname = worker_secondname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



