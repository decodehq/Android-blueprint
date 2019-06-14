package com.decode.tumblr.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.decode.tumblr.model.MainHeader;

@Dao
public interface HeaderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MainHeader header);

    @Query("DELETE FROM header_table")
    void deleteAll();

    @Query("SELECT * from header_table ORDER BY id ASC")
    LiveData<MainHeader> getHeader();

}
