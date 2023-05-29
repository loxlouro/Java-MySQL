package com.example.coursework.dbhandling;

public class DBConfig {

    protected String dbHost = "192.168.100.11";
    protected String dbPort = "3306";
    protected String dbUser = "simple_user";
    protected String dbPass = "user1234";
    protected String dbName = "coursework";
    public void setDBAdress(String adress){
        this.dbHost=adress;
    }
}
