package com.decode.tumblr.model;

import com.google.gson.annotations.SerializedName;

public class Blog {

    @SerializedName("description")
    public String description;

    @SerializedName("title")
    public String title;

    @SerializedName("updated")
    public String updated;

    @SerializedName("url")
    public String url;

    @SerializedName("total_posts")
    public int total_posts;


}