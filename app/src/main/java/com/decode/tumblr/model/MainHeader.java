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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalPost() {
        return totalPost;
    }

    public void setTotalPost(String totalPost) {
        this.totalPost = totalPost;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
