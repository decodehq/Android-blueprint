package com.decode.tumblr.di

import androidx.room.Room
import com.decode.tumblr.data.db.PostRoomDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(androidContext(),
                PostRoomDatabase::class.java, PostRoomDatabase.POSTS_DATABASE)
                .build()
    }

    single { get<PostRoomDatabase>().headerDao() }

    single { get<PostRoomDatabase>().photoDao() }

    single { get<PostRoomDatabase>().postDao() }
}