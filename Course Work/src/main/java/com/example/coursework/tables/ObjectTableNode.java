package com.example.coursework.tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ObjectTableNode {
    private String[] values=new String[0];
    public ObjectTableNode(ResultSet row,int columnCount) {
        this.values = new String[columnCount];
        try {
            for (int i = 1; i <= columnCount; i++) {
                values[i-1] = row.getString(i);
            }
        } catch (SQLException sqlException) {

        }
    }

    @Override
    public String toString() {
        StringBuilder temp=new StringBuilder();
        for (String i:this.values
             ) {
            temp.append(i+"\t");
        }
        return temp.toString();
    }

    public void setValue(int bound, String value){
        this.values[bound]=value;
    }
    public String getValue(int bound){
        return this.values[bound];
    }
    public String[] getValues() {
        return values;
    }

    public void setValues(String[] values) {
        this.values = values;
    }
}
