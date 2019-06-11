package com.tumblrdecode.model;

import com.google.gson.annotations.SerializedName;

public class Reblog {
    @SerializedName("comment")
    public String comment;
    @SerializedName("tree_html")
    public String treeHtml;

}