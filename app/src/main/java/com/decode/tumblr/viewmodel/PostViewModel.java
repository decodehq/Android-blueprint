package com.decode.tumblr.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.decode.tumblr.model.PhotoObject;
import com.decode.tumblr.model.PostObject;
import com.decode.tumblr.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel extends AndroidViewModel {

    private PostRepository repository;

    private MutableLiveData<List<PostObject>> posts = new MutableLiveData<>();
    private Observer<List<PostObject>> observer;

    public PostViewModel(Application application) {
        super(application);
        repository = new PostRepository(application);
        observeAllPosts();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        repository.getPosts().removeObserver(observer);
    }

    private void observeAllPosts() {
        observer = postObjects -> AsyncTask.execute(() -> {
            List<PostObject> postObjects1 = new ArrayList<>();

            for (PostObject postObject : postObjects) {
                PhotoObject photoObject = repository.getPhotoById(postObject.getPhotoId());
                postObject.setPhotoObject(photoObject);
                postObjects1.add(postObject);
            }

            posts.postValue(postObjects1);
        });

        repository.getPosts().observeForever(observer);
    }

    public MutableLiveData<List<PostObject>> getPosts() {
        return posts;
    }

}
