package com.decode.tumblr.viewmodel;


import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.decode.tumblr.model.PhotoObject;
import com.decode.tumblr.model.PostObject;
import com.decode.tumblr.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class PostViewModel extends AndroidViewModel {

    private PostRepository repository;

    private MutableLiveData<List<PostObject>> posts = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();

    public PostViewModel(Application application) {
        super(application);
        repository = new PostRepository(application);
        observeAllPosts();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    private void observeAllPosts() {
        disposable.add(repository.getPosts()
                .map(postObjects -> {
                    List<PostObject> postObjects1 = new ArrayList<>();

                    for (PostObject postObject : postObjects) {
                        PhotoObject photoObject = repository.getPhotoById(postObject.getPhotoId());
                        postObject.setPhotoObject(photoObject);
                        postObjects1.add(postObject);
                    }

                    return postObjects1;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(postObjects -> {
                    posts.setValue(postObjects);
                }, throwable -> {
                    Log.e("Error", "", throwable);
                    Toast.makeText(getApplication(), "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }));
    }

    public MutableLiveData<List<PostObject>> getPosts() {
        return posts;
    }

}
