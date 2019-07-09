package com.decode.tumblr.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.decode.tumblr.data.db.model.PhotoObject

@Dao
interface PhotoDao {

    @get:Query("SELECT * from photo_table ORDER BY id ASC")
    val allPhotos: LiveData<List<PhotoObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo: PhotoObject): Long

    @Query("DELETE FROM photo_table")
    fun deleteAll()

    @Query("SELECT * from photo_table WHERE id=:id")
    fun getById(id: Int): PhotoObject

}
