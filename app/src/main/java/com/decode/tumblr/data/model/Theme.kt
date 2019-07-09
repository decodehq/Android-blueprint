package com.decode.tumblr.data.model

import com.google.gson.annotations.SerializedName

data class Theme(

        @SerializedName("header_full_width")
        var headerFullWidth: Int? = null,

        @SerializedName("header_full_height")
        var headerFullHeight: Int? = null,

        @SerializedName("header_focus_width")
        var headerFocusWidth: Int? = null,

        @SerializedName("header_focus_height")
        var headerFocusHeight: Int? = null,

        @SerializedName("avatar_shape")
        var avatarShape: String? = null,

        @SerializedName("background_color")
        var backgroundColor: String? = null,

        @SerializedName("body_font")
        var bodyFont: String? = null,

        @SerializedName("header_bounds")
        var headerBounds: String? = null,

        @SerializedName("header_image")
        var headerImage: String? = null,

        @SerializedName("header_image_focused")
        var headerImageFocused: String? = null,

        @SerializedName("header_image_scaled")
        var headerImageScaled: String? = null,

        @SerializedName("header_stretch")
        var headerStretch: Boolean? = null,

        @SerializedName("link_color")
        var linkColor: String? = null,

        @SerializedName("show_avatar")
        var showAvatar: Boolean? = null,

        @SerializedName("show_description")
        var showDescription: Boolean? = null,

        @SerializedName("show_header_image")
        var showHeaderImage: Boolean? = null,

        @SerializedName("show_title")
        var showTitle: Boolean? = null,

        @SerializedName("title_color")
        var titleColor: String? = null,

        @SerializedName("title_font")
        var titleFont: String? = null,

        @SerializedName("title_font_weight")
        var titleFontWeight: String? = null

)
