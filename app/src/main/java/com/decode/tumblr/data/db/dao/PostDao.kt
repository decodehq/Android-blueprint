package com.decode.tumblr.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.decode.tumblr.data.db.model.PostObject

import io.reactivex.Flowable

@Dao
interface PostDao {

    @get:Query("SELECT * from post_table ORDER BY title ASC")
    val allPosts: Flowable<List<PostObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(post: PostObject)

    @Query("DELETE FROM post_table")
    fun deleteAll()

}
