package com.example.coursework.tables;

public class Worker {
    private int worker_id;
    private String worker_firstname;
    private String worker_secondname;
    private String worker_post;

    public Worker(int worker_id, String worker_firstname, String worker_secondname, String worker_post) {
        this.worker_id = worker_id;
        this.worker_firstname = worker_firstname;
        this.worker_secondname = worker_secondname;
        this.worker_post = worker_post;
    }
    public Worker(){}

    @Override
    public String toString() {
        return "Worker{" +
                "worker_id=" + worker_id +
                ", worker_firstname='" + worker_firstname + '\'' +
                ", worker_secondname='" + worker_secondname + '\'' +
                ", worker_post='" + worker_post + '\'' +
                '}';
    }

    public int getWorker_id() {
        return worker_id;
    }

    public void setWorker_id(int worker_id) {
        this.worker_id = worker_id;
    }

    public String getWorker_firstname() {
        return worker_firstname;
    }

    public void setWorker_firstname(String worker_firstname) {
        this.worker_firstname = worker_firstname;
    }

    public String getWorker_secondname() {
        return worker_secondname;
    }

    public void setWorker_secondname(String worker_secondname) {
        this.worker_secondname = worker_secondname;
    }

    public String getWorker_post() {
        return worker_post;
    }

    public void setWorker_post(String worker_post) {
        this.worker_post = worker_post;
    }
}
