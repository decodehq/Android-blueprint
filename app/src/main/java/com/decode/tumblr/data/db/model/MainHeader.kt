package com.decode.tumblr.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "header_table")
data class MainHeader(
        @PrimaryKey var id: Int = 0,
        var title: String?,
        var totalPost: String?,
        var updated: String?
)
