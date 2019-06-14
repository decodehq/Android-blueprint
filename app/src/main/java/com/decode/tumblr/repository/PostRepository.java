package com.decode.tumblr.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.decode.tumblr.api.ApiClient;
import com.decode.tumblr.api.ApiInterface;
import com.decode.tumblr.dao.HeaderDao;
import com.decode.tumblr.dao.PhotoDao;
import com.decode.tumblr.dao.PostDao;
import com.decode.tumblr.dao.PostRoomDatabase;
import com.decode.tumblr.helpers.DateFunction;
import com.decode.tumblr.model.Data;
import com.decode.tumblr.model.MainHeader;
import com.decode.tumblr.model.Photo;
import com.decode.tumblr.model.PhotoObject;
import com.decode.tumblr.model.Post;
import com.decode.tumblr.model.PostObject;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.decode.tumblr.App.API_KEY;

public class PostRepository {

    private final ApiInterface apiService;
    private PostDao postDao;
    private PhotoDao photoDao;
    private HeaderDao headerDao;
    private int pageIndex = 0;
    private int pageLimit = 20;

    public PostRepository(Application application) {
        PostRoomDatabase db = PostRoomDatabase.getDatabase(application);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        postDao = db.postDao();
        photoDao = db.photoDao();
        headerDao = db.headerDao();
    }


    public void fetchPosts() {
        Call<Data> call = apiService.getPosts(API_KEY, pageIndex, pageLimit);
        call.enqueue(new Callback<Data>() {
            @Override
            public void onResponse(@NonNull Call<Data> call, @NonNull Response<Data> response) {
                List<Post> results = fetchResults(response);

                if (results != null) {
                    MainHeader header = new MainHeader(response.body().response.blog.title,
                            String.valueOf(response.body().response.blog.total_posts),
                            DateFunction.getDateCurrentTimeZone(Long.parseLong(response.body().response.blog.updated)));

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            //TODO your background code
                            headerDao.insert(header);

                            // Save photo object && post object to database
                            if (response.body() != null && response.body().response.posts != null) {

                                for (Post post : response.body().response.posts) {

//                                    if (post.photos.get(0) != null || post.photos.get(0).altSizes.get(2) != null) {
                                    try {
                                        Photo photo = post.photos.get(0);

                                        PhotoObject photoObject = new PhotoObject();
                                        photoObject.setUrl(photo.altSizes.get(2).url);
                                        int photoId = (int) photoDao.insert(photoObject);

                                        PostObject postObject = new PostObject();
                                        postObject.setTitle(post.summary);
                                        postObject.setId(post.id);
                                        postObject.setPhotoObject(photoObject);
                                        postObject.setPhotoId(photoId);

                                        postDao.insert(postObject);

                                        Log.i("Image", "run: "+postObject.getPhotoObject().getUrl());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                                }
                            }

//                        }
                    });

                }

            }

            @Override
            public void onFailure(@NonNull Call<Data> call, @NonNull Throwable t) {
                // handle error
                Log.e("Error", Objects.requireNonNull(t.getMessage()));
            }
        });
    }


    public LiveData<List<PostObject>> getPosts() {

        return postDao.getAllPosts();

    }

    public PhotoObject getPhotoById(int id) {

        return photoDao.getById(id);

    }

    private List<Post> fetchResults(Response<Data> response) {
        if (response.body() != null) {
            Data data = response.body();
            return data.response.posts;
        }
        return null;
    }

    public void insert(PostObject word) {
        new insertAsyncTask(postDao).execute(word);
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
