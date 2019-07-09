package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Note(

        @SerializedName("type")
        var type: String? = null,

        @SerializedName("timestamp")
        var timestamp: Int? = null,

        @SerializedName("blog_name")
        var blogName: String? = null,

        @SerializedName("blog_uuid")
        var blogUuid: String? = null,

        @SerializedName("blog_url")
        var blogUrl: String? = null,

        @SerializedName("followed")
        var followed: Boolean? = null,

        @SerializedName("avatar_shape")
        var avatarShape: String? = null,

        @SerializedName("post_id")
        var postId: String? = null,

        @SerializedName("reblog_parent_blog_name")
        var reblogParentBlogName: String? = null

)
