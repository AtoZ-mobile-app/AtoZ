package com.example.teamapp;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Board_2 {

    public String title;
    public String content;

    public Board_2() {

    }

    public Board_2(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
