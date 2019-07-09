package com.decode.tumblr.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.decode.tumblr.data.db.model.MainHeader

import io.reactivex.Flowable

@Dao
interface HeaderDao {

    @get:Query("SELECT * from header_table ORDER BY id ASC")
    val header: Flowable<MainHeader>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(header: MainHeader)

    @Query("DELETE FROM header_table")
    fun deleteAll()

}
