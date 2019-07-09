package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Data(

        @SerializedName("meta")
        var meta: Meta? = null,

        @SerializedName("response")
        var response: Response? = null

)
