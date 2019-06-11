package com.tumblrdecode.model;

import com.google.gson.annotations.SerializedName;

public class Blog_ {

    @SerializedName("name")
    public String name;
    @SerializedName("active")
    public Boolean active;
    @SerializedName("theme")
    public Theme theme;
    @SerializedName("share_likes")
    public Boolean shareLikes;
    @SerializedName("share_following")
    public Boolean shareFollowing;
    @SerializedName("can_be_followed")
    public Boolean canBeFollowed;

}
