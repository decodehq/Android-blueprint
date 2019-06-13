package com.decode.tumblr.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.decode.tumblr.dao.PostDao;
import com.decode.tumblr.dao.PostRoomDatabase;
import com.decode.tumblr.model.Post;
import com.decode.tumblr.model.PostObject;

import java.util.List;

public class PostRepository {

    private PostDao mPostDao;
    private LiveData<List<PostObject>> mAllPosts;


    public PostRepository(Application application) {
        PostRoomDatabase db = PostRoomDatabase.getDatabase(application);
        mPostDao = db.wordDao();
        mAllPosts = mPostDao.getAllPosts();
    }


    public LiveData<List<PostObject>> getmAllPosts() {
        return mAllPosts;
    }

    public void insert(PostObject word) {
        new insertAsyncTask(mPostDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<PostObject, Void, Void> {

        private PostDao mAsyncTaskDao;

        insertAsyncTask(PostDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final PostObject... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
