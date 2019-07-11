package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Response(
        @SerializedName("blog")
        var blog: Blog? = null,

        @SerializedName("posts")
        var posts: List<Post> = emptyList()

)