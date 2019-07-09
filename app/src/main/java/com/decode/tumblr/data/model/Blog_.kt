package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Blog_(

        @SerializedName("name")
        var name: String? = null,

        @SerializedName("active")
        var active: Boolean? = null,

        @SerializedName("theme")
        var theme: Theme? = null,

        @SerializedName("share_likes")
        var shareLikes: Boolean? = null,

        @SerializedName("share_following")
        var shareFollowing: Boolean? = null,

        @SerializedName("can_be_followed")
        var canBeFollowed: Boolean? = null

)
