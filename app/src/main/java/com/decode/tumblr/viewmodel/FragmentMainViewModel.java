package com.decode.tumblr.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.decode.tumblr.helpers.SingleLiveEvent;
import com.decode.tumblr.model.MainHeader;
import com.decode.tumblr.repository.PostRepository;

public class FragmentMainViewModel extends AndroidViewModel {

    private SingleLiveEvent<String> singleLiveErrorEvent = new SingleLiveEvent<>();
    private PostRepository postRepository;

    public FragmentMainViewModel(@NonNull Application context) {
        super(context);
        postRepository = new PostRepository(context);
    }

    public SingleLiveEvent<String> loadErrorEvent() {
        return singleLiveErrorEvent;
    }


    public void fetchPosts() {
        postRepository.fetchPosts();
    }


    public LiveData<MainHeader> getMainHeaderLiveData() {
        return postRepository.getHeader();
    }
}
