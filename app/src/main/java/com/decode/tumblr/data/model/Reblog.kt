package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Reblog(
        @SerializedName("comment")
        var comment: String? = null,

        @SerializedName("tree_html")
        var treeHtml: String? = null

)