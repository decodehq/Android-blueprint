package com.decode.tumblr.helpers

import java.text.SimpleDateFormat
import java.util.*

object DateFunction {

    fun getDateCurrentTimeZone(timestamp: Long): String {
        return try {
            val calendar = Calendar.getInstance()
            val tz = TimeZone.getDefault()
            calendar.timeInMillis = timestamp * 1000
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
            val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
            val currentTimeZone = calendar.time
            sdf.format(currentTimeZone)
        } catch (e: Exception) {
            ""
        }
    }
}
