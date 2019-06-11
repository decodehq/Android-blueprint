package com.tumblrdecode.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("blog")
    public Blog blog;
    @SerializedName("posts")
    public List<Post> posts = null;

}