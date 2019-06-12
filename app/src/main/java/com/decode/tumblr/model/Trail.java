package com.decode.tumblr.model;

import com.google.gson.annotations.SerializedName;

public class Trail {

    @SerializedName("blog")
    public Blog_ blog;
    @SerializedName("post")
    public Post_ post;
    @SerializedName("content_raw")
    public String contentRaw;
    @SerializedName("content")
    public String content;
    @SerializedName("is_root_item")
    public Boolean isRootItem;

}
