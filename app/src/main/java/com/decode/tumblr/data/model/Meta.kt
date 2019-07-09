package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Meta(

        @SerializedName("status")
        var status: Int? = null,

        @SerializedName("msg")
        var msg: String? = null

)
