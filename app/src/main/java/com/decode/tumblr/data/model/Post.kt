package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Post(

        @SerializedName("type")
        var type: String,

        @SerializedName("blog_name")
        var blogName: String,

        @SerializedName("id")
        var id: Long? = null,

        @SerializedName("post_url")
        var postUrl: String,

        @SerializedName("slug")
        var slug: String,

        @SerializedName("date")
        var date: String,

        @SerializedName("timestamp")
        var timestamp: Int? = null,

        @SerializedName("state")
        var state: String,

        @SerializedName("format")
        var format: String,

        @SerializedName("reblog_key")
        var reblogKey: String,

        @SerializedName("tags")
        var tags: List<String>? = null,

        @SerializedName("short_url")
        var shortUrl: String,

        @SerializedName("summary")
        var summary: String,

        @SerializedName("is_blocks_post_format")
        var isBlocksPostFormat: Boolean? = null,

        @SerializedName("recommended_source")
        var recommendedSource: Any? = null,

        @SerializedName("recommended_color")
        var recommendedColor: Any? = null,

        @SerializedName("note_count")
        var noteCount: Int? = null,

        @SerializedName("source_url")
        var sourceUrl: String,

        @SerializedName("source_title")
        var sourceTitle: String,

        @SerializedName("title")
        var title: String,

        @SerializedName("body")
        var body: String,

        @SerializedName("caption")
        var caption: String,

        @SerializedName("can_like")
        var canLike: String,

        @SerializedName("can_reblog")
        var canReblog: String,

        @SerializedName("can_send_in_message")
        var canSendInMessage: String,

        @SerializedName("can_reply")
        var canReply: String,

        @SerializedName("display_avatar")
        var displayAvatar: String,

        @SerializedName("image_permalink")
        var imagePermalink: String,

        @SerializedName("reblog")
        var reblog: Reblog? = null,

        @SerializedName("trail")
        var trail: List<Trail>? = null,

        @SerializedName("notes")
        var notes: List<Note>? = null,

        @SerializedName("photos")
        var photos: List<Photo>? = null

)

