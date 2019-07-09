package com.decode.tumblr.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.decode.tumblr.data.db.dao.HeaderDao
import com.decode.tumblr.data.db.dao.PhotoDao
import com.decode.tumblr.data.db.dao.PostDao
import com.decode.tumblr.data.db.model.MainHeader
import com.decode.tumblr.data.db.model.PhotoObject
import com.decode.tumblr.data.db.model.PostObject

@Database(entities = [PostObject::class, PhotoObject::class, MainHeader::class], version = 1, exportSchema = false)
abstract class PostRoomDatabase : RoomDatabase() {

    companion object {
        val POSTS_DATABASE = "post_database"
    }

    abstract fun postDao(): PostDao

    abstract fun photoDao(): PhotoDao

    abstract fun headerDao(): HeaderDao

}
