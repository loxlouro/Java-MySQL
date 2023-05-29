package com.example.coursework.tables;

public class User {
    private String password;
    private String worker_group;
    private Worker worker;

    public User(String password, String worker_group, Worker worker) {
        this.password = password;
        this.worker_group = worker_group;
        this.worker = worker;
    }

    public User(int id, String login, String password){
        this.password=password;
        this.worker=new Worker();
        this.worker.setWorker_secondname(login);
    }

    @Override
    public String toString() {
        return "User{" +
                "password='" + password + '\'' +
                ", worker_group='" + worker_group + '\'' +
                ", worker=" + worker +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorker_group() {
        return worker_group;
    }

    public void setWorker_group(String worker_group) {
        this.worker_group = worker_group;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
