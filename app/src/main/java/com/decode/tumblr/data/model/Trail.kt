package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Trail(

        @SerializedName("blog")
        var blog: Blog_? = null,

        @SerializedName("post")
        var post: Post_? = null,

        @SerializedName("content_raw")
        var contentRaw: String? = null,

        @SerializedName("content")
        var content: String? = null,

        @SerializedName("is_root_item")
        var isRootItem: Boolean? = null

)
