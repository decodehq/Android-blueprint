package com.decode.tumblr.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.decode.tumblr.model.PhotoObject;

import java.util.List;

@Dao
public interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(PhotoObject photo);

    @Query("DELETE FROM photo_table")
    void deleteAll();

    @Query("SELECT * from photo_table ORDER BY id ASC")
    LiveData<List<PhotoObject>> getAllPhotos();

    @Query("SELECT * from photo_table WHERE id=:id")
    PhotoObject getById(int id);

}
