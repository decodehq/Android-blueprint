package com.decode.tumblr.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.decode.tumblr.model.MainHeader;
import com.decode.tumblr.model.PhotoObject;
import com.decode.tumblr.model.PostObject;

@Database(entities = {PostObject.class, PhotoObject.class, MainHeader.class}, version = 1, exportSchema = false)
public abstract class PostRoomDatabase extends RoomDatabase {

    private static final String POSTS_DATABASE = "post_database";
    private static volatile PostRoomDatabase INSTANCE;

    public abstract PostDao postDao();

    public abstract PhotoDao photoDao();

    public abstract HeaderDao headerDao();

    public static PostRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PostRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PostRoomDatabase.class, POSTS_DATABASE)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
