package com.decode.tumblr.api;

import com.decode.tumblr.model.Data;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("posts")
    Single<Data> getPosts(
            @Query("api_key") String api,
            @Query("offset") int page,
            @Query("limit") int limit);
}