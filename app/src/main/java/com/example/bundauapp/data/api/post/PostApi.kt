package com.example.bundauapp.data.api.post

import com.example.bundauapp.data.entity.PostEntity
import retrofit2.Call

interface PostApi {
    fun getListPost(): Call<List<PostEntity>>
}