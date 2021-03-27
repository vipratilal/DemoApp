package com.app.demoapp.model;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_user")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "firstName")
    private String firstName;

    @ColumnInfo(name = "userEmail")
    private String userEmail;

    @ColumnInfo(name = "userPassword")
    private String userPassword;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
