package com.decode.tumblr.posts;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.decode.tumblr.data.model.MainHeader;
import com.decode.tumblr.data.model.PhotoObject;
import com.decode.tumblr.data.model.PostObject;
import com.decode.tumblr.data.repository.PostRepository;
import com.decode.tumblr.helpers.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class PostViewModel extends AndroidViewModel {

    private PostRepository repository;

    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<PostObject>> posts = new MutableLiveData<>();
    private MutableLiveData<MainHeader> header = new MutableLiveData<>();
    private SingleLiveEvent<String> loadingFailed = new SingleLiveEvent<>();
    private SingleLiveEvent<String> loadingSuccess = new SingleLiveEvent<>();

    public PostViewModel(Application application, PostRepository postRepository) {
        super(application);
        repository = postRepository;
        observeAllPosts();
        observeHeader();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    public MutableLiveData<List<PostObject>> getPosts() {
        return posts;
    }

    void fetchPosts() {
        disposable.add(repository.fetchPosts()
                .subscribe(posts -> loadingSuccess.postValue("Loaded"),
                        throwable -> loadingFailed.postValue(throwable.getMessage())));
    }

    SingleLiveEvent<String> getLoadingFailed() {
        return loadingFailed;
    }

    MutableLiveData<MainHeader> getHeader() {
        return header;
    }

    private void observeAllPosts() {
        disposable.add(repository.getPosts()
                .map(posts -> {
                    List<PostObject> postObjects = new ArrayList<>();

                    for (PostObject postObject : posts) {
                        PhotoObject photoObject = repository.getPhotoById(postObject.getPhotoId());
                        postObject.setPhotoObject(photoObject);
                        postObjects.add(postObject);
                    }

                    return postObjects;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(postObjects -> posts.setValue(postObjects),
                        throwable -> {
                            Timber.e(throwable);
                            loadingFailed.setValue(throwable.getMessage());
                        }));
    }

    private void observeHeader() {
        disposable.add(repository.getHeader()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(mainHeader -> header.setValue(mainHeader), Timber::e));
    }

}
