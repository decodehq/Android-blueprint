package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Blog(

        @SerializedName("description")
        var description: String? = null,

        @SerializedName("title")
        var title: String? = null,

        @SerializedName("updated")
        var updated: String? = null,

        @SerializedName("url")
        var url: String? = null,

        @SerializedName("total_posts")
        var total_posts: Int = 0

)