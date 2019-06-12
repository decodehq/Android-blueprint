package com.decode.tumblr.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @SerializedName("caption")
    public String caption;

    @SerializedName("alt_sizes")
    public List<AltSize> altSizes = null;

}
