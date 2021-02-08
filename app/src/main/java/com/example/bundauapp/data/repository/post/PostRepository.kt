package com.example.bundauapp.data.repository.post

import androidx.lifecycle.LiveData
import com.example.bundauapp.data.entity.PostEntity
import com.example.bundauapp.data.retrofit.resource.Resource

interface PostRepository {
    fun getListPost(): LiveData<Resource<List<PostEntity>>>
}