package com.decode.tumblr.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.decode.tumblr.data.db.model.MainHeader
import com.decode.tumblr.data.db.model.PostObject
import com.decode.tumblr.data.repository.PostRepository
import com.decode.tumblr.helpers.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class PostViewModel(private val repository: PostRepository) : ViewModel() {

    val posts = MutableLiveData<List<PostObject>>()
    internal val header = MutableLiveData<MainHeader>()
    internal val loadingFailed = SingleLiveEvent<String>()
    private val disposable = CompositeDisposable()
    private val loadingSuccess = SingleLiveEvent<String>()

    init {
        observeAllPosts()
        observeHeader()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    internal fun fetchPosts() {
        disposable.add(repository.fetchPosts()
                .subscribe({ loadingSuccess.postValue("Loaded") },
                        { throwable -> loadingFailed.postValue(throwable.message) }))
    }

    private fun observeAllPosts() {
        disposable.add(repository.posts
                .map<List<PostObject>> { posts ->
                    val postObjects = ArrayList<PostObject>()

                    for (postObject in posts) {
                        val photoObject = repository.getPhotoById(postObject.photoId)
                        postObject.photoObject = photoObject
                        postObjects.add(postObject)
                    }

                    postObjects
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ postObjects -> posts.setValue(postObjects) },
                        { throwable ->
                            Timber.e(throwable)
                            loadingFailed.setValue(throwable.message)
                        }))
    }

    private fun observeHeader() {
        disposable.add(repository.header
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ mainHeader -> header.setValue(mainHeader) }, { Timber.e(it) }))
    }

}
