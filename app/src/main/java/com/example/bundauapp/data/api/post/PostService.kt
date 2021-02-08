package com.example.bundauapp.data.api.post

import com.example.bundauapp.data.entity.PostEntity
import retrofit2.Call
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    fun getListPost(): Call<List<PostEntity>>
}