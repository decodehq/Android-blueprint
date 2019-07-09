package com.decode.tumblr.data.repository

import com.decode.tumblr.App
import com.decode.tumblr.data.api.ApiInterface
import com.decode.tumblr.data.db.dao.HeaderDao
import com.decode.tumblr.data.db.dao.PhotoDao
import com.decode.tumblr.data.db.dao.PostDao
import com.decode.tumblr.data.db.model.MainHeader
import com.decode.tumblr.data.db.model.PhotoObject
import com.decode.tumblr.data.db.model.PostObject
import com.decode.tumblr.data.model.Post
import com.decode.tumblr.data.model.Response
import com.decode.tumblr.helpers.DateFunction
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.lang.Long

class PostRepository(private val apiService: ApiInterface,
                     private val postDao: PostDao,
                     private val photoDao: PhotoDao,
                     private val headerDao: HeaderDao) {

    fun fetchPosts(): Maybe<List<Post>?> {
        return apiService.getPosts(App.API_KEY, 0, 20)
                .map { data -> data.response }
                .doOnSuccess { insertHeader(it) }
                .map { response -> response.posts }
                .filter { it != null && it.isNotEmpty() && it[0].photos != null }
                .doOnSuccess { savePosts(it) }
                .subscribeOn(Schedulers.io())
    }

    fun getPhotoById(id: Int): PhotoObject {
        return photoDao.getById(id)
    }

    val posts: Flowable<List<PostObject>>
        get() = postDao.allPosts

    val header: Flowable<MainHeader>
        get() = headerDao.header

    private fun insertHeader(response: Response?) {
        response?.let {
            val header = MainHeader(0,
                    it.blog?.title,
                    it.blog?.total_posts.toString(),
                    DateFunction.getDateCurrentTimeZone(Long.parseLong(it.blog!!.updated!!)))

            Timber.i("onResponse: " + header.title + " - " + header.totalPost + " " + header.updated)

            headerDao.insert(header)
        }
    }

    private fun savePosts(posts: List<Post>?) {
        posts?.let {
            for (post in it) {
                if (post.photos != null) {
                    val photoId = insertPhoto(post)
                    insertPost(post, photoId)
                }
            }
        }
    }

    private fun insertPost(post: Post, photoId: Int) {
        val postObject = PostObject(post.id!!, post.summary, photoId)
        postDao.insert(postObject)
    }

    private fun insertPhoto(post: Post): Int {
        val photo = post.photos?.get(0)
        val photoObject = PhotoObject()
        photoObject.url = photo?.altSizes!![2].url!!

        return photoDao.insert(photoObject).toInt()
    }

}
