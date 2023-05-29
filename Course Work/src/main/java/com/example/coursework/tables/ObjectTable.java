package com.example.coursework.tables;

public class ObjectTable {
    private String table_name;
    private int table_number;

    public ObjectTable(String table_name, int table_number) {
        this.table_name = table_name;
        this.table_number = table_number;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public int getTable_number() {
        return table_number;
    }

    public void setTable_number(int table_number) {
        this.table_number = table_number;
    }

    @Override
    public String toString() {
        return "ObjectTable{" +
                "table_name='" + table_name + '\'' +
                ", table_number=" + table_number +
                '}';
    }
}
