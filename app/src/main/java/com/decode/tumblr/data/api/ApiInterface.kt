package com.decode.tumblr.data.api

import com.decode.tumblr.data.model.Data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("posts")
    fun getPosts(
            @Query("api_key") api: String,
            @Query("offset") page: Int,
            @Query("limit") limit: Int): Single<Data>
}