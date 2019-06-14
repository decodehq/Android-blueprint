package com.decode.tumblr.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.decode.tumblr.api.ApiClient;
import com.decode.tumblr.api.ApiInterface;
import com.decode.tumblr.helpers.SingleLiveEvent;
import com.decode.tumblr.model.MainHeader;
import com.decode.tumblr.model.Post;
import com.decode.tumblr.repository.PostRepository;

import java.util.List;

public class FragmentMainViewModel extends AndroidViewModel {

    private MutableLiveData<MainHeader> mainHeaderMutableLiveData = new MutableLiveData<>();
    private SingleLiveEvent<String> singleLiveErrorEvent = new SingleLiveEvent<>();
    private PostRepository postRepository;

    public FragmentMainViewModel(@NonNull Application context) {
        super(context);
        postRepository = new PostRepository(context);
        fetchPosts();
    }

    public SingleLiveEvent<String> getSingleLiveErrorEvent() {
        return singleLiveErrorEvent;
    }

    public MutableLiveData<MainHeader> getMainHeaderMutableLiveData() {
        return mainHeaderMutableLiveData;
    }

    public void fetchPosts() {
        postRepository.fetchPosts();
    }
}
