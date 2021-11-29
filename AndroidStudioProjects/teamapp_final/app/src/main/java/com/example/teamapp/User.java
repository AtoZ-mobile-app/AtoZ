package com.example.teamapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String userName;
    public String userPw;
    public String pw;

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public User() {

    }

    public User(String userName, String pw, String userPw) {
        this.userName = userName;
        this.userPw = userPw;
        this.pw = pw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPw() {
        return userPw;
    }

    public void setPw(String pw) {
        this.userPw = pw;
    }
}
