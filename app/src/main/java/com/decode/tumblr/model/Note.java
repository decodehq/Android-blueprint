package com.decode.tumblr.model;

import com.google.gson.annotations.SerializedName;

public class Note {

    @SerializedName("type")
    public String type;
    @SerializedName("timestamp")
    public Integer timestamp;
    @SerializedName("blog_name")
    public String blogName;
    @SerializedName("blog_uuid")
    public String blogUuid;
    @SerializedName("blog_url")
    public String blogUrl;
    @SerializedName("followed")
    public Boolean followed;
    @SerializedName("avatar_shape")
    public String avatarShape;
    @SerializedName("post_id")
    public String postId;
    @SerializedName("reblog_parent_blog_name")
    public String reblogParentBlogName;

}
