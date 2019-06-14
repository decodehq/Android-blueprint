package com.decode.tumblr.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.decode.tumblr.model.PostObject;
import com.decode.tumblr.repository.PostRepository;

import java.util.List;

public class PostViewModel extends AndroidViewModel {

    private PostRepository mRepository;

    private LiveData<List<PostObject>> mAllPosts;

    public PostViewModel(Application application) {
        super(application);
        mRepository = new PostRepository(application);
        mAllPosts = mRepository.getmAllPosts();
    }

    public LiveData<List<PostObject>> getmAllPosts() {
        return mAllPosts;
    }

    public void insert(PostObject word) {
        mRepository.insert(word);
    }

}
