package com.example.bundauapp.data.api.post

import com.example.bundauapp.data.entity.PostEntity
import com.example.bundauapp.data.retrofit.ServiceGenerator
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostApiImpl @Inject constructor() : PostApi {
    private var service = ServiceGenerator.createService(PostService::class.java)
    override fun getListPost(): Call<List<PostEntity>> {
        return service.getListPost()
    }
}