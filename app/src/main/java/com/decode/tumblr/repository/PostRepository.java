package com.decode.tumblr.repository;

import android.content.Context;

import com.decode.tumblr.api.ApiClient;
import com.decode.tumblr.api.ApiInterface;
import com.decode.tumblr.dao.HeaderDao;
import com.decode.tumblr.dao.PhotoDao;
import com.decode.tumblr.dao.PostDao;
import com.decode.tumblr.dao.PostRoomDatabase;
import com.decode.tumblr.helpers.DateFunction;
import com.decode.tumblr.model.MainHeader;
import com.decode.tumblr.model.Photo;
import com.decode.tumblr.model.PhotoObject;
import com.decode.tumblr.model.Post;
import com.decode.tumblr.model.PostObject;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

import static com.decode.tumblr.App.API_KEY;

public class PostRepository {

    private final ApiInterface apiService;
    private PostDao postDao;
    private PhotoDao photoDao;
    private HeaderDao headerDao;

    public PostRepository(Context context) {
        PostRoomDatabase db = PostRoomDatabase.getDatabase(context);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        postDao = db.postDao();
        photoDao = db.photoDao();
        headerDao = db.headerDao();
    }

    public Maybe<List<Post>> fetchPosts() {
        return apiService.getPosts(API_KEY, 0, 20)
                .map(data -> data.response)
                .filter(response -> response != null)
                .doOnSuccess(this::insertHeader)
                .map(response -> response.posts)
                .filter(posts -> posts != null && !posts.isEmpty() && posts.get(0).photos != null)
                .doOnSuccess(this::savePosts)
                .subscribeOn(Schedulers.io());
    }

    public Flowable<List<PostObject>> getPosts() {
        return postDao.getAllPosts();
    }

    public Flowable<MainHeader> getHeader() {
        return headerDao.getHeader();
    }

    public PhotoObject getPhotoById(int id) {
        return photoDao.getById(id);
    }

    private void insertHeader(com.decode.tumblr.model.Response response) {
        MainHeader header = new MainHeader(response.blog.title,
                String.valueOf(response.blog.total_posts),
                DateFunction.getDateCurrentTimeZone(Long.parseLong(response.blog.updated)));

        Timber.i("onResponse: " + header.getTitle() + " - " + header.getTotalPost() + " " + header.getUpdated());

        headerDao.insert(header);
    }

    private void savePosts(List<Post> posts) {
        for (Post post : posts) {
            if (post.photos != null) {
                int photoId = insertPhoto(post);
                insertPost(post, photoId);
            }
        }
    }

    private void insertPost(Post post, int photoId) {
        PostObject postObject = new PostObject();
        postObject.setTitle(post.summary);
        postObject.setId(post.id);
        postObject.setPhotoId(photoId);

        postDao.insert(postObject);
    }

    private int insertPhoto(Post post) {
        Photo photo = post.photos.get(0);
        PhotoObject photoObject = new PhotoObject();
        photoObject.setUrl(photo.altSizes.get(2).url);

        return (int) photoDao.insert(photoObject);
    }

}
