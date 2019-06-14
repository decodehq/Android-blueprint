package com.decode.tumblr.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.decode.tumblr.model.PostObject;


@Database(entities = {PostObject.class}, version = 1 , exportSchema = false)
public abstract class PostRoomDatabase extends RoomDatabase {

    private static volatile PostRoomDatabase INSTANCE;

    public abstract PostDao wordDao();

    public static PostRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PostRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PostRoomDatabase.class, "post_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
