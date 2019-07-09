package com.decode.tumblr.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "header_table")
public class MainHeader {

    @PrimaryKey()
    private int id;

    private String title;

    private String totalPost;

    private String updated;

    public MainHeader(String title, String totalPost, String updated) {
        this.title = title;
        this.totalPost = totalPost;
        this.updated = updated;
    }

    public String getTitle() {
        return title;
    }

    public String getTotalPost() {
        return totalPost;
    }

    public String getUpdated() {
        return updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public void setTotalPost(@NonNull String totalPost) {
        this.totalPost = totalPost;
    }

    public void setUpdated(@NonNull String updated) {
        this.updated = updated;
    }
}
