package com.tumblrdecode.api;

import com.tumblrdecode.model.Data;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {

    @GET("posts")
    Call<Data> getPosts(
            @Query("api_key") String api,
            @Query("offset") int page,
            @Query("limit") int limit);
}