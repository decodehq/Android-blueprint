package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class AltSize(

        @SerializedName("width")
        var width: Int = 0,

        @SerializedName("height")
        var height: Int = 0,

        @SerializedName("url")
        var url: String? = null

)
