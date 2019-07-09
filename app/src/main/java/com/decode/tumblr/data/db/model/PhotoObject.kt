package com.decode.tumblr.data.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "photo_table")
@Parcelize
data class PhotoObject(

        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,

        var url: String = ""

) : Parcelable
