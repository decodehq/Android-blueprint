package com.decode.tumblr.model;

public class MainHeader {

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
}
