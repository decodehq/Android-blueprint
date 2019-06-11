package com.tumblrdecode.model;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("meta")
    public Meta meta;
    @SerializedName("response")
    public Response response;

}