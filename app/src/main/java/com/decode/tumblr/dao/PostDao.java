package com.decode.tumblr.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.decode.tumblr.model.PostObject;

import java.util.List;

@Dao
public interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PostObject post);

    @Query("DELETE FROM post_table")
    void deleteAll();

    @Query("SELECT * from post_table ORDER BY title ASC")
    LiveData<List<PostObject>> getAllPosts();

}
