package com.decode.tumblr.data.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "post_table")
@Parcelize
data class PostObject(

        @PrimaryKey
        var id: Long = 0,

        var title: String = "",

        var photoId: Int = 0,

        @Ignore
        var photoObject: PhotoObject? = null

) : Parcelable
