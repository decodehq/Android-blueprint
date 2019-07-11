package com.decode.tumblr.helpers

import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned

@Suppress("DEPRECATION")
fun fromHtml(html: String?): Spanned {
    return when {
        html == null -> SpannableString("")
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ->
            // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
            // we are using this flag to give a consistent behaviour
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        else -> Html.fromHtml(html)
    }
}
