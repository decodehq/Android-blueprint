package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Photo(

        @SerializedName("caption")
        var caption: String? = null,

        @SerializedName("alt_sizes")
        var altSizes: List<AltSize>? = null

)
